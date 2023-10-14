package com.example.eCommerce.controller;

import com.example.eCommerce.entity.Product;
import com.example.eCommerce.global.GlobalData;
import com.example.eCommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {

    @Autowired
    private ProductService productService;


    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id){
        GlobalData.cart.add(productService.findById(id).get());
        return "redirect:/shop";
    }

    @GetMapping("/cart")
    public String cart(Model model){
        model.addAttribute("cartCount",GlobalData.cart.size());
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart",GlobalData.cart);

        return "cart";
    }

    @GetMapping("/cart/removeItem/{id}")
    public String removeItem(@PathVariable int id){
        GlobalData.cart.remove(id);

        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model){
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());

        return "checkout";
    }
}
