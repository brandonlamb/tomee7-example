package com.example.webapi;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class CorsRequestFilter implements ContainerRequestFilter {

  @Override
  public void filter(final ContainerRequestContext requestCtx) throws IOException {
    System.out.println("Executing REST request filter");
    System.out.println("Method: " + requestCtx.getRequest().getMethod());

    // When HttpMethod comes as OPTIONS, just acknowledge that it accepts...
    if ("OPTIONS".equals(requestCtx.getRequest().getMethod())) {
      System.out.println("HTTP Method (OPTIONS) - Detected!");

      // Just send a OK signal back to the browser
      final Response response = Response
        .ok()
        .header("Access-Control-Allow-Origin", "http://space-empires.com")
        .build();

      requestCtx.abortWith(response);
    }
    System.out.println("Continue on");
  }
}
