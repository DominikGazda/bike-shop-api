package pl.shop.bike.models.model.entities.address;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String street;
    private Integer houseNumber;
    private Integer localNumber;
    private String zipCode;
    private String city;
    private String phone;
    private String email;
}
