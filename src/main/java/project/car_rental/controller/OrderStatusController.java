package project.car_rental.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import project.car_rental.model.repository.OrderStatusRepository;

@Controller
@RequiredArgsConstructor
public class OrderStatusController {
    private final OrderStatusRepository orderStatusRepository;
}
