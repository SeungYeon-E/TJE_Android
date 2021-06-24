package com.example.kakaologin.Bean;

public class Friends {
    private String id;
    private String name;
    private String phone;
    private String address;
    private String email;
    private String favorite;
    private String image;

    private String gid;
    private String gname;

    private String uid;
    private String uname;
    private String uphone;
    private String uemail;
    private String uimage;
    private String uaddress;

    public Friends(String id, String name, String phone, String address, String email, String image) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.image = image;
    }

    public Friends(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getFavorite() {
        return favorite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGid() {
        return gid;
    }

    public String getGname() {
        return gname;
    }

    public String getUid() {
        return uid;
    }

    public String getUname() {
        return uname;
    }

    public String getUphone() {
        return uphone;
    }

    public String getUemail() {
        return uemail;
    }

    public String getUimage() {
        return uimage;
    }
}
