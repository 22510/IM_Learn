package com.qehing.protocols.response;

import com.qehing.protocols.Packet;
import lombok.Data;

import static com.qehing.protocols.command.Command.LOGOUT_RESPONSE;

@Data
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {

        return LOGOUT_RESPONSE;
    }
}
