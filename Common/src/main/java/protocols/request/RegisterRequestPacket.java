package protocols.request;

import lombok.Data;
import protocols.Packet;
import protocols.command.Command;

@Data
public class RegisterRequestPacket extends Packet {

    private String email;
    private String password;

    @Override
    public Byte getCommand() {
        return Command.REGISTER_REQUEST;
    }
}
