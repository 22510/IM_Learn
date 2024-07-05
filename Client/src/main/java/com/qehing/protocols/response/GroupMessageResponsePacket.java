package com.qehing.protocols.response;

import com.qehing.protocols.Packet;
import com.qehing.session.Session;
import lombok.Data;

import static com.qehing.protocols.command.Command.GROUP_MESSAGE_RESPONSE;

@Data
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;
    private String message;
    private Session fromUser;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_RESPONSE;
    }
}
