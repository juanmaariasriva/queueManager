package com.sat.queuemanager.dto;

import com.sat.queuemanager.model.Priority;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class QueueDTO {

    private Long queueId;
    private Integer currentValue;
    private Integer endValue;
    private String label;
    private Priority priority;
}
