package com.hashads.hashads;

import java.io.Serializable;

public class Ad implements Serializable {
    private String url;
    private String adId;
    private String adImageResource;
    private String hostAccount;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getAdImageResource() {
        return adImageResource;
    }

    public void setAdImageResource(String adImageResource) {
        this.adImageResource = adImageResource;
    }

    public String getHostAccount() {
        return hostAccount;
    }

    public void setHostAccount(String hostAccount) {
        this.hostAccount = hostAccount;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    private String imgUrl;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private String img;

    @Override
    public String toString() {
        return adId + ", " + adImageResource;
    }
}
