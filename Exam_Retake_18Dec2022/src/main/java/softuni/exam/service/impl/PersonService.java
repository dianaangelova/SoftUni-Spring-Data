package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportPersonDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.models.entity.Person;
import softuni.exam.repository.CountryRepository;
import softuni.exam.repository.PersonRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonService implements softuni.exam.service.PersonService {

    private final PersonRepository personRepository;
    private Gson gson;
    private final Validator validator;
    private final ModelMapper modelMapper;
    private final CountryRepository countryRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, CountryRepository countryRepository) {
        this.personRepository = personRepository;
        this.countryRepository = countryRepository;
        this.gson = new GsonBuilder().create();
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
        this.modelMapper = new ModelMapper();
    }


    @Override
    public boolean areImported() {
        return this.personRepository.count()>0;
    }

    @Override
    public String readPeopleFromFile() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "json", "people.json");
        return Files.readString(path);

    }

    @Override
    public String importPeople() throws IOException, JAXBException {
        String json = this.readPeopleFromFile();

        ImportPersonDTO[] importPersonDTOS = this.gson.fromJson(json, ImportPersonDTO[].class);

        return Arrays.stream(importPersonDTOS)
                .map(this::importPerson)
                .collect(Collectors.joining("\n"));

    }

    private String importPerson(ImportPersonDTO dto) {

        Set<ConstraintViolation<ImportPersonDTO>> errors = this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return ("Invalid person");
        }

        Optional<Person> optPerson = this.personRepository.findByFirstNameOrEmailOrPhone(dto.getFirstName(), dto.getEmail(), dto.getPhone());

        if (optPerson.isPresent()) {
            return ("Invalid person");
        }

        Person person = this.modelMapper.map(dto, Person.class);

        Optional<Country> country = this.countryRepository.findById(dto.getCountry());

        person.setCountry(country.get());

        this.personRepository.save(person);

        return String.format("Successfully imported person %s %s", person.getFirstName(), person.getLastName());

    }
}
