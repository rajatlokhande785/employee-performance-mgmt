package com.demo.empperfmgmt.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
public class EmployeeDetailDto {

    @NotNull(message = "ID is required")
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Department name is required")
    private String departmentName;

    @NotNull(message = "Project names cannot be null")
    private List<@NotBlank(message = "Project name cannot be blank") String> projectNames;

    @NotNull(message = "Recent reviews cannot be null")
    private List<@NotNull ReviewDto> recentReviews;

    @Data
    public static class ReviewDto {
        @NotNull(message = "Review date is required")
        private LocalDate reviewDate;

        @NotNull(message = "Score is required")
        private Integer score;

        @NotBlank(message = "Review comments are required")
        private String reviewComments;
    }
}
