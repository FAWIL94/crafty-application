package ch.zhaw.application_crafty;

import static org.hamcrest.core.Is.is; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {
    @Autowired
    private MockMvc mvc;


    @Test

    public void testGetById() throws Exception{
        mvc.perform(get("/api/customer/639827fba22b3e00682cd92f"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.email", is("f_wild@outlook.com")))
        .andExpect(jsonPath("$.name", is("Fabian Wild")));
    }

}
