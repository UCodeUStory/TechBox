package com.ustory.techbox.bean;

import java.util.List;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/2
 */
public class ITTech {

    private Integer id;
    private String title;
    private String content;
    private String date;
    private int image;
    private String similar_key_0;
    private String similar_key_1;
    private String similar_key_2;

    public final static int HADER = 1;
    public final static int ITEM = 2;
    private int type =0;
    private String detailHeaderTitle;
    private String detailHeaderContent;
    private String detailContentHeader;
    private List<String> detailContentItems;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDetailHeaderTitle() {
        return detailHeaderTitle;
    }

    public void setDetailHeaderTitle(String detailHeaderTitle) {
        this.detailHeaderTitle = detailHeaderTitle;
    }

    public String getDetailHeaderContent() {
        return detailHeaderContent;
    }

    public void setDetailHeaderContent(String detailHeaderContent) {
        this.detailHeaderContent = detailHeaderContent;
    }

    public String getDetailContentHeader() {
        return detailContentHeader;
    }

    public void setDetailContentHeader(String detailContentHeader) {
        this.detailContentHeader = detailContentHeader;
    }

    public List<String> getDetailContentItems() {
        return detailContentItems;
    }

    public void setDetailContentItems(List<String> detailContentItems) {
        this.detailContentItems = detailContentItems;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getSimilar_key_0() {
        return similar_key_0;
    }

    public void setSimilar_key_0(String similar_key_0) {
        this.similar_key_0 = similar_key_0;
    }

    public String getSimilar_key_1() {
        return similar_key_1;
    }

    public void setSimilar_key_1(String similar_key_1) {
        this.similar_key_1 = similar_key_1;
    }

    public String getSimilar_key_2() {
        return similar_key_2;
    }

    public void setSimilar_key_2(String similar_key_2) {
        this.similar_key_2 = similar_key_2;
    }
}
