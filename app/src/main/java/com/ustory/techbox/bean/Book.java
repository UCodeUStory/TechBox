package com.ustory.techbox.bean;

/**
 * Created by clevo on 2015/7/27.
 */
public class Book {
    private Integer id;
    private int img;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book(){

    }
    public Book(int img, String name) {
        this.img = img;
        this.name = name;
    }


}
