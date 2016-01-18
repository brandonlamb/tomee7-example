package com.example.webapi;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

/**
 * See http://www.w3.org/TR/cors/
 *
 * @author airhacks.com
 */
@Provider
public class CorsResponseFilter implements ContainerResponseFilter {

  private static final String ALLOWED_METHODS = "GET, POST, PUT, DELETE, OPTIONS, HEAD";
  private final static int MAX_AGE = 42 * 60 * 60;
  private final static String DEFAULT_ALLOWED_HEADERS = "origin,accept,content-type";

  @Override
  public void filter(
    final ContainerRequestContext requestContext,
    final ContainerResponseContext responseContext
  ) throws IOException {
    System.out.println("Executing REST CORS Response");

    final MultivaluedMap<String, Object> headers = responseContext.getHeaders();
//    headers.add("Access-Control-Allow-Origin", "*");
    headers.add("Access-Control-Allow-Origin", "http://space-empires.com");
    headers.add("Access-Control-Allow-Headers", getRequestedHeaders(requestContext));
    headers.add("Access-Control-Allow-Credentials", "true");
    headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
    headers.add("Access-Control-Max-Age", MAX_AGE);
    headers.add("x-responded-by", "cors-response-filter");
  }

  private String getRequestedHeaders(final ContainerRequestContext responseContext) {
    final List<String> headers = responseContext.getHeaders().get("Access-Control-Request-Headers");
    return createHeaderList(headers);
  }

  private String createHeaderList(final List<String> headers) {
    if (headers == null || headers.isEmpty()) {
      return DEFAULT_ALLOWED_HEADERS;
    }
    final StringBuilder retVal = new StringBuilder();
//    for (int i = 0; i < headers.size(); i++) {
    for (final String header : headers) {
//      String header = headers.get(i);
      retVal.append(header);
      retVal.append(',');
    }
    retVal.append(DEFAULT_ALLOWED_HEADERS);
    return retVal.toString();
  }
}
