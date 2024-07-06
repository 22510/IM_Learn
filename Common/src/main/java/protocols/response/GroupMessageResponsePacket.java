package protocols.response;


import lombok.Data;
import protocols.Packet;
import session.Session;

import static protocols.command.Command.GROUP_MESSAGE_RESPONSE;

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
