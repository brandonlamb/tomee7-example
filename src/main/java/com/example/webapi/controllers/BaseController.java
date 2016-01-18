package com.example.webapi.controllers;

import com.example.webapi.models.Chat;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Lock(LockType.READ)
public class BaseController {

  @Context
  private HttpHeaders headers;

//  @Context
//  private HttpServletRequest request;
//
//  @Context
//  private HttpServletRequestWrapper requestWrapper;

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

  @GET
  @Path("socket.io")
  @Produces({MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
  public Response getSocket() {
    System.out.println("ping socket.io");

    return Response.ok(Chat.builder().playerId(123).build()).build();
  }

  @POST
  @Path("socket.io")
  @Produces({MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
  @Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
  public Response postSocket() {
    System.out.println("ping socket.io");

    return Response.ok(Chat.builder().playerId(123).build()).build();
  }

//  @POST
//  @Path("api/players")
////  @Produces(ProtocolBufferMediaType.APPLICATION_PROTOBUF)
//  @Consumes(ProtocolBufferMediaType.APPLICATION_PROTOBUF)
//  public PlayersProtos.Player postPlayers(PlayersProtos.Player in) throws IOException {
//    return PlayersProtos.Player.newBuilder()
//      .setId(123)
//      .setName("Brandon")
//      .setEmail("brandon@brandonlamb.com")
//      .build();
//  }
//
//  @GET
//  @Path("api/players")
//  @Produces("application/x-msgpack")
//  public Response getPlayers() throws IOException {
//    final PlayersProtos.Player player = PlayersProtos.Player.newBuilder()
//      .setId(123)
//      .setName("Brandon")
//      .setEmail("brandon@brandonlamb.com")
//      .build();
//
//    return Response.ok(player).build();
//  }
}
