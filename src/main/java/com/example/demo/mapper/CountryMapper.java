package com.example.demo.mapper;

import com.example.demo.model.dto.CountryDTO;
import com.example.demo.model.dto.CountryDTOInt;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class CountryMapper {

    public abstract CountryDTO toCountryDTO(CountryDTOInt countryDTOInt);

}
