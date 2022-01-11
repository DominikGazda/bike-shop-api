package pl.shop.bike.models.model.entities.user;

import lombok.*;
import pl.shop.bike.models.model.entities.address.AddressEntity;
import pl.shop.bike.models.model.security.User;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private boolean isBlocked = false;
    private boolean isDeleted = false;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressEntity address;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

}
