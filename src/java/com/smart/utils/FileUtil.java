package com.smart.utils;


import java.io.*;

public class FileUtil {

    private static final String DEFAULT_CHARSET = "utf-8";
    private static final int DEFAULT_BUFFER_SIZE = 16 * 1024;

    /**
     * 读取指定编码方式的文件
     * @param file
     * @return
     * @throws IOException
     */
    public static String readFile(File file) throws IOException {
        return readFile(file, DEFAULT_CHARSET);
    }

    /**
     * 读取文件内容
     * @param file
     * @param charsetName 编码方式
     * @return
     * @throws IOException
     */
    public static String readFile(File file, String charsetName) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        InputStream inData = new BufferedInputStream(inputStream);
        ByteArrayOutputStream outData = new ByteArrayOutputStream();
        byte[] buffer = new byte[10240];
        for (int numRead; (numRead = inData.read(buffer)) > 0;) {
            outData.write(buffer, 0, numRead);
        }
        return outData.toString(charsetName);
    }

    /**
     * 复制文件
     * @param src
     * @param dist
     */
    public static void copyFile(File src, File dist) {
        int BUFFER_SIZE = 16 * 1024;
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
            OutputStream out = new BufferedOutputStream(new FileOutputStream(dist), BUFFER_SIZE);
            byte[] buffer = new byte[BUFFER_SIZE];
            int len;
            while ((len = in.read(buffer, 0, BUFFER_SIZE)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (Throwable t) {
            throw new RuntimeException(t.getMessage(), t);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(readFile(new File("extra/log4j.properties"), "utf-8"));
    }
}
