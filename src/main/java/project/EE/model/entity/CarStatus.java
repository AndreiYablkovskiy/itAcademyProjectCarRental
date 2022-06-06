package project.EE.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table (name = "car_status")
public class CarStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable = false)
    private String name;

    @OneToMany(mappedBy = "carStatus", cascade = CascadeType.PERSIST)
    private List<Car> cars;
}
