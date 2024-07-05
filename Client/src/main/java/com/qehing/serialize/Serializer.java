package com.qehing.serialize;

import com.qehing.serialize.impl.JSONSerializer;

public interface Serializer {
    /**
     * json序列化
     */
    byte JSON_SERIALIZER = 1;
    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     */
    byte getSerializerAlg();

    /**
     * Java对象转二进制
     */
    byte[] serialize(Object obj);

    /**
     * 二进制转Java对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
