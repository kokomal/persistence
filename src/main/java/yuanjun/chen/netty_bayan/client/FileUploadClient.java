/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package yuanjun.chen.netty_bayan.client;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @ClassName: FileUploadClient
 * @Description: 双向netty文件收发demo
 * @author: 陈元俊
 * @SpecialThanksTo: nayan
 * @date: 2018年9月17日 下午1:06:50
 */
public class FileUploadClient {
    public static final int CLIENT_COUNT = 1;

    public static void main(String args[]) {

        for (int i = 0; i < CLIENT_COUNT; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        uploadFile();
                    } catch (Exception ex) {
                        Logger.getLogger(FileUploadClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }).start();

        }

    }

    private static void uploadFile() throws Exception {
        File file = new File("d:\\tmp\\3.jpg");

        HttpEntity httpEntity = MultipartEntityBuilder.create()
                .addBinaryBody("file", file, ContentType.create("image/jpeg"), file.getName()).build();

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://localhost:8080");

        httppost.setEntity(httpEntity);
        System.out.println("executing request " + httppost.getRequestLine());

        CloseableHttpResponse response = httpclient.execute(httppost);

        System.out.println("----------------------------------------");
        System.out.println(response.getStatusLine());
        HttpEntity resEntity = response.getEntity();
        if (resEntity != null) {
            System.out.println("Response content length: " + resEntity.getContentLength());
        }

        EntityUtils.consume(resEntity);

        response.close();
    }
}
