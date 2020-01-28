package com.soniq.service;

import com.soniq.entity.Building;
import com.soniq.entity.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author R.fatthi
 */
@Service
public class OptimiserImpl implements Optimiser  {

    @Override
    public List<Result> findOptimisation(Building building) {
        List<Result> returns = new ArrayList<>();
        for (int room : building.getRooms()) {
            int initialSeniorCount = (int) Math.ceil((double) room / (double) building.getSenior());
            int initialJuniorCount = 0;
            List<Integer> results = optimise(building.getRooms(), building.getSenior(), building.getJunior(), room, getGreatestCommonDivisor(building.getSenior(), building.getJunior()), initialSeniorCount, initialJuniorCount, initialSeniorCount, initialJuniorCount);
            returns.add(Result.builder().senior(results.get(0)).junior(results.get(1)).build());
        }
        return returns;
    }

    private List<Integer> optimise(List<Integer> rooms, int senior, int junior, int roomSize, int minInterval, int previousSeniorCount, int previousJuniorCount, int currentSeniorCount, int currentJuniorCount) {
        // a linear equation of senior and junior variables
        int staffCapacity = calculateStaffCapacity(senior, currentSeniorCount, junior, currentJuniorCount);
        int spaceLeft = Math.abs(roomSize - staffCapacity);

        //do we have the space left lesser than what's allowed? and if so do we have equilibrium(staffCapacity >= room size)?
        if (spaceLeft <= minInterval && staffCapacity >= roomSize) {
            if (staffCapacity > roomSize) {
                int currentStaffCapacityWithOnlySeniors = calculateStaffCapacity(senior, currentSeniorCount, 0, 0);
                if (currentSeniorCount > 1 && currentStaffCapacityWithOnlySeniors > roomSize) {
                    return optimise(rooms, senior, junior, roomSize, minInterval, currentSeniorCount, currentJuniorCount, currentSeniorCount - 1, currentJuniorCount + 1);
                }
            }
            return Arrays.asList(currentSeniorCount, currentJuniorCount);
        }

        if (spaceLeft > Math.min(senior, junior) && staffCapacity < roomSize) {
            return Arrays.asList(previousSeniorCount, previousJuniorCount);
        }

        //if we have over assignment, then reduce the number of senior cleaners
        if (staffCapacity > roomSize && currentSeniorCount > 1) {
            return optimise(rooms, senior, junior, roomSize, minInterval, currentSeniorCount, currentJuniorCount, currentSeniorCount - 1, currentJuniorCount);
        }

        //assign a junior cleaner if not optimal
        if (staffCapacity < roomSize) {
            return optimise(rooms, senior, junior, roomSize, minInterval, currentSeniorCount, currentJuniorCount, currentSeniorCount, currentJuniorCount + 1);
        }

        return Arrays.asList(currentSeniorCount, currentJuniorCount);

        //  return listOf(1,3)
    }

    private int calculateStaffCapacity(int seniorCapacity, int seniorCount, int juniorCapacity, int juniorCount) {
        return (seniorCapacity * seniorCount) + (juniorCapacity * juniorCount);
    }

    private int getGreatestCommonDivisor(int firstNum, int secondNum) {
        if (secondNum == 0) {
            return firstNum;
        } else {
            return getGreatestCommonDivisor(secondNum, firstNum % secondNum);
        }
    }

}
