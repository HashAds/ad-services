package com.hashads.hashads;

import javax.ws.rs.core.Response;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AdProducer {

    private List<Ad> ads;
    private AtomicInteger index;





    public AdProducer()
    {
        ads = new ArrayList<Ad>();
        index = new AtomicInteger(0);

        Ad ad = new Ad();
        ad.setAdId("1");
        ad.setUrl("https://youtu.be/Eb7XJ8zfCZA");
        ad.setAdImageUrl("/Volumes/Development/ads/meltdown.png");
        ads.add(ad);

        ad = new Ad();
        ad.setAdId("2");
        ad.setUrl("https://youtu.be/Eb7XJ8zfCZA");
        ad.setAdImageUrl("/Volumes/Development/ads/meltdown.png");
        ads.add(ad);
    }

    public Ad getAd()
    {
        index.incrementAndGet();
        int i = (index.get()+1/ads.size()) % ads.size();
        Ad ad = ads.get(i);
        return ad;
    }

    public Response getImageByAdId(String id)
    {
        for (Ad ad : ads)
        {
            if (ad.getAdId().equals(id))
            {
                return getImage(ad.getAdImageUrl());
            }
        }
        return null;
    }

    public Response getImage(String imgPath){
        File file = new File("/Volumes/Development/ads/meltdown.png");
        String mediaType = "image/png";
        return Response.ok(file,mediaType).build();
    }



}
