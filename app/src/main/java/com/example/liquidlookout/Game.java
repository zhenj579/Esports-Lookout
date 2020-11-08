package com.example.liquidlookout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//games object to hold a string name and an int that holds the location of the picture in the drawable folder
public class Game {
    private String name;
    private int img;

    public Game(String name, int url, Games game) {
        this.name = name;
        this.img = url;
    }

    public int getImgUrl() {
        return img;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
