package project.car_rental.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import project.car_rental.model.repository.CarStatusRepository;

@Controller
@RequiredArgsConstructor
public class CarStatusController {
    private final CarStatusRepository carStatusRepository;
}
