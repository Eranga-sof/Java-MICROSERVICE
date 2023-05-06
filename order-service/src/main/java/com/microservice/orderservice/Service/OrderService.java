package com.microservice.orderservice.Service;

import com.microservice.orderservice.DTO.CartResponse;
import com.microservice.orderservice.DTO.Product;
import com.microservice.orderservice.Entity.Cart;
import com.microservice.orderservice.Entity.Items;
import com.microservice.orderservice.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Cart addOrder(Cart order) {
        return orderRepository.save(order);
    }

    public List<Cart> getAllOrders() {
        return orderRepository.findAll();
    }

    public CartResponse getCartById(Long id) {
        var cartResponse = new CartResponse();
        Optional<Cart> cart = orderRepository.findById(id);



        if (cart.isPresent()){

            //get list of products id
            List<Long> ids = getListOfId(cart.get());

            //invoke and get list of product
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<List<Long>> request =
                    new HttpEntity<List<Long>>(ids, headers);
            System.out.println(request);
            String url = "http://PRODUCT-SERVICE/v1/api/product/list";
            ParameterizedTypeReference<List<Product>> responseType = new ParameterizedTypeReference<List<Product>>() {};
            List<Product> products = restTemplate.exchange(url, HttpMethod.POST, request, responseType).getBody();
            System.out.println(products);

            List<Items> items = cart.get().getItems().stream()
                    .map(item -> {
                        Product product = products.stream()
                                .filter(p -> p.getId() == item.getProductId())
                                .findFirst()
                                .orElse(null);
                        if(product == null){
                            return item;
                        }else{
                            return  Items.builder()
                                    .id(item.getId())
                                    .quantity(item.getQuantity())
                                    .productId(item.getProductId())
                                    .product(Product.builder()
                                            .productName(product.getProductName())
                                            .productDescription(product.getProductDescription())
                                            .id(product.getId())
                                            .build())
                                    .build();
                        }
                    })
                    .collect(Collectors.toList());

            cartResponse = CartResponse.builder()
                    .createDate(cart.get().getCreateDate())
                    .modifyDate(cart.get().getModifyDate())
                    .items(items)
                    .build();
        }

        return cartResponse;
    }

    public List<Long> getListOfId(Cart cart){
        return  cart.getItems().stream()
                .map(c -> c.getProductId())
                .collect(Collectors.toList());
    }
}
