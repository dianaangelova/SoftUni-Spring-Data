package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportJobDTO;
import softuni.exam.models.dto.ImportJobRootDTO;
import softuni.exam.models.entity.Company;
import softuni.exam.models.entity.Job;
import softuni.exam.repository.CompanyRepository;
import softuni.exam.repository.JobRepository;
import softuni.exam.service.JobService;

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
public class JobServiceImpl implements JobService {
    private final Path path = Path.of("src", "main", "resources", "files", "xml", "jobs.xml");
    private final JobRepository jobRepository;
    private final Unmarshaller unmarshaller;
    private final Validator validator;
    private final ModelMapper modelMapper;
    private final CompanyRepository companyRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, CompanyRepository companyRepository) throws JAXBException {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
        JAXBContext context = JAXBContext.newInstance(ImportJobRootDTO.class);
        this.unmarshaller = context.createUnmarshaller();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.jobRepository.count() > 0;
    }

    @Override
    public String readJobsFileContent() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importJobs() throws IOException, JAXBException {
        ImportJobRootDTO jobDTOs = (ImportJobRootDTO)
                this.unmarshaller.unmarshal (new FileReader(path.toAbsolutePath().toString()));

        return jobDTOs
                .getJobs()
                .stream()
                .map(this::importJob)
                .collect(Collectors.joining("\n"));

    }

    private String importJob(ImportJobDTO dto) {

        Set<ConstraintViolation<ImportJobDTO>> errors = this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return ("Invalid job");
        }

        Job job = this.modelMapper.map(dto, Job.class);

        Optional<Company> company = this.companyRepository.findById(dto.getCompanyId());

        job.setCompany(company.get());

        this.jobRepository.save(job);

        String msg = String.format("Successfully imported job %s",job.getTitle());

        return msg;
    }


    @Override
    public String getBestJobs() {

        double salaryFilter = 5000.00;
        double hoursaweekFilter = 30.00;

        List<Job> jobList = this.jobRepository.findBySalaryGreaterThanEqualAndHoursAWeekLessThanEqualOrderBySalaryDesc(salaryFilter, hoursaweekFilter);

        return jobList.stream()
                .map(Job::toString)
                .collect(Collectors.joining("\n"));

    }
}
