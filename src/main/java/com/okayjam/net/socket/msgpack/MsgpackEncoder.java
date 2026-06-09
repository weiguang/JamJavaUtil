package com.okayjam.net.socket.msgpack;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2020/10/14 19:40
 **/
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.jackson.dataformat.MessagePackFactory;

public class MsgpackEncoder extends MessageToByteEncoder<Object> {
    private static final ObjectMapper objectMapper = new ObjectMapper(new MessagePackFactory());

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        byte[] raw = objectMapper.writeValueAsBytes(msg);
        out.writeBytes(raw);
    }
}
