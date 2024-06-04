package com.example.springwebtask.controller;

import com.example.springwebtask.entity.Entity;
import com.example.springwebtask.entity.NewName;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.springwebtask.service.pgService;
@Controller
public class LoginController {

    @Autowired
    private HttpSession session;
    @Autowired
    pgService productService;

    @GetMapping("/login")
    public String index(@ModelAttribute("loginForm") Entity entity) {
        return "index";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginForm")  Entity entity, BindingResult bindingResult,Model model) {
        // バリデーション
        if (bindingResult.hasErrors()) {
            return "index";
        }
        Entity product = productService.findByLogin(entity.login_id(),entity.password());
        if (product != null) {
            session.setAttribute("product", product);
            model.addAttribute("products", productService.findAll());
            return "menu";
        }
        session.setAttribute("message", "IDまたはパスワードが不正です");
        return "index";
    }

    @GetMapping("/menu.html")
    public String productList(Model model) {
        model.addAttribute("products", productService.findAll());
        return "menu";
    }

    @GetMapping("/menu")
    public String menu(@RequestParam(name="name") @PathVariable String name, Model model) {

        if (name != null && !name.trim().isEmpty()) {
            model.addAttribute("products", productService.search(name));
        } else {
            model.addAttribute("products", productService.findAll());
        }
        return "menu";
    }

    @GetMapping("/insert")
    public String insert1(Model model, @ModelAttribute("insert") NewName stationery) {
        model.addAttribute("product", new NewName("","",null,null,""));
        return "insert";
    }

    @PostMapping("/insert")
    public String insert(@Validated @ModelAttribute("insert") NewName stationery, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/insert";
        }
        productService.insert(stationery);
        return "redirect:/menu.html";
    }

}
