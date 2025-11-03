package com.product_service.service;

import com.product_service.dto.request.CreateBrandInforRequest;
import com.product_service.dto.request.UpdateBrandInforRequest;
import com.product_service.dto.response.CreateBrandInforResponse;
import com.product_service.dto.response.UpdateBrandInforResponse;
import com.product_service.exception.ECException;
import jakarta.servlet.http.HttpServletRequest;

public interface BrandService {
    CreateBrandInforResponse createBrandInfo(CreateBrandInforRequest request, HttpServletRequest httpServletRequest) throws ECException;
    UpdateBrandInforResponse updateBrandInfo(UpdateBrandInforRequest request, HttpServletRequest httpServletRequest) throws ECException;
    void deleteBrandInfo (Long id) throws ECException;
}
