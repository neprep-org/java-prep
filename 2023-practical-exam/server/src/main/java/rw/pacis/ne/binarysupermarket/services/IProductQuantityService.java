package rw.pacis.ne.binarysupermarket.services;

import rw.pacis.ne.binarysupermarket.dtos.CreateOrUpdateProductDTO;
import rw.pacis.ne.binarysupermarket.dtos.NewProductQuantityDTO;
import rw.pacis.ne.binarysupermarket.models.Product;
import rw.pacis.ne.binarysupermarket.models.ProductQuantity;

import java.util.List;
import java.util.UUID;

public interface IProductQuantityService {

    Integer byProduct(String productCode);

    ProductQuantity newQuantity(NewProductQuantityDTO dto);
}
