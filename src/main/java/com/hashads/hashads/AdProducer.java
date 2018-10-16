package com.hashads.hashads;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class AdProducer {

    private List<Ad> ads;
    private AtomicInteger index;
    private int adRate;

    private Environment env;

    public AdProducer(Environment env)
    {
        this.env = env;
        String adRateString = env.getProperty("adRate");
        try
        {
            this.adRate = Integer.parseInt(adRateString);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            adRate = 1;
        }
        ads = new ArrayList<Ad>();
        index = new AtomicInteger(0);
        initAds();
    }

    public int getAdRate()
    {
        return adRate;
    }

    private void initAds()
    {
        int i = 0;
        boolean done = false;
        while (!done)
        {
            try {
                Ad ad = getAdById(Integer.toString(i));
                ads.add(ad);
                i++;
            }
            catch (Exception e)
            {
                done = true;
            }
        }
        System.out.println("Ads initialized: " + ads);
    }

    public Ad getAd() throws IOException
    {
        index.incrementAndGet();
        int i = (index.get()+1/ads.size()) % ads.size();
        return getAdById(Integer.toString(i));
    }

    public Ad getAdById(String id)
    {
        String url = env.getProperty(id + ".url");
        String imgUrl = env.getProperty(id + ".imgUrl");
        String hostAccount = env.getProperty(id + ".hostAccount");
        if (url==null) throw new RuntimeException();
        String imageResource = env.getProperty(id + ".adImageResource");
        System.out.println("url: " + url + " res: " + imageResource);
        Ad ad = new Ad();
        ad.setAdId(id);
        ad.setUrl(url);
        ad.setImgUrl(imgUrl);
        ad.setAdImageResource(imageResource);
        ad.setHostAccount(hostAccount);
        return ad;
    }
}
