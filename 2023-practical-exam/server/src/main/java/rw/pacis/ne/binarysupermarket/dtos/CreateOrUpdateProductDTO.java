package rw.pacis.ne.binarysupermarket.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateOrUpdateProductDTO {

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @NotNull
    private Double price;

    @NotBlank
    private String image;
}
