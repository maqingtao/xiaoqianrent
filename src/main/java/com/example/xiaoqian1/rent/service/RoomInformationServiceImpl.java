package com.example.xiaoqian1.rent.service;

import com.example.xiaoqian1.rent.bean.RoomInformation;
import com.example.xiaoqian1.rent.repository.RoomInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


@Service
public class RoomInformationServiceImpl implements RoomInformationService {
    @Autowired
    RoomInformationRepository informationRepository;

    @Override
    public List<RoomInformation> getInformation() {
        List<RoomInformation> roomList = informationRepository.findAll();
        return roomList;
    }

    /**
     * @Author: maqingtao
     * @description: 分页方法
     * @create: 2019/3/18
     **/

    @Override
    public Page<RoomInformation> getPageInformation(Pageable pageable) {
        return informationRepository.findAll(pageable);
    }

    /**
     * @Author: maqingtao
     * @description: 带条件的分页查询
     * @create: 2019/3/21
     **/

    @Override
    public Page<RoomInformation> getSelectInformation(Pageable pageable, RoomInformation r) {
        Page<RoomInformation> page = null;
        //添加无限制状态
        if (r.getRoomDimID().equals("-1") && r.getSpaceDimID().equals("-1")) {
            page = informationRepository.findAll(pageable);
        } else {
            page = informationRepository.findAll(getCondition(r), pageable);
        }
        return page;
    }

    private Specification<RoomInformation> getCondition(RoomInformation roomInformation) {
        Specification<RoomInformation> sp = new Specification<RoomInformation>() {
            @Override
            public Predicate toPredicate(Root<RoomInformation> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // 创建 Predicate
                Predicate predicate = criteriaBuilder.conjunction();
                // 组装条件
                //租房类型
                if (roomInformation.getRoomDimID() != null
                        &&!roomInformation.getRoomDimID().equals("-1")) {
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("roomDimID"), roomInformation.getRoomDimID()));
                }
                //地理位置
                if (roomInformation.getSpaceDimID() != null
                        &&!roomInformation.getSpaceDimID().equals("-1")) {
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("spaceDimID"), roomInformation.getSpaceDimID()));
                }
                return predicate;
            }
        };
        return sp;
    }

}
