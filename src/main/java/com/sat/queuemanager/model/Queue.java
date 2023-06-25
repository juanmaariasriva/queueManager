package com.sat.queuemanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "queue")
@Setter @Getter
public class Queue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queueId;
    private Integer initValue;
    private Integer endValue;
    private Integer currentValue;
    private String label;
    private Long priority;
}
