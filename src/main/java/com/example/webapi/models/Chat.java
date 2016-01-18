package com.example.webapi.models;

import org.msgpack.annotation.Message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Message
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement(name = "chat")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Chat {

  public int playerId;
  public String message;
}
