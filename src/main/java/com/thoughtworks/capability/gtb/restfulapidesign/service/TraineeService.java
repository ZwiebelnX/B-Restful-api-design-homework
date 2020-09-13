package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.model.GenderType;
import com.thoughtworks.capability.gtb.restfulapidesign.model.Trainee;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.TraineeRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraineeService {

    private final TraineeRepository traineeRepository;

    public TraineeService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    public Trainee addTrainee(Trainee trainee) {
        traineeRepository.save(trainee);
        return trainee;
    }

    public void deleteTrainee(int traineeId) {
        traineeRepository.deleteById(traineeId);
    }

    public List<Trainee> getTrainees(GenderType genderType) {
        return genderType == null ? traineeRepository.findAll() : traineeRepository.findAllTraineeByGender(genderType);
    }
}
