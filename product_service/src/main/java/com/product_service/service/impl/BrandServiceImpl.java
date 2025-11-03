package com.product_service.service.impl;

import com.product_service.dto.request.CreateBrandInforRequest;
import com.product_service.dto.request.UpdateBrandInforRequest;
import com.product_service.dto.response.CreateBrandInforResponse;
import com.product_service.dto.response.UpdateBrandInforResponse;
import com.product_service.entity.Brand;
import com.product_service.exception.ECException;
import com.product_service.repository.BrandRepository;
import com.product_service.service.BrandService;
import com.product_service.utils.EcommerceErrorTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Optional;

@Service

public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CreateBrandInforResponse createBrandInfo(CreateBrandInforRequest request, HttpServletRequest httpServletRequest) throws ECException {
        Optional<Brand> optionalBrand= brandRepository.findByName(request.getName());
        if (optionalBrand.isPresent()){
            throw  new ECException(HttpStatus.BAD_REQUEST.value(), EcommerceErrorTemplate.DATA_EXISTED.getCode(),"Brand Name Existed");
        }
        if(StringUtils.isBlank(request.getName())){
            throw new ECException(HttpStatus.BAD_REQUEST.value(), EcommerceErrorTemplate.DATA_NOT_FOUND.getCode(), "Brand Name Can Not Found");
        }
        if (StringUtils.isBlank(request.getDescription())){
            throw new ECException(HttpStatus.BAD_REQUEST.value(), EcommerceErrorTemplate.DATA_NOT_FOUND.getCode(), "Description Can Not Found");
        }
        Brand brand= new Brand();
        brand.setName(request.getName());
        brand.setDescription(request.getDescription());
        brand.setActive(true);
        brandRepository.save(brand);
        return modelMapper.map(brand, CreateBrandInforResponse.class);
    }

    @Override
    public UpdateBrandInforResponse updateBrandInfo(UpdateBrandInforRequest request, HttpServletRequest httpServletRequest) throws ECException {
        if (request.getId()==null){
            throw new ECException(HttpStatus.BAD_REQUEST.value(), EcommerceErrorTemplate.DATA_NOT_FOUND.getCode(),"Brand Id Can Not Found" );
        }
        Brand brand = brandRepository.findById(request.getId())
                .orElseThrow(() -> new ECException(HttpStatus.BAD_REQUEST.value(), EcommerceErrorTemplate.DATA_NOT_FOUND.getCode(), "Brand Not Exist"));
        if (request.getName()!=null){
            brand.setName(request.getName());
        }
        if(request.getDescription()!=null){
            brand.setDescription(request.getDescription());
        }
        brand.setUpdatedAt(LocalDateTime.now());
        brandRepository.save(brand);
        return modelMapper.map(brand, UpdateBrandInforResponse.class);
    }

    @Override
    public void deleteBrandInfo(Long id) throws ECException {
        if(!brandRepository.existsById(id)){
            throw new ECException(HttpStatus.BAD_REQUEST.value(), EcommerceErrorTemplate.DATA_NOT_FOUND.getCode(),"Brand not exist");
        }

        brandRepository.deleteById(id);
    }
}
