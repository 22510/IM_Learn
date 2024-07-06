package protocols.request;

import lombok.Data;
import protocols.Packet;

import static protocols.command.Command.LIST_GROUP_REQUEST;

@Data
public class ListGroupMembersRequestPacket extends Packet {
    private String groupId;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_REQUEST;
    }
}
