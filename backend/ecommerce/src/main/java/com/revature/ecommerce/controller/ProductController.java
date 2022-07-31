package com.revature.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.revature.ecommerce.entity.Product;
import com.revature.ecommerce.entity.ProductSpecification;
import com.revature.ecommerce.exception.ItemExistsException;
import com.revature.ecommerce.service.ProductManagementService;
import com.revature.ecommerce.service.ProductSpecManagementService;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductManagementService productManagementService;

    @Autowired
    ProductSpecManagementService specManagementService;

    /**
     *  this method add list of product specification
     *  it invoke the method (addProductSpecs) of ProductSpecManagementService
      */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "spec/add")
    public List<ProductSpecification> addProductSpecs(@RequestBody List<ProductSpecification> specifications) {
        System.out.println(specifications);
        return  specManagementService.addProductSpec(specifications);
    }

    @GetMapping(path = "all")
    public List<Product> getProducts(){
        return productManagementService.getProducts();
    }

    @GetMapping(path = "getById/{productId}")
    public Product getProductByID(@PathVariable Integer productId) throws ItemExistsException {
        return productManagementService.getProductById(productId);
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public List<Product> getProductByRetailerId(@RequestParam("retailerId") Integer retailerId) {
        return productManagementService.getRetailerProducts(retailerId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "add")
    public Product addProduct(@RequestBody Product product) throws ItemExistsException {
        return productManagementService.saveProduct(product);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "update/{productId}")
    public Product updateProduct(@PathVariable Integer productId, @RequestBody Product product) throws ItemExistsException {
        return productManagementService.updateProduct(productId, product);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "delete/{productId}")
    public String deleteProduct(@PathVariable Integer productId) throws ItemExistsException {
        productManagementService.deleteProduct(productId);
        return "Deleted!";
    }



}


