package rw.pacis.ne.binarysupermarket.serviceImpls;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rw.pacis.ne.binarysupermarket.dtos.NewProductQuantityDTO;
import rw.pacis.ne.binarysupermarket.dtos.PurchaseProductDTO;
import rw.pacis.ne.binarysupermarket.enums.EProductQuantityOperation;
import rw.pacis.ne.binarysupermarket.models.ProductPurchased;
import rw.pacis.ne.binarysupermarket.models.ProductQuantity;
import rw.pacis.ne.binarysupermarket.models.User;
import rw.pacis.ne.binarysupermarket.repositories.IProductPurchasedRepository;
import rw.pacis.ne.binarysupermarket.services.IProductPurchasedService;
import rw.pacis.ne.binarysupermarket.services.IProductQuantityService;
import rw.pacis.ne.binarysupermarket.services.IUserService;

import java.util.List;

@Service
public class ProductPurchasedServiceImpl implements IProductPurchasedService {

    private final IProductPurchasedRepository repository;

    private final IUserService service;

    private final IProductQuantityService productQuantityService;

    public ProductPurchasedServiceImpl(IProductPurchasedRepository repository, IUserService service, IProductQuantityService productQuantityService) {
        this.repository = repository;
        this.service = service;
        this.productQuantityService = productQuantityService;
    }

    @Override
    public List<ProductPurchased> byLoggedInCustomer() {
        User customer = service.getLoggedInUser();
        return repository.findByCustomer(customer);
    }

    @Override
    public List<ProductPurchased> all() {
        return repository.findAll();
    }

    @Override
    public Page<ProductPurchased> allPaginated(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductPurchased purchase(PurchaseProductDTO dto) {
        User customer = service.getLoggedInUser();

        NewProductQuantityDTO newProductQuantityDTO = new NewProductQuantityDTO(dto.getCode(),dto.getQuantity(), EProductQuantityOperation.OUT);

        ProductQuantity productQuantity = productQuantityService.newQuantity(newProductQuantityDTO);

        ProductPurchased productPurchased = new ProductPurchased(productQuantity,customer);

        return repository.save(productPurchased);
    }
}
