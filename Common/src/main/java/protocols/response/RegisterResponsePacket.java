package protocols.response;


import lombok.Data;
import protocols.Packet;

import static protocols.command.Command.REGISTER_RESPONSE;

@Data
public class RegisterResponsePacket extends Packet {

    private String email;

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return REGISTER_RESPONSE;
    }
}
