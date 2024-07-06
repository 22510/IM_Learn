package protocols.command;

public interface Command {
    /**
     * 用户登录指令标识
     */
    Byte LOGIN_REQUEST = 1;

    /**
     * 用户登录-服务端回应指令标识
     */
    Byte LOGIN_RESPONSE = 2;

    /**
     * 消息发送指令标识
     */
    Byte MESSAGE_REQUEST = 3;

    /**
     * 消息回应指令标识
     */
    Byte MESSAGE_RESPONSE = 4;

    /**
     * 建群发送指令标识
     */
    Byte CREATE_GROUP_REQUEST = 5;

    /**
     * 建群发送指令标识
     */
    Byte CREATE_GROUP_RESPONSE = 6;

    /**
     * 建群发送指令标识
     */
    Byte JOIN_GROUP_REQUEST = 7;

    /**
     * 建群发送指令标识
     */
    Byte JOIN_GROUP_RESPONSE = 8;

    Byte QUIT_GROUP_REQUEST = 9;

    Byte QUIT_GROUP_RESPONSE = 10;

    Byte LIST_GROUP_REQUEST = 11;

    Byte LIST_GROUP_RESPONSE = 12;

    Byte GROUP_MESSAGE_REQUEST = 13;

    Byte GROUP_MESSAGE_RESPONSE = 14;

    Byte LOGOUT_REQUEST = 15;

    Byte LOGOUT_RESPONSE = 16;

    Byte HEARTBEAT_REQUEST = 17;

    Byte HEARTBEAT_RESPONSE = 18;


    Byte REGISTER_REQUEST = 19;

    Byte REGISTER_RESPONSE = 20;
}
