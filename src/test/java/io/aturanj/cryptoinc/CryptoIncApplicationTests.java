package io.aturanj.cryptoinc;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.aturanj.cryptoinc.dto.OrderDto;
import io.aturanj.cryptoinc.repository.OrderRepository;
import java.math.BigDecimal;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
class CryptoIncApplicationTests {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final OrderRepository orderRepository;

    @Autowired
    public CryptoIncApplicationTests(
            MockMvc mockMvc,
            ObjectMapper objectMapper,
            OrderRepository orderRepository) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.orderRepository = orderRepository;
    }

    @Test
    @DisplayName("When an order took place for it must be persisted")
    void createOrderTest() throws Exception {

        var orderDto = new OrderDto(
                111,
                "BUY",
                "BTC",
                BigDecimal.valueOf(21.05),
                BigDecimal.valueOf(13.02)
        );

        mockMvc.perform(post("/api/v1/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDto)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("When all orders are requested then they are all returned")
    void getAllOrdersTest() throws Exception {
        mockMvc.perform(get("/api/v1/orders"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize((int) orderRepository.count())));
    }

    @Test
    @DisplayName("When LiveOrderBoard are requested then they are all returned")
    void getLiveOrderBoardTest() throws Exception {

        var result = mockMvc.perform(get("/api/v1/liveorderboard"));

        result.andExpect(status().is2xxSuccessful());

        var lobRepositorySize = orderRepository.getLiveOrderBoardConditional().size();

        if (lobRepositorySize > 10) {
            result.andExpect(jsonPath("$", hasSize(10)));
        } else {
            result.andExpect(jsonPath("$", hasSize(lobRepositorySize)));
        }
    }

    @Test
    @DisplayName("Delete an order By Id")
    public void deleteOrderLogicalTest() throws Exception {

        mockMvc.perform(delete("/api/v1/delete/3"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
