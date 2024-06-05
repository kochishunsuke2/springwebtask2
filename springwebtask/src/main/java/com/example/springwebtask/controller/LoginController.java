package com.example.springwebtask.controller;

import com.example.springwebtask.entity.Entity;
import com.example.springwebtask.entity.Menu;
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
    public String insert1(Model model, @ModelAttribute("productForm") NewName stationery) {
        model.addAttribute("product", new NewName(null,"","",null,null,""));
        model.addAttribute("products", productService.findAll2());
        return "insert";

    }

    @PostMapping("/insert")
    public String insert(@Validated @ModelAttribute("productForm") NewName stationery, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("products", productService.findAll2());
            return "/insert";
        }
        productService.insert(stationery);
        return "redirect:/menu.html";
    }

    @GetMapping("/detail/{id}")
    public String productDetail(@PathVariable int id, Model model) {
        Menu product = productService.findById(id);
        model.addAttribute("product", product);
        return "detail";
    }

    @PostMapping("/detail")
    public String productDelete(@ModelAttribute("product") Menu delete) {
        productService.delete(delete.id());
        return "redirect:/menu.html";
    }

    @GetMapping("/detail/update/{id}")
    public String productUpdateForm(@PathVariable int id, Model model) {
        Menu product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("products", productService.findAll2());
        return "updateInput";
    }

    @PostMapping("/updateInput")
    public String productUpdate(@Validated @ModelAttribute("product") NewName change, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("products", productService.findAll2());
            return "updateInput";
        }
        productService.update(change);
        return "redirect:/menu.html";
    }
}
