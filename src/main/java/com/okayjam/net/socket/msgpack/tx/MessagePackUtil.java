package com.okayjam.net.socket.msgpack.tx;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.okayjam.util.CommonUtil;
import org.msgpack.jackson.dataformat.JsonArrayFormat;
import org.msgpack.jackson.dataformat.MessagePackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;

public class MessagePackUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessagePackUtil.class);

    public static byte[] pack(String service, String func, Map<String, String> paramMap) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper(new MessagePackFactory());
        //0.6
        objectMapper.setAnnotationIntrospector(new JsonArrayFormat());

        byte[] rawMsg;

        // 构建body
        RcMessage rcMessage = new RcMessage();
        rcMessage.service = service;
        rcMessage.func = func;
        rcMessage.rec_map = paramMap;
        byte[] head = new byte[20];
//        byte[] body = msgPack.write(rcMessage);
        byte[] body = objectMapper.writeValueAsBytes(rcMessage);
        int totalLength = head.length + body.length;

        // header
        ByteBuffer buffer1 = ByteBuffer.allocate(totalLength);
        buffer1.put(CommonUtil.int2BytesBig(totalLength));
        buffer1.put(CommonUtil.int2BytesBig(10001));
        buffer1.put(CommonUtil.int2BytesBig(1));
        buffer1.put(new byte[8]);


        // body
        buffer1.put(body);
        //buffer1.flip();
        rawMsg = buffer1.array();
        return rawMsg;
    }

    public static RcMessage unpack(byte[] response, int size) {
        //byte[] bodyBytes = Arrays.copyOfRange(totalBytes, 20, totalLength);
        byte[] bodyBytes = new byte[size];
        System.arraycopy(response, 20, bodyBytes, 0, size - 20);
        RcMessage message;
        try {
            ObjectMapper objectMapper = new ObjectMapper(new MessagePackFactory());
            objectMapper.setAnnotationIntrospector(new JsonArrayFormat());
//            message = new MessagePack().read(bodyBytes, RcMessage.class);
            message = objectMapper.readValue(bodyBytes, RcMessage.class);
            byte[] header = new byte[20];
            System.arraycopy(response, 0, header, 0, 20);
            message.header = header;
        } catch (Exception e) {
            message = new RcMessage();
            e.printStackTrace();
            LOGGER.error("Fail to decode message. ", e);
        }
        return message;
    }
}