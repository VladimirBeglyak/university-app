package com.begliak.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "groop")
@Builder
@EqualsAndHashCode(callSuper = true)
public class Student extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Groop groop;

}
