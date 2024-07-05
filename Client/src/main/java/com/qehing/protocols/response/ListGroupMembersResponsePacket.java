package com.qehing.protocols.response;

import com.qehing.protocols.Packet;
import com.qehing.session.Session;
import lombok.Data;

import java.util.List;

import static com.qehing.protocols.command.Command.LIST_GROUP_RESPONSE;

@Data
public class ListGroupMembersResponsePacket extends Packet {

    private Boolean success;
    private String groupId;
    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_RESPONSE;
    }
}
