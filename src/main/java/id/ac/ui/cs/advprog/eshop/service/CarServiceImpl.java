package id.ac.ui.cs.advprog.eshop.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;

@Service
public class CarServiceImpl implements ServiceInterface<Car> {
    @Autowired
    private CarRepository carRepository;

    @Override
    public Car create(Car car) {
        return carRepository.create(car);
    }

    @Override
    public List<Car> findAll() {
        Iterator<Car> carIterator = carRepository.findAll();
        List<Car> cars = new ArrayList<>();

        carIterator.forEachRemaining(cars::add);
        return cars;
    }

    @Override
    public Optional<Car> findById(String carId) {
        return carRepository.findById(carId);
    }

    @Override
    public Optional<Car> update(Car car) {
        carRepository.update(car);
        return Optional.of(car);
    }

    @Override
    public void delete(String carId) {
        carRepository.delete(carId);
    }
}