package com.microservice.productservice.Controller;

import com.microservice.productservice.Entity.Product;
import com.microservice.productservice.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @GetMapping("")
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    @PostMapping("/list")
    public List<Product> getProductsByList(@RequestBody List<Long> list){
        return productService.getListOfProducts(list);
    }


}
