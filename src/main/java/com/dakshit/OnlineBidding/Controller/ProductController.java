package com.dakshit.OnlineBidding.Controller;


import com.dakshit.OnlineBidding.Entity.Product;
import com.dakshit.OnlineBidding.Exception.ProductNotFoundException;
import com.dakshit.OnlineBidding.Exception.UnauthorisedAccessException;
import com.dakshit.OnlineBidding.Payload.Request.ProductDTO;
import com.dakshit.OnlineBidding.Payload.Response.ProductViewDTO;
import com.dakshit.OnlineBidding.Services.ProductService;
import com.dakshit.OnlineBidding.Utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserUtils userUtils;

    @PostMapping("")
    public ResponseEntity<?>  addProduct(@RequestBody ProductDTO productDTO){
        Product product = new Product();

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setSellerId(userUtils.getLoggedInUserId());

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

    @GetMapping("/myProducts")
    public List<ProductViewDTO> getUserProductList(){
        List<Product> productList = productService.getUserProductList(userUtils.getLoggedInUserId());
        List<ProductViewDTO> productViewDTOList = productList
                .stream()
                .map(ProductViewDTO::new)
                .collect(Collectors.toList());
        return productViewDTOList;
    }

    @GetMapping("/{id}")
    public ProductViewDTO getProduct(@PathVariable("id") long id) throws ProductNotFoundException {
        Product product = productService.getProduct(id);
        return new ProductViewDTO(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathParam("id") long id, ProductDTO productDTO) throws ProductNotFoundException, UnauthorisedAccessException {
        Product product = new Product();

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());

        productService.updateProduct(id, product, userUtils.getLoggedInUserId());

        return ResponseEntity.ok("product deleted Successfully");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") long id){
        productService.deleteProduct(id);

        return ResponseEntity.ok("product deleted successfully");
    }



}
