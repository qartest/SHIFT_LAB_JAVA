package qartest.work;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class Tests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void fullApiFlowTest() throws Exception {

        mockMvc.perform(post("/seller/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Ivan",
                                  "contactInfo": "ivan@test.com"
                                }
                                """))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/seller/getAll"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/seller/get/1"))
                .andExpect(status().isOk());


        mockMvc.perform(patch("/seller/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Ivan Updated",
                                  "contactInfo": "ivan.updated@test.com"
                                }
                                """))
                .andExpect(status().isOk());

        mockMvc.perform(post("/transaction/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "sellerId": 1,
                                  "amount": 150,
                                  "paymentType": "CASH"
                                }
                                """))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/transaction/allTransaction"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/transaction/get/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/transaction/getBySellerId/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/seller/findTopSellers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "startPeriod": "2024-10-01T00:00:00",
                                  "endPeriod": "2024-12-31T23:59:59"
                                }
                                """))
                .andExpect(status().isOk());

        mockMvc.perform(get("/seller/findSellersWithSumLess/200")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "startPeriod": "2024-10-01T00:00:00",
                                  "endPeriod": "2024-12-31T23:59:59"
                                }
                                """))
                .andExpect(status().isOk());
    }
}