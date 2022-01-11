package pl.shop.bike.models.model.entities.service;

import lombok.*;
import pl.shop.bike.models.model.entities.user.UserEntity;

import javax.persistence.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "service")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String time;

    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;
}


