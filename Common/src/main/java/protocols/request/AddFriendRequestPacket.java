package protocols.request;

import lombok.Data;
import protocols.Packet;

import static protocols.command.Command.CREATE_GROUP_REQUEST;


@Data
public class AddFriendRequestPacket extends Packet {
    String friendEmail;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }
}
