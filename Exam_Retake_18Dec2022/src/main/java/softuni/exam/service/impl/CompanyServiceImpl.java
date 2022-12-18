package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportCompanyDTO;
import softuni.exam.models.dto.ImportCompanyRootDTO;
import softuni.exam.models.entity.Company;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CompanyRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CompanyService;

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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final Path path = Path.of("src", "main", "resources", "files", "xml", "companies.xml");

    private final CompanyRepository companyRepository;
    private final Unmarshaller unmarshaller;
    private final Validator validator;
    private final ModelMapper modelMapper;
    private final CountryRepository countryRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, CountryRepository countryRepository) throws JAXBException {
        this.companyRepository = companyRepository;
        this.countryRepository = countryRepository;
        JAXBContext context = JAXBContext.newInstance(ImportCompanyRootDTO.class);
        this.unmarshaller = context.createUnmarshaller();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.modelMapper = new ModelMapper();

        this.modelMapper.addConverter(ctx -> LocalDate.parse(ctx.getSource(), DateTimeFormatter.ofPattern("yyyy-MM-dd")), String.class, LocalDate.class);
    }

    @Override
    public boolean areImported() {
        return this.companyRepository.count() > 0;
    }

    @Override
    public String readCompaniesFromFile() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importCompanies() throws IOException, JAXBException {
        ImportCompanyRootDTO companyDTOs = (ImportCompanyRootDTO) this.unmarshaller.unmarshal(
                new FileReader(path.toAbsolutePath().toString()));

        return companyDTOs
                .getCompanies()
                .stream()
                .map(this::importCompany)
                .collect(Collectors.joining("\n"));
    }

    private String importCompany(ImportCompanyDTO dto) {
        Set<ConstraintViolation<ImportCompanyDTO>> errors = this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return ("Invalid company");
        }

        Optional<Company> optCompany = this.companyRepository.findByName(dto.getName());

        if (optCompany.isPresent()) {
            return ("Invalid company");
        }

        Company company = this.modelMapper.map(dto, Company.class);

        Optional<Country> country = this.countryRepository.findById(dto.getCountryId());

        company.setCountry(country.get());

        this.companyRepository.save(company);

        String msg = String.format("Successfully imported company %s - %s", dto.getName(), dto.getCountryId());

        return msg;

    }
}
