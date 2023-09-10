package lk.ijse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString

@Entity
public class User {

    @Id
    private String user_name;
    private String password;
    private String Email;

}
