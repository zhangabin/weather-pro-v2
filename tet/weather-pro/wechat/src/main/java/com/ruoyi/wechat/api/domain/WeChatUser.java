package com.ruoyi.wechat.api.domain;

import java.util.List;

/**
 * 微信用户
 * @ClassName WeChatUser
 * @Author 1129017402@qq.com
 * @Date 2021/3/7 17:17
 */
public class WeChatUser {
    private String userId;
    private String name;
    private String mobile;
    private List<Integer> department;
    //性别
    private String gender;
    private String email;
    private String avatar;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getDepartment() {
        return department;
    }

    public void setDepartment(List<Integer> department) {
        this.department = department;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
