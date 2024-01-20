package com.dakshit.OnlineBidding.Services;

import com.dakshit.OnlineBidding.Entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public Product addProduct(Product product);
    public List<Product> getProductList();
    public Product getProduct(long id) throws Exception;
    public Product updateProduct(long id, Product product);
    public void deleteProduct(long id);
}
