package com.qehing.protocols.request;

import com.qehing.protocols.Packet;
import lombok.Data;

import static com.qehing.protocols.command.Command.LIST_GROUP_REQUEST;

@Data
public class ListGroupMembersRequestPacket extends Packet {
    private String groupId;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_REQUEST;
    }
}
