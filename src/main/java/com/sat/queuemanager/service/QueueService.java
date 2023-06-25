package com.sat.queuemanager.service;

import com.sat.queuemanager.dto.QueueDTO;
import com.sat.queuemanager.model.Priority;
import com.sat.queuemanager.model.Queue;
import com.sat.queuemanager.model.QueueDetail;
import com.sat.queuemanager.repository.PriorityRepository;
import com.sat.queuemanager.repository.QueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QueueService {

    @Autowired
    private QueueRepository queueRepository;
    @Autowired
    private PriorityRepository priorityRepository;
    @Autowired
    private ManagerService managerService;

    public void addQueue(Queue queue){
        queueRepository.saveAndFlush(queue);
    }

    public Integer addCurrent(Long queueId){
        Integer response = -0;
        Queue queue = queueRepository.findById(queueId).get();
        // TODO: validar limites para resetear el valor
        response = queue.getCurrentValue() + 1;
        queue.setCurrentValue(response);
        queueRepository.saveAndFlush(queue);
        return response;
    }

    public void addPriority(Priority priority){
        priorityRepository.saveAndFlush(priority);
    }

    public List<Priority> getPrioritys(){
        return priorityRepository.findAll();
    }

    public List<QueueDTO> getQueues(){
        List<QueueDTO> response = new ArrayList<>();
        List<Queue> queues = queueRepository.findAll();
        for (Queue queue: queues) {
            Priority priority = priorityRepository.findById(queue.getPriority()).get();
            QueueDTO queueDTO = new QueueDTO();
            queueDTO.setQueueId(queue.getQueueId());
            queueDTO.setLabel(queue.getLabel());
            queueDTO.setPriority(priority);
            queueDTO.setCurrentValue(queue.getCurrentValue());
            queueDTO.setEndValue(queue.getEndValue());
            response.add(queueDTO);
        }
        return response;
    }

    public String getTicket(String priority){
        String response= "";
        List<QueueDTO> queues = getQueues();
        for (QueueDTO queue: queues){
            if(queue.getPriority().getLabel().equalsIgnoreCase(priority)){
                response = getNumberStr(addCurrent(queue.getQueueId()), queue.getEndValue()) + " " + queue.getPriority().getLabel();
                QueueDetail detail = new QueueDetail();
                detail.setTicket(response);
                detail.setPriority(queue.getPriority().getFactor());
                managerService.addWaitingQueue(detail);
                break;
            }
        }
        return response;
    }

    private String getNumberStr(Integer number, Integer limit){
        String response = "";
        int amountNumber = String.valueOf(number).length();
        int amountLimit = String.valueOf(limit).length();
        int amount = amountLimit - amountNumber;
        if(amount > 0){
            for (int i = 0; i < amount; i++) {
                response += "0";
            }
        }
        return response + number;
    }
}
