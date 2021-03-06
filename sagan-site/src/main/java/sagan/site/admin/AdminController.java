package sagan.site.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller that handles requests for the root admin page. Administrative operations for
 * /blog, /team and /projects subsections are handled by their respective controllers.
 *
 * @see sagan.site.blog.support.BlogAdminController
 * @see sagan.site.team.support.TeamAdminController
 * @see sagan.site.projects.admin.ProjectAdminController
 */
@Controller
public class AdminController {

    @RequestMapping("/admin")
    public String adminPage() {
        return "admin/show";
    }
}
