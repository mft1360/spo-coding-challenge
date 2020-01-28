package com.soniq.controller;

import com.soniq.dto.BuildingDTO;
import com.soniq.exception.BusinessException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BuildingValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return BuildingDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        BuildingDTO BuildingDTO = (BuildingDTO) object;
        if (BuildingDTO.getRooms().size() < 1) {
            throw new BusinessException("the number of structure must not be less than 1");
        }
        if (BuildingDTO.getRooms().size() > 100) {
            throw new BusinessException("the number of structure must not be more than 100");
        }
        BuildingDTO.getRooms().forEach(item -> {
            if (item < 1) {
                throw new BusinessException("the size of room must not be less than 1");
            }
            if (item > 100) {
                throw new BusinessException("the size of room must not be more than 100");
            }
        });
    }
}
