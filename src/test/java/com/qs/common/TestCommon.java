package com.qs.common;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config/applicationContext.xml"})
public class TestCommon {

    /**
     * 获取远程服务器中图片，并保存至本地
     */
    @org.junit.Test
    public void testGetFile() throws IOException {
        URL url = new URL("https://idsafe-auth.udcredit.com/front/4.0/api/file_download/platform/web?name=i_20180917191934591145316.jpg&param=8664FD3090BD81E36A66A4F11F1320FF67E22514F9A54F42FC1027F46493DBCEC90FE41D78");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(5 * 1000);
        InputStream inputStream = conn.getInputStream();//通过输入流获取图片数据

        File file = new File("/Users/qinyupeng/Desktop/aa.jpg");
        OutputStream outputStream = new FileOutputStream(file);
        int len = 0;
        byte[] buf = new byte[1024];
        while (-1 != (len = (inputStream.read(buf)))) {
            outputStream.write(buf, 0, len);
            outputStream.flush();
        }

        outputStream.close();
        inputStream.close();
    }
}
