package com.example.Precept.Backend.APIs.modeller.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getPartner(){
        return productRepository.findAll();
    }


    public void addNewPartner(Product product) {

        Optional<Product> optionalStudent = productRepository.findPartnerByPartnerName(product.getProductName());
        if (optionalStudent.isPresent()){
            throw new IllegalStateException("partner name exist!");
        }
        productRepository.save(product);
    }

    public void deleteProduct(Long ProductID) {
        boolean exist = productRepository.existsById(ProductID);
        if (!exist) {
            throw new IllegalStateException("Product ID " + ProductID + " does not exist");
        }
        productRepository.deleteById(ProductID);
    }
}
