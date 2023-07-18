package com.toDoApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toDoApp.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Task")
@Table(name = "task_table")
public class Task  extends Base{
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    @Column(
            name = "title",
            nullable = false
    )
    private String title;

    @Column(name = "description")
    private String description;

    @Column(
            name = "status"
    )
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(
            name = "completed_at"
    )
    @Temporal(value = TemporalType.DATE)
    private Date completedAt;

    @ManyToOne(targetEntity = User.class)
    @JsonIgnore
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "task_user_id_fk"
            )
    )
    private User user;


}

