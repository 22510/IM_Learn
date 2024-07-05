package com.qehing.protocols.request;
import com.qehing.protocols.Packet;

import static com.qehing.protocols.command.Command.HEARTBEAT_REQUEST;

public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
