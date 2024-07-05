package com.qehing.protocols.request;

import com.qehing.protocols.Packet;
import lombok.Data;

import static com.qehing.protocols.command.Command.QUIT_GROUP_REQUEST;

@Data
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_REQUEST;
    }
}
