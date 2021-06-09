package com.aoslec.networkjson_03;

public class JsonStudent {
    private String code;
    private String name;
    private String dept;
    private String phone;
    private String image;

    public JsonStudent(String code, String name, String dept, String phone, String image) {
        this.code = code;
        this.name = name;
        this.dept = dept;
        this.phone = phone;
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
