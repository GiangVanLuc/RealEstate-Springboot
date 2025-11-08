package com.javaweb.enums;

import java.util.Map;
import java.util.TreeMap;

public enum CustomerType {
    POTENTIAL("Khách hàng tiềm năng"),
    NEGOTIATING("Đang giao dịch"),
    PURCHASED("Đã mua");

    private final String name;

    CustomerType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Map<String, String> type() {
        Map<String, String> customerTypes = new TreeMap<>();
        for (CustomerType it : CustomerType.values()) {
            customerTypes.put(it.toString(), it.name);
        }
        return customerTypes;
    }
}
