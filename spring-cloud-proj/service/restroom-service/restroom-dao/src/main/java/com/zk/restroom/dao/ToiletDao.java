package com.zk.restroom.dao;

import com.zk.restroom.entity.ToiletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author CoderZk
 */
public interface ToiletDao extends JpaRepository<ToiletEntity, Long> {

    List<ToiletEntity> findAllByCleanAndAvailable(boolean clean, boolean available);
}
