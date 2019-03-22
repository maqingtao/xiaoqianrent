package com.example.xiaoqian1.rent.service;

import com.example.xiaoqian1.rent.bean.RoomInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoomInformationService {
    public List<RoomInformation> getInformation();
    public Page<RoomInformation> getPageInformation(Pageable pageable);
    public Page<RoomInformation> getSelectInformation(Pageable pageable,RoomInformation r);
}
