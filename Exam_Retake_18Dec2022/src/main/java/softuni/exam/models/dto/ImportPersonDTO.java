package softuni.exam.models.dto;

import softuni.exam.models.entity.StatusTypeEnum;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ImportPersonDTO {

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 2, max = 30)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;

    @Size(min = 2, max = 13)
    private String phone;

    @NotNull
    private StatusTypeEnum statusType;

    @NotNull
    private long country;

    public ImportPersonDTO() {
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public StatusTypeEnum getStatusType() {
        return statusType;
    }

    public long getCountry() {
        return country;
    }
}
