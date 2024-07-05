package com.qehing.protocols.request;

import com.qehing.protocols.Packet;
import lombok.Data;

import static com.qehing.protocols.command.Command.LOGOUT_REQUEST;

@Data
public class LogoutRequestPacket extends Packet {

    private String userId;

    @Override
    public Byte getCommand() {
        return LOGOUT_REQUEST;
    }
}
