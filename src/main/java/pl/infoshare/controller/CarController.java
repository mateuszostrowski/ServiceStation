package pl.infoshare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.infoshare.model.Car;
import pl.infoshare.service.CarService;
import pl.infoshare.service.MenuService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class CarController {

    private final MenuService menuService;
    private final CarService carService;

    @Autowired
    public CarController(MenuService menuService, CarService carService) {
        this.menuService = menuService;
        this.carService = carService;
    }

    @GetMapping
    public String getIndexPage(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("menuObjects", menuService.get());
        return "index";
    }

    @GetMapping(value = "/add-car")
    public String getCarForm() {
        return "redirect:/index";
    }

    @PostMapping(value = "/add-car")
    public String addCar(@Valid @ModelAttribute("car") Car car, Errors errors) {
        if (errors.hasErrors()) {
            return "redirect:/index";
        }
        carService.save(car);
        return "redirect:/car-added";
    }

    @GetMapping("/car-added")
    public String carAddedInfo(Model model) {
        model.addAttribute("menuObjects", menuService.get());
        return "car-added";
    }

    @GetMapping("/cars-to-fix")
    public String carsToFix(Model model){
        model.addAttribute("menuObjects", menuService.get());
        model.addAttribute("cars", carService.getToFix());
        return "cars-to-fix";
    }

    @GetMapping("/fix-car/{id}")
    public String getCarToFix(@PathVariable ("id") long id) {
        return "redirect:/";
    }

    @PostMapping("/fix-car/{id}")
    public String fixCar(@PathVariable ("id") long id) {
        Optional<Car> car = carService.get(id);
        car.ifPresent(carService::fix);
        return "redirect:/";
    }

}
