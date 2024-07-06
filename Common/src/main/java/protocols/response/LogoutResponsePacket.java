package protocols.response;


import lombok.Data;
import protocols.Packet;

import static protocols.command.Command.LOGOUT_RESPONSE;

@Data
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGOUT_RESPONSE;
    }
}
