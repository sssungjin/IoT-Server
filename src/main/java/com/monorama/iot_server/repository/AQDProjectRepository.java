package com.monorama.iot_server.repository;

import com.monorama.iot_server.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AQDProjectRepository extends JpaRepository<Project, Long> {
    @Query("""
    SELECT p FROM Project p
    WHERE (p.projectType = com.monorama.iot_server.domain.type.ProjectType.AIR_QUALITY OR
        p.projectType = com.monorama.iot_server.domain.type.ProjectType.BOTH)
      AND p.startDate <= CURRENT_DATE
      AND p.endDate >= CURRENT_DATE
    """)
    List<Project> findActiveAirQualityProjects();

    @Query("""
    SELECT p FROM Project p
    WHERE p.id = :projectId
      AND p.startDate <= CURRENT_DATE
      AND p.endDate >= CURRENT_DATE
      AND (p.projectType = com.monorama.iot_server.domain.type.ProjectType.AIR_QUALITY
           OR p.projectType = com.monorama.iot_server.domain.type.ProjectType.BOTH)
    """)
    Optional<Project> findActiveAirQualityProjectById(@Param("projectId") Long projectId);
}
