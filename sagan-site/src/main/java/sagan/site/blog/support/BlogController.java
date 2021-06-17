package sagan.site.blog.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sagan.site.blog.BlogService;
import sagan.site.blog.Post;
import sagan.site.blog.PostCategory;
import sagan.site.support.DateFactory;
import sagan.site.support.nav.Navigation;
import sagan.site.support.nav.PageableFactory;
import sagan.site.support.nav.PaginationInfo;
import sagan.site.support.nav.Section;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/blog")
@Navigation(Section.BLOG)
public class BlogController {

    private static final DateTimeFormatter DAY_FORMATTER = DateTimeFormatter.ofPattern("MMMM dd, yyyy");

    private static final DateTimeFormatter MONTH_FORMATTER = DateTimeFormatter.ofPattern("MMMM yyyy");

    private final String ALL_POSTS_CATEGORY = "All Posts";
    private final String BROADCASTS_CATEGORY = "Broadcasts";

    private final BlogService blogService;
    private final DateFactory dateFactory;

    @Autowired
    public BlogController(BlogService blogService, DateFactory dateFactory) {
        this.blogService = blogService;
        this.dateFactory = dateFactory;
    }

    @GetMapping("")
    public String listPublishedPosts(Model model, @RequestParam(defaultValue = "1") int page) {
        Pageable pageRequest = PageableFactory.forLists(page);
        Page<Post> result = blogService.getPublishedPosts(pageRequest);
        return renderListOfPosts(result, model, ALL_POSTS_CATEGORY);
    }

    private String renderListOfPosts(Page<Post> page, Model model, String activeCategory) {
        Page<PostView> postViewPage = PostView.pageOf(page, dateFactory);
        List<PostView> posts = postViewPage.getContent();
        if (page.isFirst() && ALL_POSTS_CATEGORY.equals(activeCategory)) {
            List<PostView> recentPosts = new ArrayList<>(posts);
            model.addAttribute("newestPost", recentPosts.remove(0));
            model.addAttribute("posts", recentPosts);
        }
        else {
            model.addAttribute("posts", posts);
        }

        model.addAttribute("activeCategory", activeCategory);
        model.addAttribute("categories", PostCategory.values());
        model.addAttribute("paginationInfo", new PaginationInfo(postViewPage));
        model.addAttribute("disqusShortname", blogService.getDisqusShortname());
        return "blog/index";
    }
}
