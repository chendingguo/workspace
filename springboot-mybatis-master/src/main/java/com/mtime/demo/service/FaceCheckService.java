package com.mtime.demo.service;

import com.baidu.aip.face.AipFace;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;


@Service
public class FaceCheckService {
    @Value("${baidu.app.id}")
    private String APP_ID;

    @Value("${baidu.api.key}")
    private String API_KEY;

    @Value("${baidu.secret.key}")
    private String SECRET_KEY;

    @Value("${image.path}")
    private String imagePath;

    public AipFace getAipFaceClient() {


        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
          client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        // client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
        return client;
    }

    public JSONObject matchFace() {
         AipFace client=getAipFaceClient();
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("ext_fields", "qualities");
        options.put("image_liveness", ",faceliveness");
        options.put("types", "7,13");

        //参数为本地图片路径列表
        String path1 = imagePath+"sample1.jpg";
        String path2 = imagePath+"sample2.jpg";
        ArrayList<String> images = new ArrayList<String>();
        images.add(path1);
        images.add(path2);
        JSONObject res = client.match(images, options);
        System.out.println(res.toString(2));
        return res;
    }


}
