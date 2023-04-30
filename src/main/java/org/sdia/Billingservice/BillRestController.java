package org.sdia.Billingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RestController
class BillRestController{

    @Autowired
    private BillRepository billRepository;
    @Autowired private ProductItemRepository productItemRepository;
    @Autowired private CustomerServiceClient customerServiceClient;
    @Autowired private InventoryServiceClient inventoryServiceClient;
    @GetMapping("/bills/full/{id}")
    public Bill getBill(@PathVariable(name="id") Long id){
        Bill bill=billRepository.findById(id).get();
        Customer customer=customerServiceClient.findCustomerById(bill.getCustomerID());
        bill.setCustomer(customer);
        bill.getProductItems().forEach(pi->{
            System.out.println(pi.getProductID()+" "+pi.getPrice());
            Product product=inventoryServiceClient.findProductById(pi.getProductID());
            pi.setProduct(product);
        });
        return bill; }

}