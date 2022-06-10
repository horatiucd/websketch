package com.hcd.websketch.controller;

import com.hcd.websketch.api.DestinationDeepLinkClient;
import com.hcd.websketch.api.DestinationRequest;
import com.hcd.websketch.domain.Destination;
import com.hcd.websketch.exception.EntityNotFoundException;
import com.hcd.websketch.repository.DestinationRepository;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

@Controller
@RequestMapping("/deep-link")
public class DestinationDeepLinkController {

    private static final Logger LOG = LoggerFactory.getLogger(DestinationDeepLinkController.class);

    private final DestinationRepository repository;

    public DestinationDeepLinkController(DestinationRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/get/destinations/{id}")
    public String get(@PathVariable Long id) {
        final Destination destination = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        LOG.info("GET destination {}.", destination);

        return "redirect:" + destination.getUrl();
    }

    @GetMapping(value = "/post/destinations/{id}")
    public void post(@PathVariable Long id) {
        final Destination destination = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        LOG.info("POST destination {}.", destination);

        final String destinationUrl = destination.getUrl().substring(0, destination.getUrl().indexOf("?"));
        final DestinationRequest request = new DestinationRequest();
        request.setUsername("horatiu.dan@tangoe.com");
        request.setDestination("INVOICE");
        request.setInvoiceId(441527);

        final DestinationDeepLinkClient client = connect(destinationUrl);
        client.deepLink(request);

        LOG.info("POST destination successful.");
    }

    private DestinationDeepLinkClient connect(String destinationUrl) {
        final Request.Options options = new Request.Options(10L, TimeUnit.SECONDS,
                60L, TimeUnit.SECONDS, true);

        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .options(options)
                .retryer(Retryer.NEVER_RETRY)
                .logger(new Slf4jLogger(DestinationDeepLinkClient.class))
                .logLevel(feign.Logger.Level.FULL)
                .target(DestinationDeepLinkClient.class, destinationUrl);
    }
}
