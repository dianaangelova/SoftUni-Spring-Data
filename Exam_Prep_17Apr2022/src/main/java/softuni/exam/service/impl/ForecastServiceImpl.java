package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportForecastDTO;
import softuni.exam.models.dto.ImportForecastRootDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.DayOfWeekEnum;
import softuni.exam.models.entity.Forecast;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.ForecastService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ForecastServiceImpl implements ForecastService {

    Path path = Path.of("src", "main", "resources", "files", "xml", "forecasts.xml");

    private final ForecastRepository forecastRepository;
    private final Unmarshaller unmarshaller;
    private final Validator validator;
    private final ModelMapper modelMapper;
    private final CityRepository cityRepository;


    @Autowired
    public ForecastServiceImpl(ForecastRepository forecastRepository, CityRepository cityRepository) throws JAXBException {
        this.forecastRepository = forecastRepository;
        this.cityRepository = cityRepository;
        JAXBContext context = JAXBContext.newInstance(ImportForecastRootDTO.class);
        this.unmarshaller = context.createUnmarshaller();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.modelMapper = new ModelMapper();

//        this.modelMapper.addConverter((Converter<String, LocalTime>)
//                context1 -> LocalTime.parse(context1.getSource(), DateTimeFormatter.ofPattern("hh:mm:ss")));

    }


    @Override
    public boolean areImported() {
        return this.forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        ImportForecastRootDTO forecastDTOs = (ImportForecastRootDTO)
                this.unmarshaller.unmarshal(new FileReader(path.toAbsolutePath().toString()));

        return forecastDTOs
                .getForecasts()
                .stream()
                .map(this::importForecast)
                .collect(Collectors.joining("\n"));

    }

    private String importForecast(ImportForecastDTO dto) {

        Set<ConstraintViolation<ImportForecastDTO>> errors = this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return ("Invalid forecast");
        }

        Optional<Forecast> optForecast = this.forecastRepository.findByDayOfWeekAndCityId(dto.getDayOfWeek(), dto.getCity());

        if (optForecast.isPresent()) {
            return ("Invalid forecast");
        }

        Forecast forecast = this.modelMapper.map(dto, Forecast.class);

        Optional<City> optCity = this.cityRepository.findById(dto.getCity());

        forecast.setCity(optCity.get());

        this.forecastRepository.save(forecast);

        String msg = String.format("Successfully import forecast %s - %.2f", forecast.getDayOfWeek(), forecast.getMaxTemperature());

        return msg;
    }

    @Override
    public String exportForecasts() {

        DayOfWeekEnum dayOfWeekFilter = DayOfWeekEnum.valueOf("SUNDAY");
        int lessThanCitizens = 150000;

        List<Forecast> forecasts = this.forecastRepository.findByDayOfWeekAndCityPopulationLessThanOrderByMaxTemperatureDescIdAsc(dayOfWeekFilter, lessThanCitizens);

        return forecasts.stream()
                .map(Forecast::toString)
                .collect(Collectors.joining("\n"));

    }
}
