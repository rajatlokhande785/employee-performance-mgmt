package com.demo.empperfmgmt.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "performance_review")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceReview {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Employee employee;

    private LocalDate reviewDate;
    private Integer score;
    private String reviewComments;
}
