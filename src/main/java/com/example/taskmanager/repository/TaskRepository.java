package com.example.taskmanager.repository;

import com.example.taskmanager.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface TaskRepository extends CrudRepository<Task, Long> {
    Iterable<Task> findAllByDoneTrue();
    Iterable<Task> findAllByDoneFalse();
}
