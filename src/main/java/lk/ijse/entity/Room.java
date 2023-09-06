package lk.ijse.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
public class Room {

    @Id
    private String room_type_id;
    private String type;
    private String key_money;
    private Integer qty;

    @OneToMany (mappedBy = "room" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;


}
