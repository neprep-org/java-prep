package rw.pacis.ne.binarysupermarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rw.pacis.ne.binarysupermarket.dtos.CreateOrUpdateProductDTO;
import rw.pacis.ne.binarysupermarket.dtos.PurchaseProductDTO;
import rw.pacis.ne.binarysupermarket.payload.ApiResponse;
import rw.pacis.ne.binarysupermarket.services.IProductPurchasedService;
import rw.pacis.ne.binarysupermarket.services.IProductService;
import rw.pacis.ne.binarysupermarket.utils.Constants;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product-purchased")
@CrossOrigin
public class ProductPurchasedController {
    private final IProductPurchasedService service;


    @Autowired
    public ProductPurchasedController(IProductPurchasedService service) {
        this.service = service;
    }

    @GetMapping("/logged-in-user")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseEntity<ApiResponse> getAllByLoggedInCustomer() {
        return ResponseEntity.ok(ApiResponse.success(service.byLoggedInCustomer()));
    }

    @GetMapping("/all/paginated")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ApiResponse> getAll(
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "limit", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit
    ) {
        Pageable pageable = PageRequest.of(page, limit, Sort.Direction.DESC, "id");
        return ResponseEntity.ok(ApiResponse.success(service.allPaginated(pageable)));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ApiResponse> getAll() {
        return ResponseEntity.ok(ApiResponse.success(service.all()));
    }


    @PostMapping
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseEntity<ApiResponse> purchase(@Valid @RequestBody PurchaseProductDTO dto) {
        return ResponseEntity.ok(ApiResponse.success(this.service.purchase(dto)));
    }
}
