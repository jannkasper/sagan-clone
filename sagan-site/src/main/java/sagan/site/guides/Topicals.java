package sagan.site.guides;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import sagan.site.projects.Project;
import sagan.site.renderer.GuideContent;
import sagan.site.renderer.SaganRendererClient;

import java.util.Arrays;
import java.util.Optional;

/**
 * Repository implementation providing data access services for tutorial guides.
 */
@Component
public class Topicals implements GuidesRepository<Topical> {

	private static Logger logger = LoggerFactory.getLogger(Topicals.class);

	public static final String CACHE_TOPICALS = "cache.topicals";

	public static final String CACHE_TOPICAL = "cache.topical";

	private final SaganRendererClient client;

	@Autowired
	public Topicals(SaganRendererClient client) {
		this.client = client;
	}

	@Override
	@Cacheable(CACHE_TOPICALS)
	public GuideHeader[] findAll() {
		return Arrays.stream(this.client.fetchTopicalGuides())
				.map(DefaultGuideHeader::new)
				.toArray(DefaultGuideHeader[]::new);
	}

	@Override
	@Cacheable(cacheNames = CACHE_TOPICALS, key="#project.id")
	public GuideHeader[] findByProject(Project project) {
		return Arrays.stream(findAll())
				.filter(guide -> guide.getProjects().contains(project.getId()))
				.toArray(GuideHeader[]::new);
	}

	@Override
	public Optional<GuideHeader> findGuideHeaderByName(String name) {
		DefaultGuideHeader guideHeader = new DefaultGuideHeader(this.client.fetchTopicalGuide(name));
		return Optional.of(guideHeader);
	}

	@Override
	@Cacheable(CACHE_TOPICAL)
	public Topical findByName(String name) {
		try {
			DefaultGuideHeader guideHeader = new DefaultGuideHeader(this.client.fetchTopicalGuide(name));
			GuideContent guideContent = this.client.fetchTopicalGuideContent(name);
			return new Topical(guideHeader, guideContent);
		}
		catch (Exception exc) {
			logger.error("Could not render topical [" + name +"]", exc);
			throw exc;
		}
	}

	@CacheEvict(CACHE_TOPICALS)
	public void evictListFromCache() {
		logger.info("Tutorials evicted from cache");
	}

	@CacheEvict(CACHE_TOPICAL)
	public void evictFromCache(String guide) {
		logger.info("Tutorial evicted from cache: {}", guide);
	}

}
