package com.soniq.service;

import com.soniq.entity.Building;
import com.soniq.entity.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author m.fatthi
 */
@RunWith(MockitoJUnitRunner.class)
public class OptimiserServiceTest {

    @InjectMocks
    private OptimiserImpl optimiser;

    @Test
    public void findOptimisationTest_IsOK() {
        Building building = Building.builder().senior(10).junior(6).rooms(Arrays.asList(35, 21, 17, 28)).build();
        List<Result> outputs = optimiser.findOptimisation(building);
        Result result1 = Result.builder().senior(3).junior(1).build();
        Result result2 = Result.builder().senior(1).junior(2).build();
        Result result3 = Result.builder().senior(2).junior(0).build();
        Result result4 = Result.builder().senior(1).junior(3).build();
        List<Result> expect = Arrays.asList(result1, result2, result3, result4);
        assertThat(expect).isEqualTo(outputs);
    }

    @Test
    public void findOptimisationTest_ByAnotherValue_IsOK() {
        Building building = Building.builder().senior(11).junior(6).rooms(Arrays.asList(24, 28)).build();
        List<Result> outputs = optimiser.findOptimisation(building);
        Result result1 = Result.builder().senior(2).junior(1).build();
        Result result2 = Result.builder().senior(2).junior(1).build();
        List<Result> expect = Arrays.asList(result1, result2);
        assertThat(expect).isEqualTo(outputs);
    }
}
