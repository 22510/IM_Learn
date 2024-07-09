package com.qehing.server.handlers;


import com.qehing.server.handlerService.UserService;
import com.qehing.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocols.request.AddFriendRequestPacket;
import protocols.response.AddFriendResponsePacket;

public class AddFriendRequestHandler extends SimpleChannelInboundHandler<AddFriendRequestPacket> {

    public static final AddFriendRequestHandler INSTANCE = new AddFriendRequestHandler();



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AddFriendRequestPacket msg) throws Exception {
        System.out.println("服务器处理好友申请");
        addFriend(ctx, msg);
    }

    private void addFriend(ChannelHandlerContext ctx, AddFriendRequestPacket msg) {
        Long userId = SessionUtil.getSession(ctx.channel()).getUserId();
        Long friendId = UserService.getUser(msg.getFriendEmail()).getUserId();

        AddFriendResponsePacket addFriendResponsePacket = new AddFriendResponsePacket();
        addFriendResponsePacket.setVersion(msg.getVersion());
        addFriendResponsePacket.setFriendEmail(msg.getFriendEmail());
        if (friendId == null){
            addFriendResponsePacket.setSuccess(false);
            addFriendResponsePacket.setReason("用户不存在");
        } else {
            addFriendResponsePacket.setSuccess(true);
        }
        ctx.writeAndFlush(addFriendResponsePacket);
    }


}
