package lk.ijse.dto;
import lk.ijse.entity.Room;
import lk.ijse.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ReservationDTO {

    private String res_id;
    private LocalDate date;
    private String status;
    private StudentDTO student;
    private RoomDTO room;

}
