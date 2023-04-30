package org.sdia.Billingservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="inventory-service")
interface InventoryServiceClient{

    @GetMapping("/products/{id}")
    Product findProductById(@PathVariable("id") Long id);

    @GetMapping("/products?projection=fullProduct")
    PagedModel<Product> findAll();

    @GetMapping(path="/products")
    PagedModel <Product> pageProducts();
    /*
    @GetMapping(path="/products/{id}")
    Product getProductById(@PathVariable Long id);
    */





}
