package id.ac.ui.cs.advprog.eshop.car.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.eshop.car.model.Car;
import id.ac.ui.cs.advprog.eshop.car.repository.CarRepositoryImpl;

@Service
public class CarServiceImpl implements CarService { 
    @Autowired
    private CarRepositoryImpl carRepository;

    public Car create(Car car) {
        return carRepository.create(car);
    }

    public List<Car> findAll() {
        Iterator<Car> carIterator = carRepository.findAll();
        List<Car> cars = new ArrayList<>();

        carIterator.forEachRemaining(cars::add);
        return cars;
    }

    public Optional<Car> findById(String carId) {
        return carRepository.findById(carId);
    }

    public Optional<Car> update(Car car) {
        carRepository.update(car);
        return Optional.of(car);
    }

    public void delete(String carId) {
        carRepository.delete(carId);
    }
}