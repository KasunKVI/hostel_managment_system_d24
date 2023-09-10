package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomDTO {

    private String room_type_id;
    private String type;
    private String key_money;
    private Integer qty;
    private List<ReservationDTO> reservations = new ArrayList<>();
}
