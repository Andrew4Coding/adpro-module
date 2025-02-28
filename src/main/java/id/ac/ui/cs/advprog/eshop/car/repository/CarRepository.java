package id.ac.ui.cs.advprog.eshop.car.repository;

import java.util.Iterator;
import java.util.Optional;

import id.ac.ui.cs.advprog.eshop.car.model.Car;

public interface CarRepository {
    Car create(Car car);
    Iterator<Car> findAll();
    Optional<Car> findById(String carId);
    
    Optional<Car> update(Car car);
    void delete(String carId);
}
