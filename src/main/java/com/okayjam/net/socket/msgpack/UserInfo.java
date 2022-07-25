package com.okayjam.net.socket.msgpack;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2020/10/14 19:54
 **/
public class UserInfo {

    String cmd;
    String ip;

    public UserInfo() {

    }

    public UserInfo(String cmd, String ip) {
        this.cmd = cmd;
        this.ip = ip;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "cmd='" + cmd + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
