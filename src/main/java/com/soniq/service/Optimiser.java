package com.soniq.service;

import com.soniq.entity.Building;
import com.soniq.entity.Result;

import java.util.List;

public interface Optimiser {

    List<Result> findOptimisation(Building building);
}
