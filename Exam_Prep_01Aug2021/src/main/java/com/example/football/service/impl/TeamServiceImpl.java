package com.example.football.service.impl;

import com.example.football.models.dto.ImportTeamsDTO;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private Gson gson;
    private final Validator validator;
    private final ModelMapper modelMapper;
    private TownRepository townRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, TownRepository townRepository) {
        this.teamRepository = teamRepository;
        this.gson = new GsonBuilder().create();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.modelMapper = new ModelMapper();
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "json", "teams.json");
        return Files.readString(path);
    }

    @Override
    public String importTeams() throws IOException {

        String json = this.readTeamsFileContent();

        ImportTeamsDTO[] importTeamsDTOS = this.gson.fromJson(json, ImportTeamsDTO[].class);

        return Arrays.stream(importTeamsDTOS)
                .map(this::importTeam)
                .collect(Collectors.joining("\n"));

    }

    private String importTeam(ImportTeamsDTO dto) {
        Set<ConstraintViolation<ImportTeamsDTO>> errors = this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return ("Invalid Team");
        }

        Optional<Team> optTeam = this.teamRepository.findByName(dto.getName());

        if (optTeam.isPresent()) {
            return ("Invalid Team");
        }

        Team team = this.modelMapper.map(dto, Team.class);
        Optional<Town> town = this.townRepository.findByName(dto.getTownName());

        team.setTown(town.get());
        this.teamRepository.save(team);
        return String.format("Successfully imported Team %s - %d", team.getName(), team.getFanBase());
    }
}
