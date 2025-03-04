package id.ac.ui.cs.advprog.eshop.car.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.eshop.car.model.Car;

@ExtendWith(MockitoExtension.class)
class CarRepositoryTest {
    @InjectMocks
    CarRepositoryImpl carRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Car car = new Car();
        car.setId("123e4567-e89b-12d3-a456-556642440000");
        car.setName("Car 1");
        car.setQuantity(100);
        car.setColor("Red");
        carRepository.create(car);

        Iterator<Car> cars = carRepository.findAll();
        assertTrue(cars.hasNext());

        Car savedCar = cars.next();

        assertEquals(car.getId(), savedCar.getId());
        assertEquals(car.getName(), savedCar.getName());
        assertEquals(car.getQuantity(), savedCar.getQuantity());
        assertEquals(car.getColor(), savedCar.getColor());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Car> cars = carRepository.findAll();
        assertFalse(cars.hasNext());
    }

    @Test
    void testFindById() {
        Car car = new Car();
        car.setId("123e4567-e89b-12d3-a456-556642440000");
        car.setName("Car 1");
        car.setQuantity(100);
        car.setColor("Red");
        carRepository.create(car);

        Car savedCar = carRepository.findById(car.getId()).get();

        assertEquals(car.getId(), savedCar.getId());
        assertEquals(car.getName(), savedCar.getName());
        assertEquals(car.getQuantity(), savedCar.getQuantity());
        assertEquals(car.getColor(), savedCar.getColor());
    }

    @Test
    void testFindAllIfMoreThanOneCar() {
        Car car1 = new Car();
        car1.setId("123e4567-e89b-12d3-a456-556642440000");
        car1.setName("Car 1");
        car1.setQuantity(100);
        car1.setColor("Red");
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setId("123e4567-e89b-12d3-a456-556642440001");
        car2.setName("Car 2");
        car2.setQuantity(200);
        car2.setColor("Blue");
        carRepository.create(car2);

        Iterator<Car> cars = carRepository.findAll();
        
        assertTrue(cars.hasNext());

        Car savedCar = cars.next();
        if (savedCar.getId().equals(car1.getId())) {
            assertEquals(car1.getId(), savedCar.getId());
            assertEquals(car1.getName(), savedCar.getName());
            assertEquals(car1.getQuantity(), savedCar.getQuantity());
            assertEquals(car1.getColor(), savedCar.getColor());
        } else {
            assertEquals(car2.getId(), savedCar.getId());
            assertEquals(car2.getName(), savedCar.getName());
            assertEquals(car2.getQuantity(), savedCar.getQuantity());
            assertEquals(car2.getColor(), savedCar.getColor());
        }
    }

    // Update
    @Test
    void testUpdate() {
        Car car = new Car();
        car.setId("123e4567-e89b-12d3-a456-556642440000");
        car.setName("Car 1");
        car.setQuantity(100);
        car.setColor("Red");
        carRepository.create(car);

        car.setName("Car 2");
        car.setQuantity(200);
        car.setColor("Blue");
        carRepository.update(car);

        Iterator<Car> cars = carRepository.findAll();
        assertTrue(cars.hasNext());

        Car savedCar = cars.next();

        assertEquals(car.getId(), savedCar.getId());
        assertEquals(car.getName(), savedCar.getName());
        assertEquals(car.getQuantity(), savedCar.getQuantity());
        assertEquals(car.getColor(), savedCar.getColor());
    }

    @Test
    void testUpdateIfEmpty() {
        Car car = new Car();
        car.setId("123e4567-e89b-12d3-a456-556642440000");
        car.setName("Car 1");
        car.setQuantity(100);
        car.setColor("Red");
        carRepository.update(car);

        Iterator<Car> cars = carRepository.findAll();
        assertFalse(cars.hasNext());
    }

    @Test
    void testUpdateIfMoreThanOneCar() {
        Car car1 = new Car();
        car1.setId("123e4567-e89b-12d3-a456-556642440000");
        car1.setName("Car 1");
        car1.setQuantity(100);
        car1.setColor("Red");
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setId("123e4567-e89b-12d3-a456-556642440001");
        car2.setName("Car 2");
        car2.setQuantity(200);
        car2.setColor("Blue");
        carRepository.create(car2);

        car1.setName("Car 3");
        car1.setQuantity(300);
        car1.setColor("Green");
        carRepository.update(car1);

        Iterator<Car> cars = carRepository.findAll();
        assertTrue(cars.hasNext());

        Car savedCar = cars.next();
        if (savedCar.getId().equals(car1.getId())) {
            assertEquals(car1.getId(), savedCar.getId());
            assertEquals(car1.getName(), savedCar.getName());
            assertEquals(car1.getQuantity(), savedCar.getQuantity());
            assertEquals(car1.getColor(), savedCar.getColor());
        } else {
            assertEquals(car2.getId(), savedCar.getId());
            assertEquals(car2.getName(), savedCar.getName());
            assertEquals(car2.getQuantity(), savedCar.getQuantity());
            assertEquals(car2.getColor(), savedCar.getColor());
        }

        savedCar = cars.next();
        if (savedCar.getId().equals(car1.getId())) {
            assertEquals(car1.getId(), savedCar.getId());
            assertEquals(car1.getName(), savedCar.getName());
            assertEquals(car1.getQuantity(), savedCar.getQuantity());
            assertEquals(car1.getColor(), savedCar.getColor());
        } else {
            assertEquals(car2.getId(), savedCar.getId());
            assertEquals(car2.getName(), savedCar.getName());
            assertEquals(car2.getQuantity(), savedCar.getQuantity());
            assertEquals(car2.getColor(), savedCar.getColor());
        }

        assertFalse(cars.hasNext());
    }

    // Delete
    @Test
    void testDelete() {
        Car car = new Car();
        car.setId("123e4567-e89b-12d3-a456-556642440000");
        car.setName("Car 1");
        car.setQuantity(100);
        car.setColor("Red");
        carRepository.create(car);

        carRepository.delete(car.getId());

        Iterator<Car> cars = carRepository.findAll();
        assertFalse(cars.hasNext());
    }

    @Test
    void testDeleteIfEmpty() {
        carRepository.delete("123e4567-e89b-12d3-a456-556642440000");

        Iterator<Car> cars = carRepository.findAll();
        assertFalse(cars.hasNext());
    }

    @Test
    void testDeleteIfMoreThanOneCar() {
        Car car1 = new Car();
        car1.setId("123e4567-e89b-12d3-a456-556642440000");
        car1.setName("Car 1");
        car1.setQuantity(100);
        car1.setColor("Red");
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setId("123e4567-e89b-12d3-a456-556642440001");
        car2.setName("Car 2");
        car2.setQuantity(200);
        car2.setColor("Blue");
        carRepository.create(car2);

        carRepository.delete(car1.getId());

        Iterator<Car> cars = carRepository.findAll();
        assertTrue(cars.hasNext());

        Car savedCar = cars.next();
        assertEquals(car2.getId(), savedCar.getId());
        assertEquals(car2.getName(), savedCar.getName());
        assertEquals(car2.getQuantity(), savedCar.getQuantity());
        assertEquals(car2.getColor(), savedCar.getColor());

        assertFalse(cars.hasNext());
    }
}
