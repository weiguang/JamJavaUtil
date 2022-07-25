package com.okayjam.net.socket.msgpack.tx;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2020/10/19 11:31
 **/

public class RcMessage {
    public String uuid;
    public int metric;
    public int resver;

    public String service;
    public String func;
    public String sig;

    public List<String> err_vec;

    public Map<String, String> rec_map;
    public Map<String, Map<String, String>> rec_nmap;
    public Map<String, List<String>> rec_vec;


    public byte[] header;

    public RcMessage() {
        uuid = "";
        metric = 0;
        resver = 0;
        service = "";
        func = "";
        sig = "";
        err_vec = new ArrayList<String>();
        rec_map = new HashMap<String, String>();
        rec_nmap = new HashMap<String, Map<String, String>>();
        rec_vec = new HashMap<String, List<String>>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : rec_map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append("&");
        }
        return sb.toString();
    }
}