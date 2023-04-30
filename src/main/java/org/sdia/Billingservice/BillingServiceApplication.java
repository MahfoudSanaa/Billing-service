package org.sdia.Billingservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;
import java.util.Date;
import java.util.Random;
@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {
	public static void main(String[] args) {SpringApplication.run(BillingServiceApplication.class, args); }
	@Bean
	CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository,
							InventoryServiceClient inventoryServiceClient, CustomerServiceClient customerServiceClient){

		return args -> {

			Customer customer=customerServiceClient.findCustomerById(1L);
            Bill bill1=billRepository.save(new Bill(null,new Date(),null,customer.getId(),null));
			PagedModel<Product> productPageModel=inventoryServiceClient.pageProducts();

			productPageModel.forEach(p->{
				ProductItem productItem=new ProductItem();
				productItem.setPrice(p.getPrice());
				productItem.setQuantity(1+new Random().nextInt(100));
				productItem.setProductID(p.getId());
				System.out.println("----"+p.getId());
				productItem.setBill(bill1);
				//productItems.add(productItem);

				productItemRepository.save(productItem);
			});
		};
	}}