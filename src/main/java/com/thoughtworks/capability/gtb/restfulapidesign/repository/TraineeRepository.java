package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.model.GenderType;
import com.thoughtworks.capability.gtb.restfulapidesign.model.Trainee;
import com.thoughtworks.capability.gtb.restfulapidesign.model.exception.TraineeNotFoundException;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class TraineeRepository {

    private static final Map<Integer, Trainee> traineeMap = new ConcurrentHashMap<>();

    private static final AtomicInteger atomicInteger = new AtomicInteger(1);

    public Trainee save(Trainee trainee) {
        trainee.setId(atomicInteger.get());
        traineeMap.put(atomicInteger.get(), trainee);
        atomicInteger.set(atomicInteger.get() + 1);
        return trainee;
    }

    public void deleteById(int traineeId) {
        if (!traineeMap.containsKey(traineeId)) {
            throw new TraineeNotFoundException(traineeId);
        }
        traineeMap.remove(traineeId);
    }

    public List<Trainee> findAll() {
        List<Trainee> traineeList = new ArrayList<>();
        traineeMap.forEach((integer, trainee) -> traineeList.add(trainee));
        return traineeList;
    }

    public List<Trainee> findAllTraineeByGender(GenderType genderType) {
        List<Trainee> traineeList = new ArrayList<>();
        traineeMap.forEach(((integer, trainee) -> {
            if (trainee.getGender() == genderType) {
                traineeList.add(trainee);
            }
        }));
        return traineeList;
    }
}
