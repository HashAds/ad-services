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

    public String getAdImageResource() {
        return adImageResource;
    }

    public void setAdImageResource(String adImageResource) {
        this.adImageResource = adImageResource;
    }

    private String url;
    private String adId;
    private String adImageResource;

    @Override
    public String toString() {
        return adId + ", " + adImageResource;
    }
}
