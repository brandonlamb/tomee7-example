package com.example;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class JaxRsActivator extends javax.ws.rs.core.Application {
  // let the container scan for @Path resources
}
