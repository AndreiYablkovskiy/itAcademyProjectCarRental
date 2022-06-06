package project.EE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import project.EE.model.repository.CarStatusRepository;

@Controller
@RequiredArgsConstructor
public class CarStatusController {
    private final CarStatusRepository carStatusRepository;
}
