package sagan.site.tools.support;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sagan.site.tools.SpringToolsPlatformRepository;

@Controller
@RequestMapping("/tools")
public class SpringToolsController {

    private final SpringToolsPlatformRepository repository;

    public SpringToolsController(SpringToolsPlatformRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String listDownloads(Model model) {
        this.repository.findAll().forEach(platform -> {
            model.addAttribute(platform.getId(), platform);
        });

        return "tools/list";
    }
}
