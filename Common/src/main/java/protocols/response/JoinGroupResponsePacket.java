package protocols.response;

import lombok.Data;
import protocols.Packet;

import static protocols.command.Command.JOIN_GROUP_RESPONSE;

@Data
public class JoinGroupResponsePacket extends Packet {

    private Boolean success;
    private String groupId;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_RESPONSE;
    }
}
