package com.example.Precept.Backend.APIs.modeller.Product;

import com.example.Precept.Backend.APIs.exception.otherException.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/products")
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping("")
    public List<Product> getPartner() {
        return productService.getPartner();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productID)
            throws ResourceNotFoundException {
        Product product = productRepository.findById(productID)
                .orElseThrow(() -> new ResourceNotFoundException("customer not found for this id :: " + productID));
        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long productID,
                                                 @Valid @RequestBody Product productDetails) throws ResourceNotFoundException {
        Product product = productRepository.findById(productID)
                .orElseThrow(() -> new ResourceNotFoundException("customer not found for this id :: " + productID));

        product.setProductName(productDetails.getProductName());
        product.setPartner(productDetails.getPartner());
        product.setCommercial(productDetails.getCommercial());
        final Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @PostMapping("create")
    public void registerNewPartner(@RequestBody Product product) {
        productService.addNewPartner(product);
    }

    @DeleteMapping(path = "delete/{Id}")
    public void deletePartner(@PathVariable("Id") Long partnerID) {
        productService.deleteProduct(partnerID);
    }

}
