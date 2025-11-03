package com.product_service.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateBrandInforRequest {
    private String name;
    private String description;

}
