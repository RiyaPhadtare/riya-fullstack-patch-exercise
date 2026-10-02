package com.internal.tasktracker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Search tasks by term and optional status filter
    @Query(value = "SELECT * FROM tasks WHERE archived = FALSE AND ((:idSearch = TRUE "
                 + "AND CAST(id AS VARCHAR) = :id) OR (:idSearch = FALSE AND "
                 + "(LOWER(title) LIKE :term OR LOWER(description) LIKE :term))) "
                 + "AND (:status IS NULL OR status = :status) "
                 + "ORDER BY created_at ASC LIMIT :pageSize OFFSET :offset",
           nativeQuery = true)
    List<Task> searchTasks(@Param("term") String term, @Param("id") String id,
                           @Param("idSearch") boolean idSearch,
                           @Param("status") String status,
                           @Param("pageSize") int pageSize, @Param("offset") int offset);

    @Query(value = "SELECT COUNT(*) FROM tasks WHERE archived = FALSE AND ((:idSearch = TRUE "
                 + "AND CAST(id AS VARCHAR) = :id) OR (:idSearch = FALSE AND "
                 + "(LOWER(title) LIKE :term OR LOWER(description) LIKE :term))) "
                 + "AND (:status IS NULL OR status = :status)",
           nativeQuery = true)
    long countSearchTasks(@Param("term") String term, @Param("id") String id,
                          @Param("idSearch") boolean idSearch, @Param("status") String status);
}
