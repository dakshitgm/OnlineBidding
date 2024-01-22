package com.dakshit.OnlineBidding.Services.Impl;


import com.dakshit.OnlineBidding.Entity.Product;
import com.dakshit.OnlineBidding.Services.ProductService;
import com.dakshit.OnlineBidding.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(long id) throws Exception{
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent())
            return productOptional.get();

        // ToDo: Make exception class to throw exception product not exist
        throw new Exception("product not exist");
    }

    @Override
    public Product updateProduct(long id, Product product) {
        //TODO: Implements update product
        return null;
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}
