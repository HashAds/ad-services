package com.hashads.hashads;


import org.apache.cxf.jaxrs.impl.MediaTypeHeaderProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping("ads")
public class AdController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private AdProducer adProducer;

    @RequestMapping("test")
    public String test() {
        log.info("Test");
        return "OK";
    }

    @GET
    @RequestMapping("ad")
    @Produces(MediaType.APPLICATION_JSON)
    public Ad getAd()
    {
        return adProducer.getAd();
    }


    @GET
    @Path("/adImage/{adId}")
    @Produces("image/png")
    public Response getImage(@PathParam("adId") String adId)
    {
        return adProducer.getImageByAdId(adId);
    }




}