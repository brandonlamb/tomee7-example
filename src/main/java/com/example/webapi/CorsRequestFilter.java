package com.example.webapi;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;

//@Provider
//@PreMatching
public class CorsRequestFilter implements ContainerRequestFilter {

  @Override
  public void filter(ContainerRequestContext requestCtx) throws IOException {
    System.out.println("Executing REST request filter");

    // When HttpMethod comes as OPTIONS, just acknowledge that it accepts...
    if (requestCtx.getRequest().getMethod().equals("OPTIONS")) {
      System.out.println("HTTP Method (OPTIONS) - Detected!");

      // Just send a OK signal back to the browser
      requestCtx.abortWith(Response.status(Response.Status.OK).build());
    }
  }
}
