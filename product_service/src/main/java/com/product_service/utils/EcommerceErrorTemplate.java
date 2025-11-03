package com.product_service.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

public enum EcommerceErrorTemplate {
    BAD_REQUEST("EC-PRODUCT-002", "BAD_REQUEST!"),
    DATA_EXISTED("EC-PRODUCT-003","Dữ liệu tìm kiếm đã tồn tại!"),
    DATA_NOT_FOUND("EC-PRODUCT-004", "Dữ liệu tìm kiếm không tồn tại!"),
    STATUS_INVALID("EC-PRODUCT-005", "Trạng thái không hợp lệ"),
    ERROR_REQUEST_LIST("EC-PRODUCT-006", "Danh sách lỗi đầu vào!");
    private final String code;
    private final String message;

    EcommerceErrorTemplate(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
