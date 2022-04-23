package com.example.helloworld.API;

import com.ejlchina.okhttps.HTTP;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class API {
    private static HTTP http = HTTP.builder().baseUrl("http://dataserver.ehanghai.cn").build();
    private static String appKey = "7bsb3c3ub2ugb17h";

    // 获取港口信息
    public static String getHarbour() {
        Map<String, String> map = new HashMap<>();
        map.put("appkey", appKey);
        // map.put("sign", sign);
        String timestamp = System.currentTimeMillis() + "";
        map.put("timestamp", timestamp);
        return http.sync("/route/groupRoute/harbour").addUrlPara(map).get().getBody().toString();
    }
    // 获取两港口路径信息
    public static String getPath(String appkey, String startcode, String endcode, String shipheight, String depth,
                                       String shipton, String lanename, String startname, String endname, String devicenum) {
        Map<String, String> map = new HashMap<>();
        map.put("appkey", appkey);
        map.put("startCode", startcode);
        map.put("endCode", endcode);
        map.put("shipHeight", shipheight);
        map.put("depth", depth);
        map.put("shipton", shipton);
        map.put("laneName", lanename);
        map.put("endName", endname);
        map.put("deviceNum", devicenum);
        String timestamp = System.currentTimeMillis() + "";
        map.put("timestamp", timestamp);
        return http.sync("/route/find").addUrlPara(map).get().getBody().toString();
    }
}
