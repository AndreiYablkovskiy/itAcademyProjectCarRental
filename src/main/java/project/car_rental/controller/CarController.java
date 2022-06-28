package project.car_rental.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.car_rental.model.entity.Car;
import project.car_rental.model.entity.CarStatus;
import project.car_rental.service.CarService;
import project.car_rental.service.CarStatusService;

import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;
    private final CarStatusService carStatusService;

    @GetMapping("/")
    public String getAllMarks(Model model){
        Set<String> marks = carService.getAllMarksWhereCarStatusNotRepair();
        model.addAttribute("marks", marks);
        return "index";
    }

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

