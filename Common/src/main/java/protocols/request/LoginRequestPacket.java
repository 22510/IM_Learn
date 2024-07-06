package protocols.request;

import lombok.Data;
import protocols.Packet;
import protocols.command.Command;

@Data
public class LoginRequestPacket extends Packet {

    private String email;
    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
