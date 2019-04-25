package com.example.xiaoqian1.recommend;

import com.example.xiaoqian1.login.bean.User;
import com.example.xiaoqian1.recommend.Service.RoomRecommendService;
import com.example.xiaoqian1.rent.bean.RoomInformation;
import com.example.xiaoqian1.rent.repository.RoomInformationRepository;
import com.example.xiaoqian1.user.bean.MyCollect;
import com.example.xiaoqian1.user.repository.MyCollectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class RoomRecommendImpl implements RoomRecommendService {
    @Autowired
    MyCollectRepository myCollectRepository;
    @Autowired
    RoomInformationRepository roomInformationRepository;
    //获取redis方法模板
    @Autowired
    RedisTemplate<String, UserCollect> template;

    @Override
    public Set<UserCollect> getRecommend(User user) {
        List<MyCollect> allCollect = myCollectRepository.findMyCollect(user.getUserID());
        List<String> mainidList = new LinkedList<>();
        Map<String, Integer> divPrice = new LinkedHashMap<>();
        Map<String, Integer> divArea = new LinkedHashMap<>();
        Map<String, Integer> divType = new LinkedHashMap<>();
        List<RoomInformation> roomInformations = new LinkedList<>();
        //获取我的所有收藏的mainID
        if (allCollect != null && allCollect.size() > 0) {
            for (MyCollect mainID : allCollect) {
                mainidList.add(mainID.getMainID());
            }
        }
        //查询房屋收藏信息
        for (String mainID : mainidList) {
            roomInformations.add(roomInformationRepository.findRoomByMainID(mainID));
        }
        divPrice = getPriceCount(roomInformations);
        divArea = getAreaCount(roomInformations);
        divType = getTypeCount(roomInformations);
        //求各特征的偏好向量/
        Map<String, Float> pricePreferenceVector = new LinkedHashMap<>();
        Map<String, Float> areaPreferenceVector = new LinkedHashMap<>();
        Map<String, Float> typePreferenceVector = new LinkedHashMap<>();
        //得到收藏列表的总数目
        Integer lenth = allCollect.size();
        pricePreferenceVector = setPreferenceVector(divPrice, lenth);
        areaPreferenceVector = setPreferenceVector(divArea, lenth);
        typePreferenceVector = setPreferenceVector(divType, lenth);
        //这里做推荐的时候要过滤一下本地城市
        List<RoomInformation> allRooms = roomInformationRepository.findAll();
        for (int i = 0; i < allRooms.size(); i++) {
            if (!allRooms.get(i).getSpaceDimID().equals("230001")) {
                allRooms.remove(i);
            }
        }
        List<UserCollect> result = new LinkedList<>();
        //求每个房源与偏好向量的得分
        for (RoomInformation roomInformation : allRooms) {
            UserCollect userCollect = new UserCollect();
            List<RoomInformation> information = new LinkedList<>();
            information.add(roomInformation);
            Map<String, Integer> vector_price = getPriceCount(information);
            Map<String, Integer> vector_area = getAreaCount(information);
            Map<String, Integer> vector_type = getTypeCount(information);
            userCollect.setMainID(roomInformation.getMainID());
            userCollect.setPrice_cos(String.valueOf(cosine_similarity(pricePreferenceVector, vector_price)));
            userCollect.setArea_cos(String.valueOf(cosine_similarity(areaPreferenceVector, vector_area)));
            userCollect.setType_cos(String.valueOf(cosine_similarity(typePreferenceVector, vector_type)));
            result.add(userCollect);
        }
        setSimilar_item(result);
        Set<UserCollect> recommendRoom=getRoomScoreTopN(result, user.getUserID());
        return recommendRoom;
    }

    /**
     * @Author: maqingtao
     * @description: 将数据放入redis里，并获取TOPN评分的房源(获取前10)
     * @create: 2019/4/25
     **/
    public Set<UserCollect> getRoomScoreTopN(List<UserCollect> result, String userID) {
        template.delete(userID);
        for (UserCollect userCollect : result) {
            template.opsForZSet().add(userID, userCollect, Double.parseDouble(userCollect.getSimilar_item()));
        }
        Set<UserCollect> top10;
        top10 = template.opsForZSet().reverseRange(userID, 0, 9);
        return top10;
    }

    /**
     * @Author: maqingtao
     * @description: 计算加权相似度
     * @create: 2019/4/24
     **/
    public void setSimilar_item(List<UserCollect> userCollects) {
        for (UserCollect userCollect : userCollects) {
            DecimalFormat v = new DecimalFormat("###.00");
            Double result = 0.35 * Double.valueOf(userCollect.getPrice_cos())
                    + 0.35 * Double.valueOf(userCollect.getArea_cos())
                    + 0.30 * Double.valueOf(userCollect.getType_cos());
            userCollect.setSimilar_item(String.valueOf(Double.valueOf(v.format(result))));
        }
    }

    /**
     * @Author: maqingtao
     * @description: 计算两向量的余弦相似度
     * @create: 2019/4/24
     **/

    public double cosine_similarity(Map<String, Float> a, Map<String, Integer> b) {
        DecimalFormat v = new DecimalFormat("###.00");
        Float len_vector = 0.0f;
        for (String m : a.keySet()) {
            len_vector = len_vector + (a.get(m) * a.get(m));
        }
        double resule_lenth = Math.sqrt(len_vector);
        double vector_inner = 0;
        for (String k : b.keySet()) {
            if (b.get(k) == 1) {
                vector_inner = a.get(k);
            }
        }
        double result = Double.valueOf(v.format(vector_inner / resule_lenth));
        return result;
    }

    //计算特征的偏好向量
    public Map<String, Float> setPreferenceVector(Map<String, Integer> map, Integer lenth) {
        Map<String, Float> result = new LinkedHashMap<>();
        for (String m : map.keySet()) {
            DecimalFormat v = new DecimalFormat("###.00");
            Float value = ((Float.valueOf(map.get(m))) / lenth);
            result.put(m, Float.valueOf(v.format(value)));
        }
        return result;
    }

    /*对价格进行分组求各组数量*/
    public Map<String, Integer> getPriceCount(List<RoomInformation> roomInformations) {
        Map<String, Integer> divPriceCount = new LinkedHashMap<>();
        divPriceCount.put("FirstLevel", 0);
        divPriceCount.put("SecondLevel", 0);
        divPriceCount.put("ThirdLevel", 0);
        divPriceCount.put("FourthLevel", 0);
        divPriceCount.put("FifthLevel", 0);
        for (RoomInformation roomInformation : roomInformations) {
            Integer price = Integer.valueOf(roomInformation.getRoomPrice());
            if (price < 1000) {
                divPriceCount.put("FirstLevel", divPriceCount.get("FirstLevel") + 1);
            } else if (price >= 1000 && price < 1500) {
                divPriceCount.put("SecondLevel", divPriceCount.get("SecondLevel") + 1);
            } else if (price >= 1500 && price < 2000) {
                divPriceCount.put("ThirdLevel", divPriceCount.get("ThirdLevel") + 1);
            } else if (price >= 2000 && price < 2500) {
                divPriceCount.put("FourthLevel", divPriceCount.get("FourthLevel") + 1);
            } else if (price >= 2500) {
                divPriceCount.put("FifthLevel", divPriceCount.get("FifthLevel") + 1);
            }
        }
        return divPriceCount;
    }

    /*对面积进行分组求各组数量*/
    public Map<String, Integer> getAreaCount(List<RoomInformation> roomInformations) {
        Map<String, Integer> divAreaCount = new LinkedHashMap<>();
        divAreaCount.put("FirstLevel", 0);
        divAreaCount.put("SecondLevel", 0);
        divAreaCount.put("ThirdLevel", 0);
        divAreaCount.put("FourthLevel", 0);
        divAreaCount.put("FifthLevel", 0);
        for (RoomInformation roomInformation : roomInformations) {
            Integer area = Integer.valueOf(roomInformation.getRoomArea());
            if (area < 20) {
                divAreaCount.put("FirstLevel", divAreaCount.get("FirstLevel") + 1);
            } else if (area >= 20 && area < 30) {
                divAreaCount.put("SecondLevel", divAreaCount.get("SecondLevel") + 1);
            } else if (area >= 30 && area < 40) {
                divAreaCount.put("ThirdLevel", divAreaCount.get("ThirdLevel") + 1);
            } else if (area >= 40 && area < 50) {
                divAreaCount.put("FourthLevel", divAreaCount.get("FourthLevel") + 1);
            } else if (area >= 50) {
                divAreaCount.put("FifthLevel", divAreaCount.get("FifthLevel") + 1);
            }
        }
        return divAreaCount;
    }

    /*对房屋类型进行分组求各组数量*/
    public Map<String, Integer> getTypeCount(List<RoomInformation> roomInformations) {
        Map<String, Integer> divTypeCount = new LinkedHashMap<>();
        divTypeCount.put("FirstLevel", 0);
        divTypeCount.put("SecondLevel", 0);
        for (RoomInformation roomInformation : roomInformations) {
            Integer dimID = Integer.valueOf(roomInformation.getRoomDimID());
            if (dimID == 10) {
                divTypeCount.put("FirstLevel", divTypeCount.get("FirstLevel") + 1);
            } else {
                divTypeCount.put("SecondLevel", divTypeCount.get("SecondLevel") + 1);
            }
        }
        return divTypeCount;
    }
}
