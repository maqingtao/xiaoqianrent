package com.example.xiaoqian1.recommend;

import com.example.xiaoqian1.login.bean.User;
import com.example.xiaoqian1.recommend.Service.RoomRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


/**
 * @Author: maqingtao
 * @description: 获取推荐信息
 * @create: 2019/4/19
 **/

@RestController
@RequestMapping("/api")
public class RoomRecommendController {
    @Autowired
    RoomRecommendService roomRecommendService;

    @RequestMapping(value = "/myRoomRecommend", method = RequestMethod.POST, consumes = "application/json")
    public List<UserCollect> getRegister(@RequestBody User user) {
        List<UserCollect> all = new LinkedList<>();
        Set<UserCollect> result = roomRecommendService.getRecommend(user);
        Iterator<UserCollect> userCollectIterator = result.iterator();
        while (userCollectIterator.hasNext()) {
            all.add(userCollectIterator.next());
        }
        return all;
    }
}
