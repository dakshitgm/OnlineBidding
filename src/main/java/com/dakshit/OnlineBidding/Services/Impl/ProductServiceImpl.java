package com.dakshit.OnlineBidding.Services.Impl;


import com.dakshit.OnlineBidding.Entity.Product;
import com.dakshit.OnlineBidding.Entity.ProductImage;
import com.dakshit.OnlineBidding.Entity.User;
import com.dakshit.OnlineBidding.Exception.ProductNotFoundException;
import com.dakshit.OnlineBidding.Exception.UnauthorisedAccessException;
import com.dakshit.OnlineBidding.Services.ProductService;
import com.dakshit.OnlineBidding.repository.ProductImageRepository;
import com.dakshit.OnlineBidding.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getUserProductList(long userId){
        return productRepository.findBySellerId(userId);
    }

    @Override
    public Product getProduct(long id) throws ProductNotFoundException{
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent())
            return productOptional.get();
        throw new ProductNotFoundException("product not found with this id " + id);
    }

    @Override
    public void updateProduct(long id, Product body, long userId) throws ProductNotFoundException, UnauthorisedAccessException {
        Optional productOptional = productRepository.findById(id);

        if(!productOptional.isPresent())
            throw new ProductNotFoundException("product not found with this id " + id);


        Product product = (Product) productOptional.get();

        if(product.getSeller().getId() != userId){

            throw new UnauthorisedAccessException("you cannot change this product details");
        }

        product.setName(body.getName());
        product.setDescription(body.getDescription());

        productRepository.save(product);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }


    @Override
    public void saveImage(long productId, MultipartFile image) {
        try {
            ProductImage productImage = new ProductImage();
            productImage.setProductId(productId);
            productImage.setImage(image.getBytes());
            productImageRepository.save(productImage);
        } catch (IOException ioException){
            System.out.println(ioException.toString());
        }
    }

    @Override
    public byte[] getImage(long productId) {
       ProductImage productImage = productImageRepository.findByProductId(productId);
       return productImage.getImage();
    }
}
