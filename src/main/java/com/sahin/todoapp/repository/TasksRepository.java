package com.sahin.todoapp.repository;

import com.sahin.todoapp.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Tasks, Long> {
}
