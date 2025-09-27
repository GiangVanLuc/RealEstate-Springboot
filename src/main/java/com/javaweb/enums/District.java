package com.javaweb.enums;

import java.util.Map;
import java.util.TreeMap;

public enum District {
    QUAN_1("Quận 1"),
    QUAN_2("Quận 2"),
    QUAN_3("Quận 3"),
    QUAN_4("Quận 4"),
    QUAN_5("Quận 5"),
    QUAN_8("Quận 8"),
    QUAN_9("Quận 9"),
    QUAN_10("Quận 10"),
    QUAN_11("Quận 11"),
    QUAN_HOAN_KIEM("Quận Hoàn Kiếm"),
    QUAN_HA_DONG("Quận Hà Đông"),
    QUAN_THANH_XUAN("Quận Thanh Xuân");

    private final String districtName;

    District(String districtName) {
        this.districtName = districtName;
    }

    public static Map<String, String> type() {
        Map<String, String> districts = new TreeMap<>();
        for (District it : District.values()) {
            districts.put(it.toString(), it.districtName);
        }
        return districts;
    }
}
