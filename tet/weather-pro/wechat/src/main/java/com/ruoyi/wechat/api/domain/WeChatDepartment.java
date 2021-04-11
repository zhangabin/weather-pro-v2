package com.ruoyi.wechat.api.domain;

/**
 * 微信部门
 * @ClassName WeChatDepartment
 * @Author 1129017402@qq.com
 * @Date 2021/3/7 19:25
 */
public class WeChatDepartment {
    private Long id;
    private String name;
    private String nameEn;
    private Long parentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
