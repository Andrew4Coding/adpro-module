package id.ac.ui.cs.advprog.eshop.car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import id.ac.ui.cs.advprog.eshop.car.model.Car;
import id.ac.ui.cs.advprog.eshop.car.service.CarService;

@Controller
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/create")
    public String createCar(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "createCar";
    }

    @PostMapping("/create")
    public String createCar(@ModelAttribute Car car, Model model) {
        carService.create(car);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String listCar(Model model) {
        model.addAttribute("cars", carService.findAll());
        return "listCar";
    }

    @GetMapping("/update/{id}")
    public String updateCar(@PathVariable("id") String id, Model model) {
        Car car = carService.findById(id).get();
        model.addAttribute("car", car);
        
        return "updateCar";
    }

    @PostMapping("/update/{id}")
    public String updateCar(@PathVariable("id") String id, @ModelAttribute Car car, Model model) {
        car.setId(id);
        carService.update(car);
        return "redirect:/car/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteCar(@PathVariable("id") String id) {
        carService.delete(id);
        return "redirect:/car/list";
    }
}
