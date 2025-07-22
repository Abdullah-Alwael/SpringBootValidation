package com.spring.boot.eventmanagementsystem.Modle;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Event {
    @NotEmpty
    @Size(min = 3, message = "ID must not be less than 3 characters")
    private String iD;

    @NotEmpty
    @Size(min = 16, message = "description must not be less than 16 characters")
    private String description;

    @NotNull
    @Positive
    @Min(value = 26, message = "capacity must not be less than 26, and it must be a positive number")
    private int capacity;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent
    private LocalDate startDate, endDate;

}
