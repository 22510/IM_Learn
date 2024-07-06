package protocols.response;


import lombok.Data;
import protocols.Packet;

import static protocols.command.Command.QUIT_GROUP_RESPONSE;

@Data
public class QuitGroupResponsePacket extends Packet {

    private Boolean success;
    private String groupId;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_RESPONSE;
    }
}
