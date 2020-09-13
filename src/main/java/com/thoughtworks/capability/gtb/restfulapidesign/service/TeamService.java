package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.model.Team;
import com.thoughtworks.capability.gtb.restfulapidesign.model.Trainee;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.TeamRepository;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.TraineeRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    private final TraineeRepository traineeRepository;

    public TeamService(TeamRepository teamRepository, TraineeRepository traineeRepository) {
        this.teamRepository = teamRepository;
        this.traineeRepository = traineeRepository;
    }

    public List<Team> splitIntoTeam() {
        List<Team> teamList = new ArrayList<>();
        for (int id = 1; id <= 6; id++) {
            teamList.add(Team.builder().id(id).name("Team " + id).traineeList(new ArrayList<>()).build());
        }

        List<Trainee> traineeList = new ArrayList<>(traineeRepository.findAll());
        Collections.shuffle(traineeList);
        int teamIndex = 0;
        while (!traineeList.isEmpty()) {
            Trainee trainee = traineeList.remove(traineeList.size() - 1);
            teamList.get(teamIndex).getTraineeList().add(trainee);
            teamIndex++;
            teamIndex = teamIndex % teamList.size();
        }
        teamRepository.save(teamList);
        return teamList;
    }

    public Team changeTeamName(int teamId, Team team) {
        Team teamInRepo = teamRepository.findById(teamId);
        teamInRepo.setName(team.getName());
        return teamRepository.save(teamInRepo);
    }
}
