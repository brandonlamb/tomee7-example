package com.example.webapi.controllers;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

public abstract class BaseController {

  @Context
  private HttpHeaders headers;

  /**
   * Added due to unit tests not having an accept-language header available
   *
   * @return String
   */
  protected String getLocale() {
    if (headers.getAcceptableLanguages().size() > 0) {
      return headers.getAcceptableLanguages().get(0).getLanguage();
    }
    return "en";
  }
}
