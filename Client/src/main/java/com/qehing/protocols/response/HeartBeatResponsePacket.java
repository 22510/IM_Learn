package com.qehing.protocols.response;


import com.qehing.protocols.Packet;

import static com.qehing.protocols.command.Command.HEARTBEAT_RESPONSE;

public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
