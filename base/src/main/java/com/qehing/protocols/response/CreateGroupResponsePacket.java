package com.qehing.protocols.response;

import com.qehing.protocols.Packet;
import lombok.Data;

import java.util.List;

import static com.qehing.protocols.command.Command.CREATE_GROUP_RESPONSE;

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
