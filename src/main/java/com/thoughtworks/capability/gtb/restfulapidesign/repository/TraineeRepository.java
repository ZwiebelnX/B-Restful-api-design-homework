package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.model.GenderType;
import com.thoughtworks.capability.gtb.restfulapidesign.model.Trainee;
import com.thoughtworks.capability.gtb.restfulapidesign.model.exception.InitTraineeException;
import com.thoughtworks.capability.gtb.restfulapidesign.model.exception.TraineeNotFoundException;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

@Component
public class TraineeRepository {

    private static final Map<Integer, Trainee> traineeMap = new ConcurrentHashMap<>();

    private static final AtomicInteger atomicInteger = new AtomicInteger(1);

    public Trainee save(Trainee trainee) {
        if (trainee.getId() == 0 || !traineeMap.containsKey(trainee.getId())) {
            trainee.setId(getNextId());
        }
        traineeMap.put(trainee.getId(), trainee);
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

    public Trainee findById(int traineeId) {
        if (!traineeMap.containsKey(traineeId)) {
            throw new TraineeNotFoundException(traineeId);
        }
        return traineeMap.get(traineeId);
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

    private int getNextId() {
        return atomicInteger.getAndIncrement();
    }

    @PostConstruct
    public void initDefaultTrainees() {
        try {
            File file = new File("defaultList.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String lineString;
            while ((lineString = bufferedReader.readLine()) != null) {
                String[] traineeInfoStrings = lineString.split(",");
                int id = getNextId();
                Trainee trainee = Trainee.builder()
                    .id(id)
                    .name(traineeInfoStrings[0])
                    .gender(GenderType.valueOf(traineeInfoStrings[1]))
                    .build();
                traineeMap.put(id, trainee);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new InitTraineeException();
        }
    }
}
