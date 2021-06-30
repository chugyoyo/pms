package com.xin.enums;

import lombok.Getter;

/**
 * @author 吴泽欣
 * @since 2021/1/8 22:50
 */
public enum  ClientStatus {
    /**
     * 正常
     */
    NORMAL("NORMAL", "正常"),
    /**
     * 禁用
     */
    BAN("BAN", "禁用"),
    /**
     * 已删除（暂时不会有该功能，只会禁掉用户）
     */
    DELETED("DELETED", "已删除");

    @Getter
    private final String code;
    @Getter
    private final String text;

    ClientStatus(String c, String txt) {
        code = c;
        text = txt;
    }
}
