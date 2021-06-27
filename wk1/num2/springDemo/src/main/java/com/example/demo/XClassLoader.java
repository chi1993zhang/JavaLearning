package com.example.demo;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class XClassLoader extends ClassLoader {
    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = new XClassLoader();
        Class<?> clazz = classLoader.loadClass("Hello");
        Object instance = clazz.getDeclaredConstructor().newInstance();
        Method method = clazz.getMethod("hello");
        method.invoke(instance);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(name + ".xlass");
        int length = 0;
        try {
            length = inputStream.available();
            byte[] byteArray = new byte[length];
            inputStream.read(byteArray);
            byte[] classBytes = decode(byteArray);
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] decode(byte[] byteArray) {
        byte[] targetArray = new byte[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            targetArray[i] = (byte) (255 - byteArray[i]);
        }
        return targetArray;
    }
}
