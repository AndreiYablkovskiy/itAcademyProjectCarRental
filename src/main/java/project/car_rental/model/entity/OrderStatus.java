package project.car_rental.model.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "order_status")
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable = false)
    private String name;

    @OneToMany(mappedBy = "orderStatus")
    private List<Order> orders;

    public OrderStatus() {
    }

    public OrderStatus(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                '}';
    }
}
