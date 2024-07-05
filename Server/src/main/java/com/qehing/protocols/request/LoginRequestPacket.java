package com.qehing.protocols.request;

import com.qehing.protocols.Packet;
import lombok.Data;

import static com.qehing.protocols.command.Command.LOGIN_REQUEST;

@Data
public class LoginRequestPacket extends Packet {

    private String email;
    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
