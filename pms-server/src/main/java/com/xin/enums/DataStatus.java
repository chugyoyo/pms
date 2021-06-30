package com.xin.enums;

import lombok.Getter;

public enum DataStatus {
    /**
     * 正常
     */
    NORMAL(1, "正常"),
    /**
     * 删除
     */
    DELETE(2, "删除");

    @Getter
    private final int code;
    @Getter
    private final String text;

    DataStatus(int c, String txt) {
        code = c;
        text = txt;
    }

}
