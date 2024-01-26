package com.dakshit.OnlineBidding.Services;

import com.dakshit.OnlineBidding.Entity.Product;
import com.dakshit.OnlineBidding.Entity.User;
import com.dakshit.OnlineBidding.Exception.ProductNotFoundException;
import com.dakshit.OnlineBidding.Exception.UnauthorisedAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ProductService {
    public Product addProduct(Product product);
    public List<Product> getProductList();
    public Product getProduct(long id) throws ProductNotFoundException;
    public void updateProduct(long id, Product product, long userId) throws ProductNotFoundException, UnauthorisedAccessException;
    public List<Product> getUserProductList(long userId);
    public void deleteProduct(long id);

    public void saveImage(long productId, MultipartFile image);
    public byte[] getImage(long productId);
}
