package eu.ericsson.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static eu.ericsson.task.HarryKartSubObj.*;
import static org.springframework.http.MediaType.APPLICATION_XML;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RaceControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void calculateWinner_ShouldReturnTop3Winners() throws Exception {
        mockMvc.perform(post("/api/v1/calculate-winner")
                        .contentType(APPLICATION_XML)
                        .with(httpBasic("user", "password"))
                        .content(HARRY_KART_XML_EXAMPLE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].rank").value(1))
                .andExpect(jsonPath("$[0].name").value(WAIKIKI_SILVIO))
                .andExpect(jsonPath("$[1].rank").value(2))
                .andExpect(jsonPath("$[1].name").value(TIMETOBELUCKY))
                .andExpect(jsonPath("$[2].rank").value(3))
                .andExpect(jsonPath("$[2].name").value(HERCULES_BOKO));
    }

    @Test
    void calculateWinner_ShouldReturnUnauthorizedForBadCredentials() throws Exception {
        mockMvc.perform(post("/api/v1/calculate-winner")
                        .with(httpBasic("wronguser", "wrongpassword"))
                        .contentType(MediaType.APPLICATION_XML)
                        .content(HARRY_KART_XML_EXAMPLE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void calculateWinner_ShouldReturnInternalServerErrorForInvalidXml() throws Exception {
        mockMvc.perform(post("/api/v1/calculate-winner")
                        .with(httpBasic("user", "password"))
                        .contentType(MediaType.APPLICATION_XML)
                        .content("<harryKart><numberOfLoops>0</numberOfLoops></harryKart>")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.detail").value("Number of loops must be at least one"));
    }
}