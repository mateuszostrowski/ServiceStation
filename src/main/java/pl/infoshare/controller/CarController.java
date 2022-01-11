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
import java.util.List;

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
        carService.saveNew(car);
        return "redirect:/car-added";
    }

    @GetMapping("/car-added")
    public String getCarAddedInfo(Model model) {
        model.addAttribute("menuObjects", menuService.get());
        return "car-added";
    }

    @GetMapping("/cars-to-fix")
    public String getCarsToFix(Model model) {
        model.addAttribute("menuObjects", menuService.get());
        model.addAttribute("cars", carService.getToFix());
        return "cars-to-fix";
    }

    @GetMapping("/fix-car/{id}")
    public String getCarToFix(@PathVariable("id") long id) {
        return "redirect:/";
    }

    @PostMapping("/fix-car/{id}")
    public String fixCar(@PathVariable("id") long id) {
        carService.fixCarWithId(id);
        return "redirect:/";
    }

    @GetMapping("/cars-fixed")
    public String getFixedCars(Model model) {
        model.addAttribute("menuObjects", menuService.get());
        model.addAttribute("cars", carService.getFixed());
        return "cars-fixed";
    }

    @GetMapping("/fix-car")
    public String findCarsToFix(Model model) {
        model.addAttribute("menuObjects", menuService.get());
        return "fix-car";
    }

    @GetMapping("/find-car")
    public String getFindCarForm(@RequestParam(value = "info") String info, Model model) {
        model.addAttribute("info", info);
        return "fix-car";
    }

    @PostMapping("/find-car")
    public String findCars(@RequestParam(value = "info") String info){
        return "redirect:/cars-to-fix/" + info;
    }

    @GetMapping("/cars-to-fix/{info}")
    public String getMatchedCarsToFix(@PathVariable("info") String info, Model model) {
        model.addAttribute("menuObjects", menuService.get());
        List<Car> found = carService.findMatch(info);
        if (found.isEmpty()) {
            return "redirect:/car-not-found";
        }
        model.addAttribute("cars", found);
        return "cars-to-fix";
    }

    @GetMapping("/car-not-found")
    public String getCarNotFoundInfo(Model model) {
        model.addAttribute("menuObjects", menuService.get());
        return "car-not-found";
    }
}
