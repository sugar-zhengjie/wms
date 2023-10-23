package com.zj.wms.utils;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.Session;

import java.io.*;

public class SerializableUtil {
    //序列化，把session对象转成字符串
    public static String serialize(Session session) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(session);
        return Base64.encodeToString(os.toByteArray());
    }
    //反序列化，把session字符串转session对象
    public static Session deSerialize(String sessionStr) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decode(sessionStr));
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (Session) ois.readObject();
    }
}
