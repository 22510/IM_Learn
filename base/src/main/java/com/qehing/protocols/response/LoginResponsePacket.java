package com.qehing.protocols.response;

import com.qehing.protocols.Packet;
import lombok.Data;

import static com.qehing.protocols.command.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {
    private String userId;

    private String userName;

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
