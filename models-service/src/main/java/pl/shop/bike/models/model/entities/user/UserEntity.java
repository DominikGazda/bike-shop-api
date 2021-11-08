package pl.shop.bike.models.model.entities.user;

import lombok.*;
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
    private String street;
    private Integer houseNumber;
    private Integer localNumber;
    private String city;
    private String email;
    private String phone;
    private String postalCode;
    private String statute;
    private String zipCode;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}
