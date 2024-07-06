package protocols.response;

import lombok.Data;
import protocols.Packet;

import java.util.List;

import static protocols.command.Command.CREATE_GROUP_RESPONSE;


@Data
public class CreateGroupResponsePacket extends Packet {
    private Boolean success;
    private String groupId;
    private List<String> userNameList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_RESPONSE;
    }
}
