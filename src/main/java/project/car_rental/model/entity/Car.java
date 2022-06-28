package project.car_rental.model.entity;

import lombok.Data;


import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable = false)
    private String mark;

    @Column (nullable = false)
    private String model;

    @Column (name = "cost_for_one_hour", nullable = false)
    private Double costForOneHour;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_status_id")
    private CarStatus carStatus;

    @OneToMany(mappedBy = "car")
    private List<Order> orders;

    @Override
    public String toString() {
        return "Car{" +
                "id=" + getId() +
                ", mark='" + getMark() + '\'' +
                ", model='" + getModel() + '\'' +
                ", costForOneHour=" + getCostForOneHour() +
                ", carStatus=" + getCarStatus().getId() +
                '}';
    }
}
