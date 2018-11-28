package com.daniel.fang.house_service.service;

import java.util.List;

import com.daniel.fang.house_service.mapper.CityMapper;
import com.daniel.fang.house_service.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
  
  @Autowired
  private CityMapper cityMapper;
  
  public List<City> getAllCitys(){
    City query = new City();
    return cityMapper.selectCitys(query);
  }

}
