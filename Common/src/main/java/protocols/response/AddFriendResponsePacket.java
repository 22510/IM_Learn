package protocols.response;

import lombok.Data;
import protocols.Packet;

import static protocols.command.Command.ADD_FRIEND_RESPONSE;


@Data
public class AddFriendResponsePacket extends Packet {
    private String friendEmail;
    private Boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return ADD_FRIEND_RESPONSE;
    }
}
