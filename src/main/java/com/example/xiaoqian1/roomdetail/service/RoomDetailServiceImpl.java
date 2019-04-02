package com.example.xiaoqian1.roomdetail.service;
import com.example.xiaoqian1.roomdetail.bean.RoomDetail;
import com.example.xiaoqian1.roomdetail.repository.RoomDetailRepository;
import com.example.xiaoqian1.util.PropertyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

@Service
public class RoomDetailServiceImpl implements RoomDetailService{
    @Autowired
    RoomDetailRepository detailRepository;
    @Override
    public List<RoomDetail> getDetail(RoomDetail room) {
        List<RoomDetail> list=detailRepository.findAll(getCondition(room));
        return list;
    }

    @Override
    public Map<String, String> getFacility(RoomDetail room) {
        List<RoomDetail> list=detailRepository.findAll(getCondition(room));
        String facility=list.get(0).getFacility();
        Map<String,String> result=PropertyUtil.getSimpleMap(facility);
        return result;
    }


    private Specification<RoomDetail> getCondition(RoomDetail r) {
        Specification<RoomDetail> sp = new Specification<RoomDetail>() {
            @Override
            public Predicate toPredicate(Root<RoomDetail> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // 创建 Predicate
                Predicate predicate = criteriaBuilder.conjunction();
                // 组装条件
                //租房类型
                if (r.getRoomDimID() != null
                        && !r.getRoomDimID().equals("-1")) {
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("roomDimID"), r.getRoomDimID()));
                }
                //地理位置
                if (r.getSpaceDimID() != null
                        && !r.getSpaceDimID().equals("-1")) {
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("spaceDimID"), r.getSpaceDimID()));
                }
                if (r.getMainID()!=null)
                {
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("mainID"), r.getMainID()));
                }
                return predicate;
            }
        };
        return sp;
    }

}
