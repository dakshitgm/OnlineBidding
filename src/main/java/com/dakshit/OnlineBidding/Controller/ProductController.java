package com.dakshit.OnlineBidding.Controller;


import com.dakshit.OnlineBidding.Entity.Product;
import com.dakshit.OnlineBidding.Payload.Request.ProductDTO;
import com.dakshit.OnlineBidding.Payload.Response.ProductViewDTO;
import com.dakshit.OnlineBidding.Services.ProductService;
import com.dakshit.OnlineBidding.Entity.User;
import com.dakshit.OnlineBidding.Utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserUtils userUtils;

    @PostMapping("/")
    public ResponseEntity<?>  addProduct(@RequestBody ProductDTO productDTO){
        Product product = new Product();

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setSeller(userUtils.getLoggedInUser());

        productService.addProduct(product);
        return ResponseEntity.ok("product added successfully");
    }

    @GetMapping("/")
    public List<ProductViewDTO> getProductList(){
        List<Product> productList =  productService.getProductList();
        List<ProductViewDTO> productViewDTOList = productList
                .stream()
                .map(ProductViewDTO::new)
                .collect(Collectors.toList());
        return productViewDTOList;
    }

    @GetMapping("/{id}")
    public ProductViewDTO getProduct(@PathVariable("id") long id) throws Exception{
        Product product = productService.getProduct(id);
        return new ProductViewDTO(product);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") long id){
        productService.deleteProduct(id);

        return ResponseEntity.ok("product deleted successfully");
    }



}
