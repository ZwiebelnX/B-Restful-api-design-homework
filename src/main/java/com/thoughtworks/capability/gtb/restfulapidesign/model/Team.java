package com.thoughtworks.capability.gtb.restfulapidesign.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Team {

    private int id;

    @NotEmpty
    private String name;

    private List<Trainee> traineeList;

    private String note;

}
