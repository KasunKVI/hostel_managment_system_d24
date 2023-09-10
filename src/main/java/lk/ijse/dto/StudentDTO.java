package lk.ijse.dto;

import lk.ijse.entity.Reservation;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class StudentDTO {

    private String student_id;
    private String name;
    private String address;
    private String contact_no;
    private LocalDate dob;
    private String gender;
    private String university;
    private String email;
    private List<ReservationDTO> reservations = new ArrayList<>();

}
