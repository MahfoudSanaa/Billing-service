package org.sdia.Billingservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;
@RepositoryRestResource
interface ProductItemRepository extends
        JpaRepository<ProductItem,Long> {

    List<ProductItem> findByBillId(Long billID);

}
