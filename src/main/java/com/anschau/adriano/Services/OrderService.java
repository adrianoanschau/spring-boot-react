package com.anschau.adriano.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.anschau.adriano.DTO.CreateProductDTO;
import com.anschau.adriano.Entities.OrderEntity;
import com.anschau.adriano.Entities.ProductEntity;
import com.anschau.adriano.Repositories.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
	public List<OrderEntity> findAll() {
        return this.orderRepository.findAll();
	}

    public OrderEntity createOrderWithProducts(List<CreateProductDTO> productsList) {
        OrderEntity order = new OrderEntity();
        List<ProductEntity> products = new ArrayList();

        for (CreateProductDTO productDto : productsList) {
            ProductEntity product = new ProductEntity();
            product.setName(productDto.getName());
            product.setExternalId(productDto.getId());
            product.setOrder(order);
            
            products.add(product);
        }

        order.setProducts(products);

        return this.orderRepository.saveAndFlush(order);
    }
    
}
