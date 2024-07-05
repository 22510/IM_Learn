package com.qehing.protocols.response;

import com.qehing.protocols.Packet;
import lombok.Data;

import static com.qehing.protocols.command.Command.JOIN_GROUP_RESPONSE;

@Data
public class JoinGroupResponsePacket extends Packet {

    private Boolean success;
    private String groupId;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_RESPONSE;
    }
}
