package com.example.kbo.data.repository;

import com.example.kbo.data.entity.GameSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameScheduleRepository extends JpaRepository <GameSchedule, Long>{
}
