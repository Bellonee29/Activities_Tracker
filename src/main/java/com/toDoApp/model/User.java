package com.toDoApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toDoApp.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "User")
@Table(name = "user_Table")
public class User  extends Base{
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @Column(
            name = "email",
            nullable = false,
            unique = true
    )
    private String email;

    @Column(
            name = "gender",
            nullable = false
    )
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(
            name = "password",
            nullable = false
    )
    private String password;

    @JsonIgnore
    @OneToMany(
            mappedBy = "user",
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Task> task;
    public User(
            String name,
            String email,
            Gender gender,
            String password
    ) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.password=password;
    }

    public User(Long id,
                String name,
                String email,
                Gender gender,
                String password
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.password = password;
    }
}
