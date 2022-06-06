package project.EE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.EE.model.entity.Car;
import project.EE.service.CarService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping()
public class CarController {
    private final CarService carService;

    @GetMapping("/cars")
    public String getCars (Model model){
        List<Car> cars = carService.findAllWithoutRepairStatus();
        model.addAttribute("cars", cars);
        return "cars/cars";
    }

    @GetMapping("/car")
    public String getCarById (@RequestParam Integer id, Model model){
        Car car = carService.findById(id);
        model.addAttribute("car", car);
        return "cars/carbyid";
    }
}
