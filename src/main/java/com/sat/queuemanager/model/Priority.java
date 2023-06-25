package com.sat.queuemanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "priority")
@Setter @Getter
public class Priority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long priorityId;
    private String label;
    private Integer factor;
}
