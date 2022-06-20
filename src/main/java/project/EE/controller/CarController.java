package project.EE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.EE.model.entity.Car;
import project.EE.model.entity.CarStatus;
import project.EE.service.CarService;
import project.EE.service.CarStatusService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;
    private final CarStatusService carStatusService;

    @GetMapping("/cars")
    public String getCars(@RequestParam(required = false) Integer statusId, Model model) {
        List<Car> cars = carService.getCarsByStatusId(statusId);
        model.addAttribute("cars", cars);
        List<CarStatus> carStatuses = carStatusService.findAllWithoutRepairStatus();
        model.addAttribute("statuses", carStatuses);
        return "cars/cars";
    }

    @GetMapping("/car")
    public String getCarById(@RequestParam Integer id, Model model) {
        Car car = carService.findById(id);
        model.addAttribute("car", car);
        return "cars/carbyid";
    }

    @PostMapping("/car")
    public String updateCarStatus(@RequestParam Integer car, @RequestParam Integer carStatus) {
        carService.updateCarStatus(car, carStatus);
        return "redirect:/car?id=" + car;
    }
}

