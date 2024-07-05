package com.qehing.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.qehing.serialize.Serializer;
import com.qehing.serialize.SerializerAlg;

public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlg() {
        return SerializerAlg.JSON;
    }

    @Override
    public byte[] serialize(Object obj) {
        return JSON.toJSONBytes(obj);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
