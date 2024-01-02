package com.greenfox.reddit.controllers;

import com.greenfox.reddit.models.Post;
import com.greenfox.reddit.models.User;
import com.greenfox.reddit.services.AppService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
public class IndexController {
    private AppService appService;
    @Autowired
    public IndexController(AppService appService) {
        this.appService = appService;
    }

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request, @CookieValue(name = "user-id", defaultValue = "1") String userId) {
        model.addAttribute("crossroad", request.getRequestURI());
        model.addAttribute("loggedUser", appService.findUserById(Long.parseLong(userId)));
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        //model.addAttribute("loggedUser", appService.findUserById(Long.parseLong(userId)));
        model.addAttribute("posts", appService.findPosts());
        return "index";
    }

    @GetMapping("/posts")
    public String getSubmitPost(Model model) {
        model.addAttribute("post", new Post());
        return "index";
    }

    @PostMapping("/posts")
    public String submitPost(@ModelAttribute Post post, @ModelAttribute("loggedUser") User user) {
        appService.savePost(post, user.getId());
        return "redirect:/";
    }

    @PutMapping("/posts/{id}")
    public String modifyLikes(@PathVariable Long id, boolean likeIncrease) {
        appService.incrementLikes(id, likeIncrease);
        return "redirect:/";
    }

    @DeleteMapping("/posts/{id}")
    public String deletePost(@PathVariable Long id) {
        appService.deletePost(id);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "index";
    }

    @GetMapping("/submit-login")
    public String loginSubmit(String name, String password, HttpServletResponse response) {
        User logUser = appService.login(name, password);
        if (logUser != null) {
            response.addCookie(new Cookie("user-id", logUser.getId().toString()));
            return "redirect:/";
        }

        return "redirect:/login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "index";
    }

    @PostMapping("/signup")
    public String signup(String name, String password, String avatar) {
        appService.saveUser(name, password, avatar);
        return "redirect:/";
    }
}
