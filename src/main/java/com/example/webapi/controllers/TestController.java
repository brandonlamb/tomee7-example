package com.example.webapi.controllers;

import com.example.provider.TestProvider;
import com.example.webapi.models.Test;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import lombok.extern.java.Log;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

@ApplicationScoped
@Path("examples")
@Consumes({APPLICATION_JSON, APPLICATION_XML})
@Produces({APPLICATION_JSON, APPLICATION_XML})
@Log
public class TestController extends BaseController {

  @Inject
  private TestProvider testService;

  private Test st = new Test(0, "Test");

  @GET
  @Path("{id: \\d+}")
  public Response getCollection(
      @PathParam("id")
      final int id
  ) {
    try {
      return Response.ok(new Test(id, "Test " + id)).build();
    } catch (final Exception e) {
      log.warning(e.getMessage());
      return Response.serverError().entity(e.getMessage()).build();
    }
  }

  @POST
  public Response postCollection(final Test test) {
    final List<Test> models = testService.findAll();

    if (models.get(test.getId() - 1) != null) {
      return Response.ok(models.get(test.getId() - 1)).build();
    } else {
      return Response.ok(test).build();
    }
  }
}
