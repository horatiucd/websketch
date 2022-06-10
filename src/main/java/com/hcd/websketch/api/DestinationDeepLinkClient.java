package com.hcd.websketch.api;

import feign.Headers;
import feign.RequestLine;

public interface DestinationDeepLinkClient {

    @RequestLine("POST ")
    @Headers("Content-Type: application/json")
    void deepLink(DestinationRequest request);
}
