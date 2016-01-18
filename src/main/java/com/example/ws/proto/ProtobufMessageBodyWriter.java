package com.example.ws.proto;

import com.google.protobuf.Message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.WeakHashMap;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(ProtocolBufferMediaType.APPLICATION_PROTOBUF)
public class ProtobufMessageBodyWriter implements MessageBodyWriter<Message> {

  private final Map<Object, byte[]> buffer = new WeakHashMap<>();

  @Override
  public boolean isWriteable(
      final Class<?> type,
      final Type genericType,
      final Annotation[] annotations,
      final MediaType mediaType
  ) {
    System.out.println("isWriteable");
    return Message.class.isAssignableFrom(type);
  }

  @Override
  public long getSize(
      final Message m,
      final Class<?> type,
      final Type genericType,
      final Annotation[] annotations,
      final MediaType mediaType
  ) {
    System.out.println("getSize");
    final ByteArrayOutputStream baos = new ByteArrayOutputStream();

    try {
      m.writeTo(baos);
    } catch (final IOException e) {
      return -1;
    }

    byte[] bytes = baos.toByteArray();
    buffer.put(m, bytes);

    return bytes.length;
  }

  public void writeTo(
      final Message m,
      final Class type,
      final Type genericType,
      final Annotation[] annotations,
      final MediaType mediaType,
      final MultivaluedMap httpHeaders,
      final OutputStream entityStream
  ) throws IOException, WebApplicationException {
    System.out.println("writeTo");
    entityStream.write(buffer.remove(m));
  }
}
