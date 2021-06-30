package com.xin.enums;

import lombok.Getter;

/**
 * @author 吴泽欣
 * @since 2021/1/7 19:30
 */
public enum Role {
    /**
     * 游客
     */
    VISITOR("VISITOR", "游客"),
    /**
     * 超级管理员
     */
    ROOT("ROOT", "超级管理员"),

    /**
     * 文件管理员
     */
    FILE_ADMIN("FILE_ADMIN", "文件管理员"),

    /**
     * 评论管理员
     */
    COMMENT_ADMIN("COMMENT_ADMIN", "评论管理员"),

    /**
     * 用户信息管理员
     */
    CLIENT_ADMIN("CLIENT_ADMIN", "用户信息管理员");


    @Getter
    private final String roleCode;
    @Getter
    private final String roleName;

    Role(String roleCode, String roleName) {
        this.roleCode = roleCode;
        this.roleName = roleName;
    }
}
