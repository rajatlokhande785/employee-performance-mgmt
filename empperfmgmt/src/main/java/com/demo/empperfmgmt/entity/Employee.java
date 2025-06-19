package com.demo.empperfmgmt.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id @GeneratedValue
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private LocalDate dateOfJoining;
    private Double salary;

    @ManyToOne
    private Department department;

    @ManyToOne
    private Employee manager;
}
