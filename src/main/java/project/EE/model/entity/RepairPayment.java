package project.EE.model.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "repair_payment")
public class RepairPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Double value;

    @Column
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
