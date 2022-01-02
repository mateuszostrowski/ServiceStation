package pl.infoshare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.infoshare.service.MenuService;

@Controller
public class MenuController {

    private final MenuService service;

    public MenuController(MenuService service) {
        this.service = service;
    }

    @GetMapping("menu")
    public String getMenu(Model model) {
        model.addAttribute("menuObjects", service.get());
        return "menu";
    }
}
