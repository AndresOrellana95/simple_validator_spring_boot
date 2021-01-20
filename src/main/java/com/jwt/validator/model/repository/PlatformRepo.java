package com.jwt.validator.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jwt.validator.model.entity.Platform;

@Repository
public interface PlatformRepo extends JpaRepository<Platform, Long> {

    Platform findByKeyValue(String keyValue);
}
