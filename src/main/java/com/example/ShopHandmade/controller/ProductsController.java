package com.example.ShopHandmade.controller;

import com.example.ShopHandmade.service.CategoryService;
import com.example.ShopHandmade.service.ProductService;
import com.example.ShopHandmade.service.CategoryService.CategoryDTO;
import com.example.ShopHandmade.service.response.GetDetailProductResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ProductsController {
    private final CategoryService categoryService;
    private final ProductService productService;

    public ProductsController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/products")
    public String productsView(@RequestParam(defaultValue = "1") int categoryId, Model model) {
        // Lấy danh sách danh mục
        List<CategoryDTO> listCategories = categoryService.getCategories();

        // Gọi API lấy danh sách sản phẩm theo categoryId
        List<Map<String, Object>> listProducts = productService.getProducts(categoryId, 0, 10).getContent().stream()
                .map(product -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", product.getId());
                    map.put("name", product.getName());
                    map.put("description", product.getDescription());
                    map.put("price", product.getPrice());
                    map.put("images", product.getImages());
                    return map;
                }).collect(Collectors.toList());

        model.addAttribute("categories", listCategories);
        model.addAttribute("products", listProducts);
        // model.addAttribute("selectedCategory", categoryId); // Lưu ID danh mục đang chọn

        return "product";
    }
    @GetMapping("/products/detail/{id}")
    public String detailProductView(@PathVariable("id") int id, Model model) {
        GetDetailProductResponse detailProductResponse = this.productService.getProductById(id);
        model.addAttribute("product", detailProductResponse);
        return "detailProduct";
    }

}
