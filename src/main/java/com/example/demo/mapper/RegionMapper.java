package com.example.demo.mapper;

import com.example.demo.model.dto.RegionDTO;
import com.example.demo.model.entity.Region;
import com.example.demo.model.entity.Region_;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class RegionMapper {

    public abstract Region toRegion(RegionDTO dto);

    public void action() {
        String s = Region_.ID;
        System.out.println(s);
    }

}
