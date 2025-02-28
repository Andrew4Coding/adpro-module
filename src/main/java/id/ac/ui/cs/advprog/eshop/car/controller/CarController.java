package id.ac.ui.cs.advprog.eshop.car.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "redirect:listCar";
    }

    @GetMapping("/list")
    public String carListCar(Model model) {
        model.addAttribute("cars", carService.findAll());
        return "listCar";
    }

    @GetMapping("/update/{id}")
    public String updateCar(@PathVariable("id") String id, Model model) {
        Optional<Car> car = carService.findById(id);
        model.addAttribute("car", car);
        return "updateCar";
    }

    @PostMapping("/update")
    public String updateCar(@ModelAttribute Car car, Model model) {
        System.out.println(car.getId());
        carService.update(car);
        return "redirect:listCar";
    }

    @PostMapping("/delete")
    public String deleteCar(@RequestParam("carId") String carId) {
        carService.delete(carId);
        return "redirect:listCar";
    }
}
