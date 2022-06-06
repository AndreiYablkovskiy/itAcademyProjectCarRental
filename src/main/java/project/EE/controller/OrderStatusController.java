package project.EE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import project.EE.model.repository.OrderStatusRepository;

@Controller
@RequiredArgsConstructor
public class OrderStatusController {
    private final OrderStatusRepository orderStatusRepository;
}
