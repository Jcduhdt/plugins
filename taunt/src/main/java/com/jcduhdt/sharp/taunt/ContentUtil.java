package com.jcduhdt.sharp.taunt;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class ContentUtil {

    public static String getContent() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> entity = restTemplate.getForEntity("http://www.52my1.cn/dujitang/api/", String.class);
            HttpStatus statusCode = entity.getStatusCode();
            if (statusCode.is2xxSuccessful()) {
                JSONObject jsonObject = JSONObject.parseObject(entity.getBody());
                String content = jsonObject.getJSONObject("data").getJSONObject("content").getString("content");
                return content;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }
}
