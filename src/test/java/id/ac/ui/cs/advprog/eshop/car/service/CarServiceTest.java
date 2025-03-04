package id.ac.ui.cs.advprog.eshop.car.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.eshop.car.model.Car;
import id.ac.ui.cs.advprog.eshop.car.repository.CarRepositoryImpl;


@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @Mock
    CarRepositoryImpl carRepository;

    @InjectMocks
    CarServiceImpl carService;

    private Car car;

    @BeforeEach
    void setUp() {
        car = new Car();
        car.setId("123e4567-e89b-12d3-a456-556642440000");
        car.setName("Car 1");
        car.setQuantity(100);
        car.setColor("Red");
    }

    @Test
    void testCreate() {
        when(carRepository.create(car)).thenReturn(car);

        Car createdCar = carService.create(car);

        assertEquals(car.getId(), createdCar.getId());
        assertEquals(car.getName(), createdCar.getName());
        assertEquals(car.getQuantity(), createdCar.getQuantity());
        assertEquals(car.getColor(), createdCar.getColor());
        verify(carRepository, times(1)).create(createdCar);
    }

    @Test
    void testFindAll() {
        Car car2 = new Car();
        car2.setId("123e4567-e89b-12d3-a456-556642440001");
        car2.setName("Car 2");
        car2.setQuantity(200);
        car2.setColor("Blue");

        when(carRepository.findAll()).thenReturn(Arrays.asList(car, car2).iterator());

        List<Car> cars = carService.findAll();

        assertEquals(2, cars.size());
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(carRepository.findById(car.getId())).thenReturn(Optional.of(car));

        Car foundCar = carService.findById(car.getId()).get();

        assertEquals(car.getId(), foundCar.getId());
        assertEquals(car.getName(), foundCar.getName());
        assertEquals(car.getQuantity(), foundCar.getQuantity());
        assertEquals(car.getColor(), foundCar.getColor());
        verify(carRepository, times(1)).findById(car.getId());
    }

    @Test
    void testUpdate() {
        Car updatedCar = new Car();
        updatedCar.setId("123e4567-e89b-12d3-a456-556642440000");
        updatedCar.setName("Car 1");
        updatedCar.setQuantity(200);
        updatedCar.setColor("Red");

        when(carRepository.update(updatedCar)).thenReturn(Optional.of(updatedCar));

        Optional<Car> carOptional = carService.update(updatedCar);

        assertTrue(carOptional.isPresent());
        assertEquals(updatedCar.getId(), carOptional.get().getId());
        assertEquals(updatedCar.getName(), carOptional.get().getName());
        assertEquals(updatedCar.getQuantity(), carOptional.get().getQuantity());
        assertEquals(updatedCar.getColor(), carOptional.get().getColor());

        verify(carRepository, times(1)).update(updatedCar);
    }

    @Test
    void testDelete() {
        doNothing().when(carRepository).delete(car.getId());

        carService.delete(car.getId());

        verify(carRepository, times(1)).delete(car.getId());
    }
}
