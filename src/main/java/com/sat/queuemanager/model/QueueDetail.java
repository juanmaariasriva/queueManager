package com.sat.queuemanager.model;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class QueueDetail {

    private String ticket;
    private String cashier;
    private Integer priority;
}
