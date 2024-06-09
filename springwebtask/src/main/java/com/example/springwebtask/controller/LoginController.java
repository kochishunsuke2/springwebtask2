package com.example.springwebtask.controller;

import com.example.springwebtask.entity.Detail;
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

    @GetMapping("index.html")
    public String index(@ModelAttribute("loginForm") Entity entity) {
        return "index";
    }

    @PostMapping("index.html")
    public String login(@Validated @ModelAttribute("loginForm")  Entity entity, BindingResult bindingResult,Model model) {
        // バリデーション
        if (bindingResult.hasErrors()) {
            return "index";
        }
        Entity product = productService.findByLogin(entity.login_id(),entity.password());
        if (product != null && product.role() ==1) {
            session.setAttribute("product", product);
            model.addAttribute("products", productService.findAll());
            return "menu";
        }
        if (product != null && product.role() ==2) {
            session.setAttribute("product", product);
            model.addAttribute("products", productService.findAll());
            return "menu2";
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

    @GetMapping("/menu2.html")
    public String productList2(Model model) {
        model.addAttribute("products", productService.findAll());
        return "menu2";
    }

    @GetMapping("/menu2")
    public String menu2(@RequestParam(name="name") @PathVariable String name, Model model) {

        if (name != null && !name.trim().isEmpty()) {
            model.addAttribute("products", productService.search(name));
        } else {
            model.addAttribute("products", productService.findAll());
        }
        return "menu2";
    }

    @GetMapping("/insert")
    public String insert1(Model model, @ModelAttribute("productForm") NewName stationery) {
        model.addAttribute("product", new NewName(null,"","",null,null,""));
        model.addAttribute("products", productService.findAll2());
        return "insert";

    }

    @PostMapping("/insert")
    public String insert(@Validated @ModelAttribute("productForm") NewName stationery , BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("products", productService.findAll2());
            return "insert";
        }
        NewName product = productService.findByProductId(stationery.product_id());
        if (product != null) {
            //session.setAttribute("message", "商品IDが重複しています");
            model.addAttribute("errorMsg", "商品IDが重複しています");
            model.addAttribute("products", productService.findAll2());
            return "insert";
        }
        productService.insert(stationery);
//        return "redirect:/menu.html";
        return "success";
    }

    @GetMapping("/detail/{id}")
    public String productDetail(@PathVariable int id, Model model) {
        Detail product = productService.findById(id);
        var category = productService.findByCategory(product.category_id());
        model.addAttribute("category", category);
        model.addAttribute("product", product);
        return "detail";
    }

    @PostMapping("/detail")
    public String productDelete(@ModelAttribute("product") Menu delete) {
        productService.delete(delete.id());
        return "success";
    }

    @GetMapping("/detail/update/{id}")
    public String productUpdateForm(@PathVariable int id, Model model, @ModelAttribute("product") NewName change) {
      //  @ModelAttribute("productForm")
        var product = productService.findById(id);
//        model.addAttribute("product_id",product.product_id());
        model.addAttribute("product", product);
        model.addAttribute("categoryList", productService.findAll2());
        return "updateInput";
    }

    @PostMapping("/update")
    public String productUpdate( @Validated @ModelAttribute("product") NewName change, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categoryList", productService.findAll2());
            return "updateInput";
        }
        NewName product = productService.findByProductId(change.product_id());
        if (product != null && !(product.id().equals(change.id()))) {
           // session.setAttribute("message", "商品IDが重複しています");
            model.addAttribute("errorMsg", "商品IDが重複しています");
            model.addAttribute("categoryList", productService.findAll2());
            return "updateInput";
        }
        productService.update(change);
        return "success";
    }

    @GetMapping("/logout.html")
    public String logout(@ModelAttribute("loginForm") Entity entity) {
        session.invalidate();
        return "logout";
    }

    @GetMapping("/detail2/{id}")
    public String productDetail2(@PathVariable int id, Model model) {
        Detail product = productService.findById(id);
        var category = productService.findByCategory(product.category_id());
        model.addAttribute("category", category);
        model.addAttribute("product", product);
        return "detail2";
    }

}
