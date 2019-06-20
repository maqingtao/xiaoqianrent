package com.example.xiaoqian1.roomdetail;

import com.example.xiaoqian1.Xiaoqian1Application;
import com.example.xiaoqian1.roomdetail.bean.RoomDetail;
import com.example.xiaoqian1.roomdetail.service.RoomDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @Author: maqingtao
 * @description: 具体房源信息
 * @create: 2019/5/31
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Xiaoqian1Application.class)
public class TestRoomDetail {
    @Autowired
    RoomDetailService roomDetailService;

    //查询固定id的房源信息
    @Test
    public void testRoomDetail() {
        RoomDetail room = new RoomDetail();
        room.setMainID("1325482039");
        List<RoomDetail> roomDetails = roomDetailService.getDetail(room);
        assertTrue(roomDetails.size() > 0);
        if (roomDetails.size() > 0) {
            assertTrue(roomDetails.get(0).getSpaceDimType().equals("哈尔滨市"));
            assertTrue(roomDetails.get(0).getRoomArea().equals("53平方米"));
        }
    }
}
