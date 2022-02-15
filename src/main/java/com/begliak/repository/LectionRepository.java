package com.begliak.repository;

import com.begliak.entity.Day;
import com.begliak.entity.Lection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectionRepository extends JpaRepository<Lection, Long> {
    @Query("select l from Lection l " +
            "join l.groop c " +
            "join c.students s " +
            "where s.id=:id and l.day=:day")
    List<Lection> findAllByStudentAndDay(Long id, Day day);
}
