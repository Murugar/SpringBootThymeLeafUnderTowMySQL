package com.iqmsoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static org.springframework.data.domain.Sort.Direction.ASC;


@Controller
public class MainController {
    
    private static final int PAGE_SIZE = 5;
    private static final String SORT = "name";
    
    private final UserRepo repo;
    
    @Autowired
    public MainController(UserRepo repo) {
        this.repo = repo;
    }
    
    @GetMapping("/")
    public String users(
            @RequestParam(value = "q", required = false) String query,
            Model model,
            @PageableDefault(size = PAGE_SIZE, sort = SORT, direction = ASC) Pageable pageable
    ) {
        Page<User> users = repo.findByNameOrEmail((query != null) ? query : "", pageable);
        model.addAttribute("users", users);
        model.addAttribute("query", query);

        model.addAttribute("totalPages", users.getTotalPages());
        model.addAttribute("current", pageable.getPageNumber());
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());

        return "users";
    }
    
    @GetMapping("/user/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("user", repo.findOne(id));
        return "edit";
    }
    
    @PostMapping("/user")
    public String save(@ModelAttribute User user) {
        repo.save(user);
        try {
            return "redirect:/?q=" + URLEncoder.encode(user.getName(), "UTF8");
        } catch (UnsupportedEncodingException ignore) {
            return "redirect:/";
        }
    }
    
    @GetMapping("/user/delete/{id}")
    public String delete(@PathVariable Integer id) {
        repo.delete(id);
        return "redirect:/";
    }
    
    @GetMapping("/user")
    public String create(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "edit";
    }
}
