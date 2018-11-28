package com.daniel.fang.house_service.mapper;

import java.util.List;

import com.daniel.fang.house_service.model.City;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CityMapper {
  
  public List<City> selectCitys(City city);

}
