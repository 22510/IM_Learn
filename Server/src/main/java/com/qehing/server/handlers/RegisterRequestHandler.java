package com.qehing.server.handlers;

import com.qehing.server.handlerService.ThreadPoolManager;
import com.qehing.server.handlerService.UserService;
import com.qehing.utils.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocols.request.RegisterRequestPacket;
import protocols.response.RegisterResponsePacket;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@ChannelHandler.Sharable
public class RegisterRequestHandler extends SimpleChannelInboundHandler<RegisterRequestPacket> {

    private static ExecutorService threadPoolExecutor;
    public static final RegisterRequestHandler INSTANCE = new RegisterRequestHandler();

    protected RegisterRequestHandler() {
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
    protected void channelRead0(ChannelHandlerContext ctx, RegisterRequestPacket registerRequestPacket) {
        System.out.println(new Date() + ": 收到客户端注册请求……");
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
                boolean valid = register(registerRequestPacket);
                RegisterResponsePacket registerResponsePacket = new RegisterResponsePacket();
                registerResponsePacket.setVersion(registerRequestPacket.getVersion());
                registerResponsePacket.setEmail(registerRequestPacket.getEmail());
                if (valid) {
                    registerResponsePacket.setSuccess(true);
                    System.out.println(new Date() + ":" + registerRequestPacket.getEmail() + ": 注册成功!");
                } else {
                    registerResponsePacket.setReason("注册失败");
                    registerResponsePacket.setSuccess(false);
                    System.out.println(new Date() + ":" + registerRequestPacket.getEmail() + ": 注册失败!");
                }
                ctx.writeAndFlush(registerResponsePacket);
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

    private boolean register(RegisterRequestPacket registerRequestPacket) {
        String userName = registerRequestPacket.getUserName();
        String email = registerRequestPacket.getEmail();
        String password = registerRequestPacket.getPassword();
        if (UserService.getUser(email) == null){
            return UserService.register(userName, email, password);
        }
        return false;
    }
}