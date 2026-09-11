package com.okayjam.net.socket.msgpack;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2020/10/14 19:40
 **/
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.jackson.dataformat.MessagePackFactory;

import java.util.List;
import java.util.Map;

public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {
    private static final ObjectMapper objectMapper = new ObjectMapper(new MessagePackFactory());

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        final byte[] array;
        final int length = msg.readableBytes();
        array = new byte[length];
        msg.getBytes(msg.readerIndex(), array, 0, length);
        Map<String, String> result = objectMapper.readValue(array, new TypeReference<Map<String, String>>() {});
        out.add(result);
    }
}
