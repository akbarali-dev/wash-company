package com.example.washer.repository.base;

import com.example.washer.model.template.AbsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import java.util.UUID;

public interface BaseRepository<T extends AbsEntity> extends JpaRepository<T, UUID>, JpaSpecificationExecutor<T> {


}
