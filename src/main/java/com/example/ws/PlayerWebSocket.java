package com.example.ws;

import com.example.webapi.models.Player;

import org.msgpack.MessagePack;

import java.io.IOException;
import java.nio.ByteBuffer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import lombok.extern.java.Log;

@ApplicationScoped
@ServerEndpoint("/players")
@Log
public class PlayerWebSocket {

  private final MessagePack msgpack = new MessagePack();

  @Inject
  private SessionHandler sessionHandler;

  @OnOpen
  public void open(final Session session) {
    sessionHandler.addSession(session);
  }

  @OnClose
  public void close(final Session session) {
    sessionHandler.removeSession(session);
  }

  @OnError
  public void onError(final Throwable error) {
    log.warning(error.getMessage());
  }

  @OnMessage
  public void handleMessage(final ByteBuffer message, final Session session) throws IOException {
    final Player player = msgpack.read(message, Player.class);
    System.out.println(player);
  }
}
