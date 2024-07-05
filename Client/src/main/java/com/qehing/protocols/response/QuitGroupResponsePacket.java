package com.qehing.protocols.response;

import com.qehing.protocols.Packet;
import lombok.Data;

import static com.qehing.protocols.command.Command.QUIT_GROUP_RESPONSE;

@Data
public class QuitGroupResponsePacket extends Packet {

    private Boolean success;
    private String groupId;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_RESPONSE;
    }
}
