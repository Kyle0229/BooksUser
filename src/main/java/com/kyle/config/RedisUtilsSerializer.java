package com.kyle.config;


import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class RedisUtilsSerializer implements RedisSerializer {
    //获得序列化的转换器
    Converter<Object ,byte[]> serializingConverter     =  new SerializingConverter();
    //获得反序列化的转换器
     Converter<byte[],Object> deserializingConverter=new DeserializingConverter();
private static final byte[] EMPTY_BYTE_ARRAY=new byte[0];
    //序列化转化器
    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if (o==null){
            return EMPTY_BYTE_ARRAY;
        }
        return serializingConverter.convert(o);
    }
    //反序列化
    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes==null){
            return null;
        }
        return deserializingConverter.convert(bytes);
    }
}
