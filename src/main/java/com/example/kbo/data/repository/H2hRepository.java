package com.example.kbo.data.repository;

import com.example.kbo.data.entity.H2h;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface H2hRepository extends JpaRepository <H2h, Long>{
}
