package com.spring.boot.projecttrackersystem.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    @NotEmpty
    @Size(min = 3, message = "iD must not be less than 3 characters")
    private String iD;

    @NotEmpty
    @Size(min = 8, message = "title must not be less than 8 characters")
    private String title;

    @NotEmpty
    @Size(min = 15, message = "description must not be less than 15 characters")
    private String description ;

    @NotEmpty
    @Pattern(regexp = "^(Not Started)|(in Progress)|(Completed)$",
            message = "must be one of the following: (Not Started, in Progress, or Completed) only")
    private String status;

    @NotEmpty
    @Size(min = 6)
    private String companyName;
}
