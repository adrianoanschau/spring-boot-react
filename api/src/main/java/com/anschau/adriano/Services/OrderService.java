package com.anschau.adriano.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    
	public List<OrderEntity> findAll() throws Exception {
        return this.orderRepository.findAll();
	}

    public OrderEntity create(List<CreateProductDTO> productsList) throws Exception {
        OrderEntity order = new OrderEntity();
        List<ProductEntity> products = new ArrayList();

        for (CreateProductDTO productDto : productsList) {
            ProductEntity product = new ProductEntity();
            product.setName(productDto.getName());
            product.setExternalId(productDto.getId());
            product.setPrice(productDto.getPrice());
            product.setQuantity(productDto.getQuantity());
            product.setOrder(order);
            
            products.add(product);
        }

        order.setProducts(products);

        return this.orderRepository.saveAndFlush(order);
    }

    public Optional<OrderEntity> findOne(UUID id) throws Exception {
        return this.orderRepository.findById(id);
    }

    public boolean delete(UUID id) throws Exception {
        try {
            this.orderRepository.deleteById(id);

            return true;
            
        } catch (Exception e) {
            return false;
        }
    }
    
}
