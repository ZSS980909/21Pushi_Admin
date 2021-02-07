package com.ershiyi.service;

import com.ershiyi.dto.LocationRequestDTO;

public interface LocationService {

    int getLocation(LocationRequestDTO localtionrequest);

    LocationRequestDTO queryLocation(LocationRequestDTO localtionrequest);
}
