package com.qehing.protocols.response;

import com.qehing.protocols.Packet;
import lombok.Data;

import static com.qehing.protocols.command.Command.MESSAGE_RESPONSE;

@Data
public class MessageResponsePacket extends Packet {

    private String fromUserId;
    private String fromUserName;
    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
