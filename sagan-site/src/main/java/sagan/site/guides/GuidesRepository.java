package sagan.site.guides;

//import sagan.site.projects.Project;

import java.util.Optional;

public interface GuidesRepository<T extends Guide> {

	GuideHeader[] findAll();

	Optional<GuideHeader> findGuideHeaderByName(String name);

	T findByName(String name);
// TODO
//   	GuideHeader[] findByProject(Project project);
}
