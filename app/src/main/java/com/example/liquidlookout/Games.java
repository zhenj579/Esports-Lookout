package com.example.liquidlookout;

public class Games {
    private String name;
    private int img;

    public Games(String name,int url) {
        this.name = name;
        this.img = url;
    }

    public int getImgUrl() {
        return img;
    }

    public void setImgUrl(int imgUrl) {
        this.img = imgUrl;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
