package rw.pacis.ne.binarysupermarket.serviceImpls;

import org.springframework.stereotype.Service;
import rw.pacis.ne.binarysupermarket.dtos.NewProductQuantityDTO;
import rw.pacis.ne.binarysupermarket.enums.EProductQuantityOperation;
import rw.pacis.ne.binarysupermarket.exceptions.BadRequestException;
import rw.pacis.ne.binarysupermarket.models.Product;
import rw.pacis.ne.binarysupermarket.models.ProductQuantity;
import rw.pacis.ne.binarysupermarket.repositories.IProductQuantityRepository;
import rw.pacis.ne.binarysupermarket.services.IProductQuantityService;
import rw.pacis.ne.binarysupermarket.services.IProductService;

import java.util.List;

@Service
public class ProductQuantityServiceImpl implements IProductQuantityService {

    private final IProductService productService;

    private final IProductQuantityRepository repository;

    public ProductQuantityServiceImpl(IProductService productService, IProductQuantityRepository repository) {
        this.productService = productService;
        this.repository = repository;
    }

    @Override
    public Integer byProduct(String productCode) {
        Product product = productService.findByCode(productCode);

        List<ProductQuantity> productQuantities = repository.findByProduct(product);
        int totalQuantity = 0;
        for (ProductQuantity productQuantity : productQuantities) {
            if (productQuantity.getOperation() == EProductQuantityOperation.IN) {
                totalQuantity += productQuantity.getQuantity();
            } else if (productQuantity.getOperation() == EProductQuantityOperation.OUT) {
                totalQuantity -= productQuantity.getQuantity();
            }
        }

        return totalQuantity;
    }

    @Override
    public ProductQuantity newQuantity(NewProductQuantityDTO dto) {

        if(dto.getOperation() == EProductQuantityOperation.OUT){
            Integer quantity = byProduct(dto.getCode());
            if(quantity - dto.getQuantity() < 0)
                throw new BadRequestException("The quantity you provided is greater than the quantity in store");
        }

        Product product = productService.findByCode(dto.getCode());



        ProductQuantity productQuantity = new ProductQuantity(dto,product);

        return repository.save(productQuantity);
    }
}
