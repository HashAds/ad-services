package com.hashads.hashads;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import javax.ws.rs.core.Response;
import java.io.*;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class AdProducer {

    private List<Ad> ads;
    private AtomicInteger index;

    private Environment env;

    public AdProducer(Environment env)
    {
        this.env = env;
        ads = new ArrayList<Ad>();
        index = new AtomicInteger(0);
        initAds();
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

    public Ad getAd()
    {
        index.incrementAndGet();
        int i = (index.get()+1/ads.size()) % ads.size();
        //i-=1;
        return getAdById(Integer.toString(i));
    }

    private Ad getAdById(String id)
    {
        String url = env.getProperty(id + ".url");
        if (url==null) throw new RuntimeException();
        String imageResource = env.getProperty(id + ".adImageResource");
        System.out.println("url: " + url + " res: " + imageResource);
        Ad ad = new Ad();
        ad.setAdId(id);
        ad.setUrl(url);
        ad.setAdImageResource(imageResource);
        return ad;
    }

    public Response getImageByAdId(String id) throws IOException
    {
        for (Ad ad : ads)
        {
            if (ad.getAdId().equals(id))
            {
                return getImage(ad.getAdImageResource(), false);
            }
        }
        return null;
    }

    public Response getImageBase64ByAdId(String id) throws IOException
    {
        for (Ad ad : ads)
        {
            if (ad.getAdId().equals(id))
            {
                return getImage(ad.getAdImageResource(), true);
            }
        }
        return null;
    }





    public Response getImage(String imgResource, boolean base64) throws IOException  {
//        String path = "/Volumes/Development/hashads/target/classes/adimgs/meltdown.png";
        File file = new File(imgResource);

        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            byte[] encodedBytes = base64 ? Base64.getEncoder().encode(fileContent) : fileContent;
            Response.ResponseBuilder responseBuilder = Response.ok(encodedBytes, "image/png");
            responseBuilder.header("Content-Disposition", "attachment; filename=\"MyImageFile.png\"");
            return responseBuilder.build();

        }
        catch (Exception e)
        {
            System.err.println(e);
            e.printStackTrace();
        }
        return null;

    }





}
