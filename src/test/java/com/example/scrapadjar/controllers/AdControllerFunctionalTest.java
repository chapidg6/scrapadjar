package com.example.scrapadjar.controllers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AdControllerFunctionalTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSearchAds() throws Exception {
        mockMvc.perform(get("/scrapadjar/search")
        .param("term", "cobre")
        .param("page", "0")     
        .param("size", "4"))    
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content", hasSize(3))) // Verifica que hay 3 elementos en la página actual
        .andExpect(jsonPath("$.content[0].id").value("223c983b-f32d-4b06-b17d-16061dead330"))
        .andExpect(jsonPath("$.content[1].id").value("25393260-f0d0-47e9-899f-2aefa5c6fcff"))
        .andExpect(jsonPath("$.content[2].id").value("b434b963-ad9f-47db-b22e-e8d5c04d4e70"))
        .andExpect(jsonPath("$.totalElements", is(3))) // Verifica el total de elementos en todos los resultados 
        .andExpect(jsonPath("$.size", is(4)))          // Verifica el tamaño de página solicitado
        .andExpect(jsonPath("$.number", is(0)));  
    }

    @Test
    public void testGetAdDetail() throws Exception {
        mockMvc.perform(get("/scrapadjar/addetail/{id}", "b434b963-ad9f-47db-b22e-e8d5c04d4e70"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("b434b963-ad9f-47db-b22e-e8d5c04d4e70"))
                .andExpect(jsonPath("$.name").value("Lingotes de cobre"))
                .andExpect(jsonPath("$.price").value("15.0"))
                .andExpect(jsonPath("$.amount").value("10"))
                .andExpect(jsonPath("$.relatedAds", hasSize(4)));
    }
}
