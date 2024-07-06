package protocols.request;

import lombok.Data;
import protocols.Packet;
import protocols.command.Command;

@Data
public class LogoutRequestPacket extends Packet {

    private String userId;

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }
}
