package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportCityDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CityService;

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
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private Gson gson;
    //import javax.validation.Validator;
    private final Validator validator;
    private final ModelMapper modelMapper;
    private final CountryRepository countryRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.gson = new GsonBuilder().create();
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "json", "cities.json");
        return Files.readString(path);
    }

    @Override
    public String importCities() throws IOException {
        String json = this.readCitiesFileContent();

        ImportCityDTO[] importTeamsDTOS = this.gson.fromJson(json, ImportCityDTO[].class);

        return Arrays.stream(importTeamsDTOS)
                .map(this::importCity)
                .collect(Collectors.joining("\n"));

    }

    private String importCity(ImportCityDTO dto) {

        Set<ConstraintViolation<ImportCityDTO>> errors = this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return ("Invalid city");
        }

        Optional<City> optCity = this.cityRepository.findByCityName(dto.getCityName());

        if (optCity.isPresent()) {
            return ("Invalid city");
        }

        City city = this.modelMapper.map(dto, City.class);

        Optional<Country> country = this.countryRepository.findById(dto.getCountry());

        city.setCountry(country.get());

        this.cityRepository.save(city);

        return String.format("Successfully imported city %s - %d", city.getCityName(), city.getPopulation());

    }
}
