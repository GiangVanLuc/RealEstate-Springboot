package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public enum StatusCode {
    CHUA_XU_LY("Chưa xử lý"),
    DANG_XU_LY("Đang xử lý"),
    DA_XU_LY("Đã xử lý"),
    HUY("Hủy");

    private final String statusName;

    StatusCode(String statusName) {
        this.statusName = statusName;
    }

    public static Map<String, String> getStatusCode() {
        Map<String, String> statusCodes = new LinkedHashMap<>();
        for (StatusCode it : StatusCode.values()) {
            statusCodes.put(it.toString(), it.statusName);
        }
        return statusCodes;
    }
}
