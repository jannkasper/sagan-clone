package sagan.site.guides;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sagan.site.support.nav.Navigation;
import sagan.site.support.nav.Section;

import java.util.Arrays;

/**
 * Controller that handles requests for the index page for all guide docs at /guides.
 *
 * @see GettingStartedGuideController
 * @see TutorialController
 * @see TopicalController
 */
@Controller
@Navigation(Section.GUIDES)
class GuideIndexController {

	private final GettingStartedGuides gsGuides;

	private final Tutorials tutorials;

	private final Topicals topicals;

	@Autowired
	public GuideIndexController(GettingStartedGuides gsGuides, Tutorials tutorials, Topicals topicals) {
		this.gsGuides = gsGuides;
		this.tutorials = tutorials;
		this.topicals = topicals;
	}

	@GetMapping("/guides")
	public String viewIndex(Model model) {
		model.addAttribute("guides", Arrays.asList(gsGuides.findAll()));
		model.addAttribute("tutorials", Arrays.asList(tutorials.findAll()));
		model.addAttribute("topicals", Arrays.asList(topicals.findAll()));
		return "guides/index";
	}
}
