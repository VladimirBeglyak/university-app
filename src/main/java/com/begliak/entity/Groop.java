package com.begliak.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"students", "lections"})
@EqualsAndHashCode(callSuper = true)
@Builder
public class Groop extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String number;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "groop_id")
    List<Student> students = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "groop")
    private List<Lection> lections = new ArrayList<>();

    public void addLections(Lection... lections) {
        this.lections.addAll(List.of(lections));
        Arrays.stream(lections).forEach(lection -> lection.setGroop(this));
    }

    public void deleteLections(Lection... lections) {
        List.of(lections).forEach(lection -> this.lections.remove(lection));
    }

    public void addStudents(Student... students) {
        this.students.addAll(List.of(students));
        Arrays.stream(students).forEach(student -> student.setGroop(this));
    }

    public void deleteStudents(Student... students) {
        List.of(students).forEach(student -> this.students.remove(student));
    }
}



