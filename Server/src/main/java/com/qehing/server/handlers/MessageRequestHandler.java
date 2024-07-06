package com.qehing.server.handlers;


import com.qehing.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocols.request.MessageRequestPacket;
import protocols.response.MessageResponsePacket;
import session.Session;

import java.util.Date;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        Session session = SessionUtil.getSession(ctx.channel());

        System.out.println(new Date() + ":收到客户端新消息："+messageRequestPacket.getMessage());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setVersion(messageRequestPacket.getVersion());
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());

//        ByteBuf byteBuf = PacketCodeC.encode(ctx.alloc().ioBuffer(),messageResponsePacket);
//        ctx.channel().writeAndFlush(messageResponsePacket);
        Channel toUserChannel = SessionUtil.getChannel(messageRequestPacket.getToUserId());
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(messageResponsePacket);
        } else {
            System.err.println(messageRequestPacket.getToUserId()+":对方不在线");
        }
    }
}
