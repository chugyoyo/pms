package com.xin.enums;

import lombok.Getter;

/**
 * @author 吴泽欣
 * @since 2021/1/7 11:43
 */
public enum TaskStatus {
    /**
     * 未完成
     */
    UNFINISHED("UNFINISHED", "未完成"),

    /**
     * 已完成
     */
    FINISHED("FINISHED", "已完成"),

    /**
     * 逾期已完成
     */
    OVERDUE_FINISHED("OVERDUE_FINISHED","逾期已完成"),

    /**
     * 逾期未完成
     */
    OVERDUE_UNFINISHED("OVERDUE_UNFINISHED","逾期未完成");


    @Getter
    private final String code;
    @Getter
    private final String text;

    TaskStatus(String c, String txt) {
        code = c;
        text = txt;
    }
}
