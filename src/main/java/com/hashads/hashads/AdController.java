package com.hashads.hashads;

import com.hedera.sdk.account.HederaAccount;
import com.hedera.sdk.common.HederaAccountID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;


@RestController
@RequestMapping("ads")
public class AdController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private AdProducer adProducer;

    @GET
    @RequestMapping("ad")
    @Produces(MediaType.APPLICATION_JSON)
    public Ad getAd() throws IOException
    {
        return adProducer.getAd();
    }

    @POST
    @RequestMapping("trackView/{adId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackView(@PathVariable("adId") String adId) throws Exception
    {

//        HederaAccount a = new HederaAccount();
//        HederaAccountID id = new HederaAccountID();
//        a.send(id, 3);

        return Response.ok().build();
    }

}