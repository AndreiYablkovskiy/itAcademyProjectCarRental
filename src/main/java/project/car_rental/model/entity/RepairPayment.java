package project.car_rental.model.entity;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "repair_payment")
public class RepairPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotNull(message = "Value should not be empty")
    private Double value;

    @Column
    @NotNull(message = "Description should not be empty")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Override
    public String toString() {
        return "RepairPayment{" +
                "id=" + getId() +
                ", value=" + getValue() +
                ", order=" + getOrder().getId() +
                '}';
    }
}
