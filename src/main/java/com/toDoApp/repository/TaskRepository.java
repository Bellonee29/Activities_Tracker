package com.toDoApp.repository;

import com.toDoApp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    @Query("SELECT t FROM Task t WHERE t.user.id=?1")
    List<Task> findAllUserTasks(Long userId);

    @Query(value = "select * from task where user_id=?1 and status=?2",nativeQuery = true)
    List<Task> findByUserIdAndStatus(Long userId, String status);

}
