package id.ac.ui.cs.advprog.eshop.car.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import id.ac.ui.cs.advprog.eshop.car.model.Car;
import id.ac.ui.cs.advprog.eshop.car.service.CarService;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private CarService carService;

    @Test
    public void testGetHome() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void testCreateCarGet() throws Exception {
        mockMvc.perform(get("/car/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createCar"))
                .andExpect(model().attributeExists("car"));
    }

    @Test
    public void testCreateCarPost() throws Exception {
        mockMvc.perform(post("/car/create")
                .param("name", "Test Car")
                .param("price", "100")
                .param("color", "Red"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));
    }

    @Test
    public void testListCar() throws Exception {
        Car car1 = new Car();
        Car car2 = new Car();

        car1.setId("1");
        car2.setId("2");

        car1.setName("Car 1");
        car2.setName("Car 2");

        car1.setQuantity(100);
        car2.setQuantity(200);

        car1.setColor("Red");
        car2.setColor("Blue");

        when(carService.findAll()).thenReturn(Arrays.asList(car1, car2));

        mockMvc.perform(get("/car/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("listCar"))
                .andExpect(model().attributeExists("cars"));
    }

    @Test
    public void testUpdateCarGet() throws Exception {
        Car car = new Car();
        car.setId("1");
        car.setName("Car 1");
        car.setQuantity(100);
        car.setColor("Red");

        when(carService.findById("1")).thenReturn(Optional.of(car));
        mockMvc.perform(get("/car/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("updateCar"))
                .andExpect(model().attributeExists("car"));
    }

    @Test
    public void testUpdateCarPost() throws Exception {
        mockMvc.perform(post("/car/update/1")
                .param("name", "Updated Car")
                .param("price", "150")
                .param("color", "Green"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/car/list"));
    }

    @Test
    public void testDeleteCar() throws Exception {
        mockMvc.perform(post("/car/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/car/list"));
    }

    @Test
    public void testError() throws Exception {
        mockMvc.perform(get("/car/error"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"));
    }
}
