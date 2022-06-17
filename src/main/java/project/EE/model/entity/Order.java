package project.EE.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "rental_order")
@Data
public class Order {

    public Order() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "order_date")
    private LocalDateTime orderDate;

    @Column(name ="rental_start")
    private LocalDateTime rentalStart;

    @Column(name ="rental_end")
    private LocalDateTime rentalEnd;

    @Column(name = "payment_value")
    private Double paymentValue;

    @Column(name = "order_info")
    private String orderInfo;

    @Column(name = "employee_name")
    private String employeeName;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<RepairPayment> repairPayments;

}
