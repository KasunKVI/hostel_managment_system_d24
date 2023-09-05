package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
public class Reservation {

    @Id
    private String res_id;
    private LocalDate date;
    private String status;

    @JoinColumn(name = "student_id")
    @ManyToOne
    private Student student;

    @JoinColumn(name = "room_type_id")
    @ManyToOne
    private Room room;

}
