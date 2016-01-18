package com.example.webapi.proto;

import com.google.protobuf.InvalidProtocolBufferException;

import com.example.webapi.proto.protos.DropwizardProtos.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidProtocolBufferExceptionMapper
    implements ExceptionMapper<InvalidProtocolBufferException> {

  @Override
  public Response toResponse(InvalidProtocolBufferException exception) {
    final ErrorMessage message = ErrorMessage.newBuilder()
        .setMessage("Unable to process protocol buffer")
        .setCode(Response.Status.BAD_REQUEST.getStatusCode())
        .build();

    System.out.println("Unable to process protocol buffer message");

    return Response
        .status(Response.Status.BAD_REQUEST)
        .type(ProtocolBufferMediaType.APPLICATION_PROTOBUF_TYPE)
        .entity(message)
        .build();
  }
}
