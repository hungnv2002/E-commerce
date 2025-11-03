package com.product_service.controller;

import com.product_service.core.BaseResponse;
import com.product_service.dto.request.CreateBrandInforRequest;
import com.product_service.dto.request.UpdateBrandInforRequest;
import com.product_service.dto.response.CreateBrandInforResponse;
import com.product_service.dto.response.UpdateBrandInforResponse;
import com.product_service.exception.ECListException;
import com.product_service.service.BrandService;
import com.product_service.utils.EcommerceErrorTemplate;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product/brand")
@Tag(name = "Brand Controller", description = "APIs for managing brands")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }
    @PostMapping("/create")
    public BaseResponse<?>postCreateBrandInfo(@Valid @RequestBody CreateBrandInforRequest request, HttpServletRequest httpServletRequest, BindingResult bindingResult) throws Exception{
        if (bindingResult.hasErrors()){
            throw new ECListException(EcommerceErrorTemplate.ERROR_REQUEST_LIST.getCode(),EcommerceErrorTemplate.ERROR_REQUEST_LIST.getMessage(), bindingResult.getAllErrors());
        }
        CreateBrandInforResponse response= brandService.createBrandInfo(request,httpServletRequest);
        return new BaseResponse<>(response);
    }
    @PutMapping("/update")
    public BaseResponse<?>putUpdateBrandInfo(@Valid @RequestBody UpdateBrandInforRequest request, HttpServletRequest httpServletRequest, BindingResult bindingResult) throws Exception{
        if (bindingResult.hasErrors()){
            throw new ECListException(EcommerceErrorTemplate.ERROR_REQUEST_LIST.getCode(),EcommerceErrorTemplate.ERROR_REQUEST_LIST.getMessage(), bindingResult.getAllErrors());
        }
        UpdateBrandInforResponse response= brandService.updateBrandInfo(request,httpServletRequest);
        return new BaseResponse<>(response);
    }
}
