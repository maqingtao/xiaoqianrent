package com.example.xiaoqian1.user;

import com.example.xiaoqian1.TestUtils;
import com.example.xiaoqian1.Xiaoqian1Application;
import com.example.xiaoqian1.admin.bean.ReviewRoomInformation;
import com.example.xiaoqian1.roomdetail.bean.RoomDetail;
import com.example.xiaoqian1.roomdetail.service.RoomDetailService;
import com.example.xiaoqian1.user.bean.MyCollect;
import com.example.xiaoqian1.user.bean.PersonInformation;
import com.example.xiaoqian1.user.repository.MyCollectRepository;
import com.example.xiaoqian1.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Xiaoqian1Application.class)
public class UserTest {
    @Autowired
    UserService userService;
    @Autowired
    RoomDetailService roomDetailService;
    @Autowired
    MyCollectRepository myCollectRepository;
    //测试我的发布数据查询
    @Test
    public void testMyPublish()
    {
        List<ReviewRoomInformation> list=userService.getMyPublish(TestUtils.getReviewRoom());
        assertTrue(list.size()>0);
        //判断查询的房源是否为该用户发布的
        for (ReviewRoomInformation r:list)
        {
            assertTrue(r.getUserID().equals("14"));
        }
    }

    //测试我的收藏房源数据,以及对应详细房源数据是否存在
    @Test
    public void testMyCollect()
    {
        List<MyCollect> lists=myCollectRepository.findMyCollect(TestUtils.getReviewRoom().getUserID());
        assertTrue(lists.size()>0);
        for (MyCollect myCollect:lists)
        {
            RoomDetail roomDetail=new RoomDetail();
            roomDetail.setMainID(myCollect.getMainID());
            List<RoomDetail> roomDetails = roomDetailService.getDetail(roomDetail);
            assertTrue(roomDetails.size()>0);
        }
    }
    //测试个人信息是否存在
    @Test
    public void testPersonInformation()
    {
        PersonInformation personInformation=new PersonInformation();
        personInformation.setUserID(TestUtils.getReviewRoom().getUserID());
       PersonInformation re=userService.getPersonInformation(personInformation);
       assertTrue(re.getUserID().equals(TestUtils.getReviewRoom().getUserID()));
       assertTrue(re.getUserName()!=null);
    }

}
