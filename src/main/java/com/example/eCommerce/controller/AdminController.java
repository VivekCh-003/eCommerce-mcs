package com.example.eCommerce.controller;

import com.example.eCommerce.dto.ProductDto;
import com.example.eCommerce.entity.Category;
import com.example.eCommerce.entity.Product;
import com.example.eCommerce.service.CategoryService;
import com.example.eCommerce.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    public String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }


    @GetMapping("/admin/categories")
    public String getCatergories(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String getaddCategories(Model model){
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String postaddCategories(@ModelAttribute("category")Category category){
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable("id")int id){
        categoryService.deleteById(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable("id")int id,Model model){
        Optional<Category> category = categoryService.getCategoryById(id);
        if(category.isPresent()){
            model.addAttribute("category",category.get());
        }
        return "categoriesAdd";
    }


    // ------- Product Section -------

    @GetMapping("/admin/products")
    public String getProducts(Model model){
        List<Product> productList = productService.findAll();
        model.addAttribute("products",productList);
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String addProductsGet(Model model){
        model.addAttribute("productDTO",new ProductDto());
        model.addAttribute("categories",categoryService.getAllCategory());
        return "productsAdd";
    }

    @PostMapping("/admin/products/add")
    public String addProductPost(@ModelAttribute("productDTO")ProductDto productDto,
                                 @RequestParam("productImage")MultipartFile file,
                                 @RequestParam("imgName") String imgName) throws IOException {

        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setCategory(categoryService.getCategoryById(productDto.getCategoryId()).get());
        product.setPrice(productDto.getPrice());
        product.setWeight(productDto.getWeight());
        product.setDescription(productDto.getDescription());
        String imageUUID;
        if(!file.isEmpty()){
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir,imageUUID);
            Files.write(fileNameAndPath,file.getBytes());
        }else{
            imageUUID = imgName;
        }
        product.setImageName(imageUUID);
        productService.save(product);

        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable long id){
        productService.deleteById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String update(@PathVariable long id,Model model){
        Product product = productService.findById(id).get();
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product,productDto);

//        productDto.setId(product.getId());
//        productDto.setName(product.getName());
//        productDto.setCategoryId(product.getCategory().getId());
//        productDto.setPrice(product.getPrice());
//        productDto.setWeight(product.getWeight());
//        productDto.setDescription(product.getDescription());
//        productDto.setImageName(product.getImageName());


        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("productDTO",productDto);

        return "productsAdd";

    }

}
