package id.ac.ui.cs.advprog.eshop.car.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.eshop.car.model.Car;

@Repository
public class CarRepositoryImpl implements CarRepository {
    
    private Map<String, Car> cars = new HashMap<>();


    public Car create(Car model) {
        cars.put(model.getId(), model);
        return model;
    }

    public Iterator<Car> findAll() {
        List<Car> modelList = new ArrayList<>(cars.values());
        return modelList.iterator();
    }

    public Optional<Car> findById(String id) {
        return Optional.ofNullable(cars.get(id));
    }

    public Optional<Car> update(Car model) {
        if (cars.containsKey(model.getId())) {
            cars.put(model.getId(), model);
            return Optional.of(model);
        }
        return Optional.empty();
    }

    public void delete(String id) {
        cars.remove(id);
    }
}
