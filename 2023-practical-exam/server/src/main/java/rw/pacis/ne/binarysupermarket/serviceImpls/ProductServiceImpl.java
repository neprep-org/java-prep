package rw.pacis.ne.binarysupermarket.serviceImpls;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import rw.pacis.ne.binarysupermarket.dtos.CreateOrUpdateProductDTO;
import rw.pacis.ne.binarysupermarket.exceptions.BadRequestException;
import rw.pacis.ne.binarysupermarket.exceptions.ResourceNotFoundException;
import rw.pacis.ne.binarysupermarket.models.Product;
import rw.pacis.ne.binarysupermarket.repositories.IProductRepository;
import rw.pacis.ne.binarysupermarket.services.IProductQuantityService;
import rw.pacis.ne.binarysupermarket.services.IProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository repository;

    private final IProductQuantityService productQuantityService;

    public ProductServiceImpl(IProductRepository repository,@Lazy IProductQuantityService productQuantityService) {
        this.repository = repository;
        this.productQuantityService = productQuantityService;
    }

    @Override
    public Product findByCode(String code) {
        return repository.findById(code).orElseThrow(()->new ResourceNotFoundException("Product","code",code));
    }

    @Override
    public List<Product> all() {
        List<Product> products = repository.findAll();

        for (Product product : products) {
            int quantity = productQuantityService.byProduct(product.getCode());
            product.setQuantity(quantity);
        }

        return products;
    }


    @Override
    public Product register(CreateOrUpdateProductDTO dto) {

        Optional<Product> findByCode = repository.findById(dto.getCode());
        if(findByCode.isPresent()) throw new BadRequestException(String.format("Product with code '%s' already exists",dto.getCode()));

        Product product = new Product(dto);
        return repository.save(product);
    }
}
