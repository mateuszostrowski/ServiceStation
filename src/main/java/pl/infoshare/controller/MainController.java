package pl.infoshare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.infoshare.model.Car;
import pl.infoshare.service.MenuService;

@Controller
public class MainController {

    private final MenuService menuService;

    public MainController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public String get(Model model){
        model.addAttribute("car", new Car());
        model.addAttribute("menuObjects", menuService.get());
        return "index";
    }

}
