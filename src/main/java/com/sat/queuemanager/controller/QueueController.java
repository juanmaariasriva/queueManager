package com.sat.queuemanager.controller;

import com.sat.queuemanager.dto.QueueDTO;
import com.sat.queuemanager.model.Priority;
import com.sat.queuemanager.model.Queue;
import com.sat.queuemanager.service.QueueService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("queue")
public class QueueController {

    @Autowired
    private QueueService queueService;

    @PostMapping("/addQueue")
    public void addQueue(@RequestBody Queue queue){
        queueService.addQueue(queue);
    }

    @PostMapping("/addPriority")
    public void addPriority(@RequestBody Priority priority){
        queueService.addPriority(priority);
    }

    @GetMapping("/getPrioritys")
    @Operation(summary = "This method obtain all prioritys configurated")
    public List<Priority> getPrioritys(){
        return queueService.getPrioritys();
    }

    @GetMapping("/getQueue")
    @Operation(summary = "This method obtain all queue configurated")
    public List<QueueDTO> getQueue(){
        return queueService.getQueues();
    }
}
