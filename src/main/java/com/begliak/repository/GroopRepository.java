package com.begliak.repository;

import com.begliak.entity.Groop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroopRepository extends JpaRepository<Groop, Long> {
}
