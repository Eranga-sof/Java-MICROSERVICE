package com.microservice.productservice.Service;

import com.microservice.productservice.Entity.Product;
import com.microservice.productservice.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public List<Product> getListOfProducts(List<Long> list) {
        List<Product> productList = new ArrayList<>();
        for(Long l: list){
            Optional<Product> product = productRepository.findById(l);
            if(product.isPresent()){
                productList.add(product.get());
            }
        }
        System.out.println(productList);
        return productList;
    }
}
