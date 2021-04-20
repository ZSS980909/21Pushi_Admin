//package com.ershiyi.controller;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Properties;
//
//public class test {
//    public static void main(String[] args) throws IOException {
//        //1、获取类加载器
//        ClassLoader cd = test.class.getClassLoader();
//        //2、用类加载器读取文件信息
//        InputStream in = cd.getResourceAsStream("static/parent/alipayCertPublicKey_RSA21.crt");
//        //3、创建数组，遍历文件信息
//        byte[] by = new byte[in.available()];
//        int len = in.read(by);
//        System.out.println(new String(by,0,len));
//    }
//}
