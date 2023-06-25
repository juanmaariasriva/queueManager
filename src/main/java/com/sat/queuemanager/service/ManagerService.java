package com.sat.queuemanager.service;

import com.sat.queuemanager.model.QueueDetail;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class ManagerService {

    private List<QueueDetail> queueDetails = new ArrayList<>();

    public String nextTicket(String cashier){
        String response = "";
        if(!queueDetails.isEmpty()) {
            QueueDetail detail = queueDetails.get(0);
            response = detail.getTicket();
            // TODO: Guardar en historico con el cajero y con la hora de inicio para hacer el calculo del tiempo
            queueDetails.remove(0);
        }
        return response;
    }

    public void addWaitingQueue(QueueDetail queueDetail){
        List<QueueDetail> response = new ArrayList<>();
        boolean modify = false;
        if(queueDetails.isEmpty()){
            response.add(queueDetail);
        }else{
            for (QueueDetail detail : queueDetails) {
                if(!modify) {
                    if (queueDetail.getPriority() < detail.getPriority()) {
                        response.add(queueDetail);
                        modify = true;
                    }
                    response.add(detail);
                }else{
                    response.add(detail);
                }
            }
            if(!modify){
                response.add(queueDetail);
            }
        }
        queueDetails.clear();
        queueDetails.addAll(response);
    }
}
