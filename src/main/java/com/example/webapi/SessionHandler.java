package com.example.webapi;

import com.example.webapi.models.Device;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;

import lombok.extern.java.Log;

@ApplicationScoped
@Log
public class SessionHandler {

//  private final Set<Session> sessions = new HashSet<>();
  private final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());
  private final Set<Device> devices = Collections.synchronizedSet(new HashSet<>());
  private int deviceId = 0;

  public void addSession(final Session session) {
    sessions.add(session);
    devices.forEach(i -> sendToSession(session, createAddMessage(i)));
  }

  public void removeSession(final Session session) {
    sessions.remove(session);
  }

  public List getDevices() {
    return new ArrayList<>(devices);
  }

  public void addDevice(final Device device) {
    device.setId(deviceId);
    devices.add(device);
    deviceId++;
    sendToAllConnectedSessions(createAddMessage(device));
  }

  public void removeDevice(final int id) {
    final Device device = getDeviceById(id);

    if (device != null) {
      devices.remove(device);
      final JsonProvider provider = JsonProvider.provider();
      sendToAllConnectedSessions(
          provider.createObjectBuilder()
              .add("action", "remove")
              .add("id", id)
              .build()
      );
    }
  }

  public void toggleDevice(final int id) {
    final JsonProvider provider = JsonProvider.provider();
    final Device device = getDeviceById(id);

    if (device != null) {
      if ("On".equals(device.getStatus())) {
        device.setStatus("Off");
      } else {
        device.setStatus("On");
      }

      sendToAllConnectedSessions(
          provider.createObjectBuilder()
              .add("action", "toggle")
              .add("id", device.getId())
              .add("status", device.getStatus())
              .build()
      );
    }
  }

  private Device getDeviceById(final int id) {
    for (final Device device : devices) {
      if (device.getId() == id) {
        return device;
      }
    }
    return null;
  }

  private JsonObject createAddMessage(final Device device) {
    final JsonProvider provider = JsonProvider.provider();
    return provider.createObjectBuilder()
        .add("action", "add")
        .add("id", device.getId())
        .add("name", device.getName())
        .add("type", device.getType())
        .add("status", device.getStatus())
        .add("description", device.getDescription())
        .build();
  }

  private void sendToAllConnectedSessions(final JsonObject message) {
    System.out.println(message);
    sessions.forEach(i -> sendToSession(i, message));
  }

  private void sendToSession(final Session session, final JsonObject message) {
    try {
      session.getBasicRemote().sendText(message.toString());
    } catch (final IOException ex) {
      sessions.remove(session);
      log.warning(ex.getMessage());
    }
  }
}
