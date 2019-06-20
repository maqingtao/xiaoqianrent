package com.example.xiaoqian1.rent;

import com.example.xiaoqian1.TestUtils;
import com.example.xiaoqian1.Xiaoqian1Application;
import com.example.xiaoqian1.rent.bean.RoomInformation;
import com.example.xiaoqian1.rent.service.RoomInformationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Xiaoqian1Application.class)
public class RoomRentTest {
    @Autowired
    RoomInformationService roomInformationService;

    //判断房源查询数据不为空
    @Test
    public void testAllRoom() {
        assertTrue(roomInformationService.getInformation().size() > 0);
    }

    //查询分页房源数据是否满足查询条件要求
    @Test
    public void testSelectRoom() {
        PageRequest pageRequest = PageRequest.of(0, 8);
        Page<RoomInformation> r = roomInformationService.getSelectInformation(pageRequest, TestUtils.getRoomInformation());
        List<RoomInformation> roomInformations = r.getContent();
        RoomInformation test=roomInformations.get(0);
        assertTrue(Integer.parseInt(test.getRoomArea())>=10);
        assertTrue(Integer.parseInt(test.getRoomArea())<=100);
        assertTrue(Integer.parseInt(test.getRoomPrice())>=1000);
        assertTrue(Integer.parseInt(test.getRoomPrice())<=1500);
        assertTrue(test.getRoomDimID().equals("10"));
        assertTrue(test.getSpaceDimID().equals("230001"));

    }
}
