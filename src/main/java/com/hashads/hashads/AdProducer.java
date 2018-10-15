package com.hashads.hashads;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
                return getImage(ad.getAdImageResource());
            }
        }
        return null;
    }


    public Response getImage(String imgResource) throws IOException {
        System.out.println("getImage() - imgResource: " + imgResource);

        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(imgResource);
        System.out.println("URL: " + url);
        File file = new File(url.getFile());
        String mediaType = "image/png";

        Response.ResponseBuilder response = Response.ok((Object) file);
        return Response.ok(file,mediaType).build();
        //response.header("Content-Disposition", "attachment; filename=test.png");
        //return response.build();

    }



}
