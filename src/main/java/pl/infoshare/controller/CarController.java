package pl.infoshare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.infoshare.model.Car;
import pl.infoshare.service.CarService;
import pl.infoshare.service.MenuService;

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

    @PostMapping(value = "/add-car")
    public String addCar(@ModelAttribute("car") Car car) {
        carService.save(car);
        return "redirect:/car-added";
    }

    @GetMapping("/car-added")
    public String carAddedInfo(Model model) {
        model.addAttribute("menuObjects", menuService.get());
        return "car-added";
    }

}
