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
public class Student {

    @Id

    private String student_id;
    private String name;
    private String address;
    private String contact_no;
    private LocalDate dob;
    private String gender;
    private String university;
    private String email;

    @OneToMany (mappedBy = "student" , cascade = CascadeType.ALL, targetEntity = Reservation.class)
    private List<Reservation> reservation;

}
