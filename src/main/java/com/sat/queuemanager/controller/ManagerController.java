package com.sat.queuemanager.controller;

import com.sat.queuemanager.model.QueueDetail;
import com.sat.queuemanager.service.ManagerService;
import com.sat.queuemanager.service.QueueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("manager")
public class ManagerController {

    @Autowired
    private QueueService queueService;
    @Autowired
    private ManagerService managerService;
    @GetMapping("/getTicket/{priority}")
    @Operation(summary = "This method is to the CLIENT to obtain a ticket.")
    public String getTicket(@Parameter(description = "Could be: TE, CJ, EM, MA (those datas are from DB H2)") @PathVariable("priority") String priority){
        String ticket = queueService.getTicket(priority);
        return ticket;
    }

    @GetMapping("/getTickets")
    @Operation(summary = "This method is to obtain all ticket's in state waiting.")
    public List<QueueDetail> getTickets(){
        return managerService.getQueueDetails();
    }

    @DeleteMapping("/nextTicket/{cashier}")
    @Operation(summary = "This method is for the CASHIER to obtain the next Ticket.")
    public String nextTicket(@Parameter(description = "It's the Id of the cashier for now it's be any text") @PathVariable("cashier") String cashier){
        return managerService.nextTicket(cashier);
    }
}
