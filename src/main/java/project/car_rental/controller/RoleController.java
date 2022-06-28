package project.car_rental.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import project.car_rental.model.repository.RoleRepository;

@Controller
@RequiredArgsConstructor
public class RoleController {
    private final RoleRepository repository;
}
