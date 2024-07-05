package com.qehing.protocols.request;

import com.qehing.protocols.Packet;
import lombok.Data;

import java.util.List;

import static com.qehing.protocols.command.Command.CREATE_GROUP_REQUEST;

@Data
public class CreateGroupRequestPacket extends Packet {
    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }
}
