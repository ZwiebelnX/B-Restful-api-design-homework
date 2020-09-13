package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.model.Team;
import com.thoughtworks.capability.gtb.restfulapidesign.model.exception.TeamNameConflictException;
import com.thoughtworks.capability.gtb.restfulapidesign.model.exception.TeamNotFoundException;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TeamRepository {

    private static final Map<Integer, Team> teamMap = new ConcurrentHashMap<>();

    public Team save(Team team) {
        if (teamMap.values()
            .stream()
            .anyMatch(currentTeam -> currentTeam != team && currentTeam.getName().equals(team.getName()))) {
            throw new TeamNameConflictException(team.getName());
        }
        teamMap.put(team.getId(), team);
        return team;
    }

    public void save(List<Team> teamList) {
        teamList.forEach(team -> teamMap.put(team.getId(), team));
    }

    public Team findById(int id) {
        if (!teamMap.containsKey(id)) {
            throw new TeamNotFoundException(id);
        }
        return teamMap.get(id);
    }
}
