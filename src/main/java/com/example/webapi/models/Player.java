package com.example.webapi.models;

import org.msgpack.annotation.Message;

@Message
public class Player {

  public int id;
  public String name;
  public String email;
}
