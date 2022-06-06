package project.EE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import project.EE.model.repository.RoleRepository;

@Controller
@RequiredArgsConstructor
public class RoleController {
    private final RoleRepository repository;
}
