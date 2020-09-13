package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.model.GenderType;
import com.thoughtworks.capability.gtb.restfulapidesign.model.Trainee;
import com.thoughtworks.capability.gtb.restfulapidesign.service.TraineeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/trainees")
@CrossOrigin
@Api("/trainees")
public class TraineeController {

    private final TraineeService traineeService;

    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @PostMapping("")
    @ApiOperation(value = "test")
    public ResponseEntity<Trainee> addTrainee(@RequestBody Trainee trainee) {
        return ResponseEntity.created(URI.create("")).body(traineeService.addTrainee(trainee));
    }

    @DeleteMapping("/{traineeId}")
    public ResponseEntity<Void> deleteTrainee(@PathVariable int traineeId) {
        traineeService.deleteTrainee(traineeId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<List<Trainee>> getTrainees(@RequestParam(required = false) GenderType gender) {
        return ResponseEntity.ok(traineeService.getTrainees(gender));
    }
}
