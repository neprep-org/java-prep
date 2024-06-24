package rw.pacis.ne.binarysupermarket.dtos;

import lombok.Data;
import rw.pacis.ne.binarysupermarket.enums.EProductQuantityOperation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PurchaseProductDTO {

    @NotBlank
    private String code;

    @NotNull
    private Integer quantity;
}