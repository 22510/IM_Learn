package protocols.response;


import lombok.Data;
import protocols.Packet;

import static protocols.command.Command.LIST_GROUP_RESPONSE;

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
