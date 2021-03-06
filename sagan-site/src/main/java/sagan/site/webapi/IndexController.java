package sagan.site.webapi;

import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sagan.site.webapi.project.ProjectMetadataController;
import sagan.site.webapi.repository.RepositoryMetadataController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class IndexController {

    @GetMapping(path = "/api", produces = MediaTypes.HAL_JSON_VALUE)
    public RepresentationModel index() {
        return RepresentationModel.of(null).add(
                linkTo(methodOn(ProjectMetadataController.class).listProjects()).withRel("projects"),
                linkTo(methodOn(RepositoryMetadataController.class).listRepositories()).withRel("repositories")
        );
    }
}
