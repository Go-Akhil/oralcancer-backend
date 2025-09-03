package com.oralcancer.oralcancer.Repository;

import com.oralcancer.oralcancer.Entity.Detection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetectionRepo extends JpaRepository<Detection,Integer> {
}
