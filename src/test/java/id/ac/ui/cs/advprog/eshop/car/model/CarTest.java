package id.ac.ui.cs.advprog.eshop.car.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarTest {
    Car car;

    @BeforeEach
    void setup() {
        this.car = new Car();
        this.car.setId("123e4567-e89b-12d3-a456-556642440000");
        this.car.setName("Car 1");
        this.car.setQuantity(100);
        this.car.setColor("Red");
    }

    @Test
    public void testgetId() {
        assert(this.car.getId().equals("123e4567-e89b-12d3-a456-556642440000"));
    }

    @Test
    public void testgetName() {
        assert(this.car.getName().equals("Car 1"));
    }

    @Test
    public void testgetQuantity() {
        assert (this.car.getQuantity() == 100);
    }
    
    @Test
    public void testgetColor() {
        assert (this.car.getColor().equals("Red"));
    }
}
