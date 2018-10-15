package com.hashads.hashads;

import java.io.Serializable;

public class Ad implements Serializable {

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

    public String getAdImageUrl() {
        return adImageUrl;
    }

    public void setAdImageUrl(String adImageUrl) {
        this.adImageUrl = adImageUrl;
    }

    private String url;
    private String adId;
    private String adImageUrl;

    @Override
    public String toString() {
        return adId + ", " + adImageUrl;
    }
}
