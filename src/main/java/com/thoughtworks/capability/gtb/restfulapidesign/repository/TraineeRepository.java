package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.model.Trainee;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class TraineeRepository {

    private static final List<Trainee> traineeList = new ArrayList<>();

    private static final AtomicInteger atomicInteger = new AtomicInteger(1);

    public Trainee save(Trainee trainee) {
        trainee.setId(atomicInteger.get());
        traineeList.add(trainee);
        atomicInteger.set(atomicInteger.get() + 1);
        return trainee;
    }
}
