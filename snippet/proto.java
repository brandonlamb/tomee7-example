final ServletInputStream in = request.getInputStream();

    int count;
final ByteArrayOutputStream out = new ByteArrayOutputStream();
    byte[] b = new byte[16000];
    while ((count = in.read(b)) != -1) {
    out.write(b, 0, count);
    }
    out.close();

final byte[] ba = out.toByteArray();
    System.out.println("BYTEARRAY: " + ba);

//    final BufferedInputStream io1 = request.getReader();
//    final InputStream io2 = request.getInputStream();
//      final InputStream is = request.getInputStream();
//    body = IOUtils.toByteArray(is);

//    final ByteArrayInputStream is = request.getInputStream();
//
//    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
//    byte[] buffer = new byte[1024];
//    int readBytes = -1;
//
//    while ((readBytes = is.read(buffer)) > 1) {
//      baos.write(buffer, 0, readBytes);
//    }
//
//    byte[] responseArray = baos.toByteArray();

final MessagePack msgpack = new MessagePack();

    System.out.println("Read player");
final Player player = msgpack.read(ba, Player.class);
    System.out.println(player);

    return Response.ok().build();
//    return Response.ok().entity(msgpack.write(player)).build();
