package com.okayjam.util;

public class CommonUtil {

    /**
     * 小端输出模式
     * @param i  the integer need to change
     * @return byte array
     */
    public static byte[] int2BytesSmall(int i) {
        return new byte[]{
                (byte) (i & 0xFF),
                (byte) ((i >> 8) & 0xFF),
                (byte) ((i >> 16) & 0xFF),
                (byte) ((i >> 24) & 0xFF)
        };
    }

    /**
     * 大端输出模式
     * @param i  the integer need to change
     * @return byte array
     */
    public static byte[] int2BytesBig(int i) {
        byte[] bytes = new byte[4];
        int offset = 0;
        bytes[offset] = (byte) (i >>> 24);
        bytes[offset + 1] = (byte) (i >>> 16);
        bytes[offset + 2] = (byte) (i >>> 8);
        bytes[offset + 3] = (byte) i;
        return bytes;
    }

}