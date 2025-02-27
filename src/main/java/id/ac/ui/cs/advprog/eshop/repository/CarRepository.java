package id.ac.ui.cs.advprog.eshop.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.eshop.model.Car;

@Repository
public class CarRepository {
    static int id = 0;

    private List<Car> carData = new ArrayList<>();
    
    public Car create(Car car) {
        carData.add(car);
        return car;
    }


    public Iterator<Car> findAll() {
        return carData.iterator();
    }

    public Car findById(String carId) {
        for (Car car : carData) {
            if (car.getId().equals(carId)) {
                return car;
            }
        }
        return null;
    }

    public Car update(String id, Car updatedCar) {
        for (Car car : carData) {
            if (car.getId().equals(id)) {
                car.setName(updatedCar.getName());
                car.setColor(updatedCar.getColor());
                car.setQuantity(updatedCar.getQuantity());
                return car;
            }
        }
        return null;
    }

    public void delete(String id) {
        carData.removeIf(car -> car.getId().equals(id));
    }
}
