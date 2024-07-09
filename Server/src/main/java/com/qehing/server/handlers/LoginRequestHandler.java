package com.qehing.server.handlers;

import com.qehing.dbService.dao.User;
import com.qehing.server.handlerService.ThreadPoolManager;
import com.qehing.server.handlerService.UserService;
import com.qehing.utils.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocols.request.LoginRequestPacket;
import protocols.response.LoginResponsePacket;
import session.Session;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    private static ExecutorService threadPoolExecutor;
    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    protected LoginRequestHandler() {
        threadPoolExecutor = ThreadPoolManager.registerThreadPool(
                16,
                32,
                1L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
        System.out.println(new Date() + ": 收到客户端登录请求……");
//        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
//        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
//        loginResponsePacket.setUserName(loginRequestPacket.getUsername());
//
//        boolean valid = valid(loginRequestPacket);
//
//        if (valid) {
//            loginResponsePacket.setSuccess(true);
//            String userId = randomUserId();
//            loginResponsePacket.setUserId(userId);
//            System.out.println(new Date() +":"+loginRequestPacket.getUsername()+": 登录成功!");
//            SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUsername()), ctx.channel());
//        } else {
//            loginResponsePacket.setReason("账号密码校验失败");
//            loginResponsePacket.setSuccess(false);
//            System.out.println(new Date() + ": 登录失败!");
//        }
//
//        // 登录响应
//        ctx.writeAndFlush(loginResponsePacket);
        threadPoolExecutor.submit(() -> {
            try {
                long userId = valid(loginRequestPacket);
                LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
                loginResponsePacket.setVersion(loginRequestPacket.getVersion());
                loginResponsePacket.setEmail(loginRequestPacket.getEmail());
                if (userId != 0) {
//                    String userId = randomUserId();

                    loginResponsePacket.setUserId(userId);
                    // TODO: 登录状态缓存Redis，似乎不必要了，反正也只是缓存一个用户email与channel的映射关系。
                    loginResponsePacket.setSuccess(true);
                    System.out.println(new Date() + ":" + loginRequestPacket.getEmail() + ": 登录成功!");
                    SessionUtil.bindSession(new Session(userId, loginRequestPacket.getEmail()), ctx.channel());
                } else {
                    loginResponsePacket.setReason("账号密码校验失败");
                    loginResponsePacket.setSuccess(false);
                    System.out.println(new Date() + ":" + loginRequestPacket.getEmail() + ": 登录失败!");
                }
                ctx.writeAndFlush(loginResponsePacket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("通道关闭");
        SessionUtil.unbindSession(ctx.channel());
    }

    private Long valid(LoginRequestPacket loginRequestPacket) {
        String email = loginRequestPacket.getEmail();
        User user = UserService.getUser(email);
//        return user != null && user.getPassword().equals(loginRequestPacket.getPassword());
        return user.getUserId();
    }

    private static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}