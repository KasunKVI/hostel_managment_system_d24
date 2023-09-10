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

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Student student;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Room room;

}
