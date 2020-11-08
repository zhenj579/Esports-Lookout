package com.example.liquidlookout;

public class Games {
    private String name;
    private String imgUrl;

    public Games(String name,String url) {
        this.name = name;
        this.imgUrl = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }





    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
