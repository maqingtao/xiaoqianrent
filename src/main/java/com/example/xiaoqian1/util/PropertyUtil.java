package com.example.xiaoqian1.util;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PropertyUtil {
    public static SimplePropertyPreFilter getFilter() {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("id");
        filter.getExcludes().add("mainID");
        filter.getExcludes().add("spaceDimID");
        filter.getExcludes().add("roomDimID");
        return filter;
    }

    /**
     * @Author: maqingtao
     * @description: 空调|有,网络|有 格式数据切割
     * @create: 2019/3/26
     **/

    public static Map<String, String> getSimpleMap(String str) {
        Map<String, String> map = new LinkedHashMap<>();
        String s[] = str.split(",");
        for (int i = 0; i < s.length; i++) {
            map.put(s[i],String.valueOf(i));
        }
        return map;
    }

    /**
     * @Author: maqingtao
     * @description: 获取房间类型
     * @create: 2019/4/2
     **/

    public static String getRoomDimType(String roomDimID) {
        Map<String, String> map = new HashMap<>();
        map.put("10", "合租");
        map.put("11", "整租");
        return map.get(roomDimID);
    }
}
