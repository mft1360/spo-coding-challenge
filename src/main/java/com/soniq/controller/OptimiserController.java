package com.soniq.controller;

import com.soniq.aspect.CheckBindingResult;
import com.soniq.dto.BuildingDTO;
import com.soniq.dto.ResultDTO;
import com.soniq.mapper.Mapper;
import com.soniq.service.Optimiser;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author R.fatthi
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/optimiser")
public class OptimiserController {

    private final Optimiser optimiser;

    private final BuildingValidator buildingValidator;

    private final Mapper mapper;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(buildingValidator);
    }

    @PostMapping
    @CheckBindingResult
    public List<ResultDTO> optimise(@RequestBody @Valid BuildingDTO buildingDTO, BindingResult bindingResult) {
        return mapper.toResultDTOs(optimiser.findOptimisation(mapper.toBuilding(buildingDTO)));
    }

}
