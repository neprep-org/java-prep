package rw.pacis.ne.binarysupermarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.pacis.ne.binarysupermarket.models.ProductPurchased;
import rw.pacis.ne.binarysupermarket.models.ProductQuantity;
import rw.pacis.ne.binarysupermarket.models.User;

import java.util.List;
import java.util.UUID;

public interface IProductPurchasedRepository extends JpaRepository<ProductPurchased, UUID> {
    List<ProductPurchased> findByCustomer(User customer);
}
