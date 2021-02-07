package com.ershiyi.service.Impl;

import com.ershiyi.dto.LocationRequestDTO;
import com.ershiyi.mapper.LocationMapper;
import com.ershiyi.service.LocationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {
    public static Log log = LogFactory.getLog(LocationServiceImpl.class);
    @Autowired
    private LocationMapper mapper;


    @Override
    public int getLocation(LocationRequestDTO localtionrequest) {
            log.info("学生编号"+localtionrequest.getStudenterId()+"接收定位纬度"+localtionrequest.getLatiTude()
                    +"接收的定位经度为"+localtionrequest.getLongiTude()+"此学生定位为"+localtionrequest.getProvince()+
                    localtionrequest.getCity()+localtionrequest.getPosition());
        return mapper.getLocation(localtionrequest);
    }

    @Override
    public LocationRequestDTO queryLocation(LocationRequestDTO localtionrequest) {
        return mapper.queryLocation(localtionrequest);
    }
}
