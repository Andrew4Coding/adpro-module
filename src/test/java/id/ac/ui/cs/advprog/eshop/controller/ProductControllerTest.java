package id.ac.ui.cs.advprog.eshop.controller;

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

import id.ac.ui.cs.advprog.eshop.product.model.Product;
import id.ac.ui.cs.advprog.eshop.product.service.ProductService;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private ProductService productService;

    @Test
    public void testGetHome() throws Exception {
        mockMvc.perform(get("/product/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void testCreateProductGet() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    public void testCreateProductPost() throws Exception {
        mockMvc.perform(post("/product/create")
                .param("name", "Test Product")
                .param("price", "100"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));
    }

    @Test
    public void testListProduct() throws Exception {
        Product product1 = new Product();
        Product product2 = new Product();

        product1.setId("1");
        product2.setId("2");

        product1.setName("Product 1");
        product2.setName("Product 2");

        product1.setQuantity(100);
        product2.setQuantity(200);

        when(productService.findAll()).thenReturn(Arrays.asList(product1, product2));

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("listProduct"))
                .andExpect(model().attributeExists("products"));
    }

    @Test
    public void testUpdateProductGet() throws Exception {
        Product product = new Product();
        product.setId("1");
        product.setName("Product 1");
        product.setQuantity(100);

        when(productService.findById("1")).thenReturn(Optional.of(product));
        mockMvc.perform(get("/product/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("updateProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    public void testUpdateProductPost() throws Exception {
        mockMvc.perform(post("/product/update/1")
                .param("name", "Updated Product")
                .param("price", "150"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(post("/product/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }

    @Test
    public void testError() throws Exception {
        mockMvc.perform(get("/product/error"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"));
    }
}
