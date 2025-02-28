package id.ac.ui.cs.advprog.eshop.car.service;

import java.util.List;
import java.util.Optional;

import id.ac.ui.cs.advprog.eshop.car.model.Car;

public interface CarService {
    Car create(Car car);

    List<Car> findAll();

    Optional<Car> findById(String carId);

    Optional<Car> update(Car car);

    void delete(String carId);
}
