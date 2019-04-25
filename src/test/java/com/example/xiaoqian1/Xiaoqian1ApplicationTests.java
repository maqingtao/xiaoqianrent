package com.example.xiaoqian1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Xiaoqian1ApplicationTests {
//    @Autowired
//    private RedisTemplate<String,Tuser> redisTemplate;
//
//    @Test
//    public void test() throws Exception {
//
//        // 保存对象
//        Tuser user = new Tuser("w", 20);
//        redisTemplate.opsForValue().set(user.getUsername(), user);
//
//        user = new Tuser("q", 30);
//        redisTemplate.opsForValue().set(user.getUsername(), user);
//
//        user = new Tuser("s", 40);
//        redisTemplate.opsForValue().set(user.getUsername(), user);
//        System.out.print(redisTemplate.opsForValue().get("s"));
//        Assert.assertEquals(20, redisTemplate.opsForValue().get("w").getAge().longValue());
//        Assert.assertEquals(30, redisTemplate.opsForValue().get("q").getAge().longValue());
//        Assert.assertEquals(40, redisTemplate.opsForValue().get("s").getAge().longValue());
//
//    }
    @Test
    public void contextLoads() {
    }

}

