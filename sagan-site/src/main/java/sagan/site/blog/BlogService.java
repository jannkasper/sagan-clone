package sagan.site.blog;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sagan.site.support.DateFactory;
import sagan.site.team.MemberProfile;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Service providing high-level, selectively cached data access and other {@link Post}
 * -related operations.
 */
@Service
public class BlogService {

    private static final Log logger = LogFactory.getLog(BlogService.class);

    private final PostRepository postRepository;
    private final DateFactory dateFactory;

    private String disqusShortname;

    public BlogService(PostRepository postRepository, DateFactory dateFactory) {
        this.postRepository = postRepository;
        this.dateFactory = dateFactory;
    }

    // Property methods

    public String getDisqusShortname() {
        return disqusShortname;
    }

    // Query methods

    public Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));
    }

    public Post getPost(String title, Date createdAt) {
        return postRepository.findByTitleAndCreatedAt(title, createdAt);
    }

    public Page<Post> getDraftPosts(Pageable pageRequest) {
        return postRepository.findByDraftTrue(pageRequest);
    }

    public Page<Post> getScheduledPosts(Pageable pageRequest) {
        return postRepository.findByDraftFalseAndPublishAtAfter(dateFactory.now(), pageRequest);
    }

    public Page<Post> getPublishedPosts(Pageable pageRequest) {
        return postRepository.findByDraftFalseAndPublishAtBeforeOrderByPublishAtDesc(dateFactory.now(), pageRequest);
    }

    public Post getPublishedPost(String publicSlug) {
        Date now = dateFactory.now();
        Post post = postRepository.findByPublicSlugAndDraftFalseAndPublishAtBefore(publicSlug, now);
        if (post == null) {
            post = postRepository.findByPublicSlugAliasesInAndDraftFalseAndPublishAtBefore(
                    Collections.singleton(publicSlug), now);
            if (post != null) {
                throw new PostMovedException(post.getPublicSlug());
            }
            throw new PostNotFoundException(publicSlug);
        }
        return post;
    }

    public List<Post> getAllPublishedPosts() {
        return postRepository.findByDraftFalseAndPublishAtBeforeOrderByPublishAtDesc(dateFactory.now());
    }

    public Page<Post> getPublishedPostsByDate(int year, int month, int day, Pageable pageRequest) {
        return postRepository.findByDate(year, month, day, pageRequest);
    }

    public Page<Post> getPublishedPostsByDate(int year, int month, Pageable pageRequest) {
        return postRepository.findByDate(year, month, pageRequest);
    }

    public Page<Post> getPublishedPostsByDate(int year, Pageable pageRequest) {
        return postRepository.findByDate(year, pageRequest);
    }

    public Page<Post> getPublishedPosts(PostCategory category, Pageable pageRequest) {
        return postRepository.findByCategoryAndDraftFalseAndPublishAtBefore(category, dateFactory.now(), pageRequest);
    }

    public Page<Post> getPublishedBroadcastPosts(Pageable pageRequest) {
        return postRepository.findByBroadcastAndDraftFalseAndPublishAtBefore(true, dateFactory.now(), pageRequest);
    }

    public Page<Post> getPublishedPostsForMember(MemberProfile profile, Pageable pageRequest) {
        return postRepository.findByDraftFalseAndAuthorAndPublishAtBeforeOrderByPublishAtDesc(profile, dateFactory.now(), pageRequest);
    }

    public Page<Post> getAllPosts(Pageable pageRequest) {
        return postRepository.findAll(pageRequest);
    }
}
