package com.sat.queuemanager.repository;

import com.sat.queuemanager.model.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  QueueRepository extends JpaRepository<Queue, Long> {
}
