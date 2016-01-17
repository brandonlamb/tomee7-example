package com.example.webapi.controllers;

import com.example.webapi.SessionHandler;
import com.example.webapi.models.Device;

import java.io.StringReader;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import lombok.extern.java.Log;

@ApplicationScoped
@ServerEndpoint("/chat")
@Log
public class ChatWebSocket {

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
  public void handleMessage(final String message, final Session session) {
    try (JsonReader reader = Json.createReader(new StringReader(message))) {
      JsonObject jsonMessage = reader.readObject();

      if ("add".equals(jsonMessage.getString("action"))) {
        Device device = new Device();
        device.setName(jsonMessage.getString("name"));
        device.setDescription(jsonMessage.getString("description"));
        device.setType(jsonMessage.getString("type"));
        device.setStatus("Off");
        sessionHandler.addDevice(device);
      }

      if ("remove".equals(jsonMessage.getString("action"))) {
        int id = jsonMessage.getInt("id");
        sessionHandler.removeDevice(id);
      }

      if ("toggle".equals(jsonMessage.getString("action"))) {
        int id = jsonMessage.getInt("id");
        sessionHandler.toggleDevice(id);
      }
    }
  }
}
