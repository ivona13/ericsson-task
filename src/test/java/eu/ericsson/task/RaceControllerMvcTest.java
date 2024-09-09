package eu.ericsson.task;

import eu.ericsson.task.controller.RaceController;
import eu.ericsson.task.domain.HarryKart;
import eu.ericsson.task.domain.Participant;
import eu.ericsson.task.service.RaceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static eu.ericsson.task.HarryKartSubObj.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_XML;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RaceController.class)
class RaceControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RaceService raceService;

    @Test
    void calculateWinner_ShouldReturnTop3Winners() throws Exception {
        String inputXml = "<harryKart>\n" +
                "    <numberOfLoops>3</numberOfLoops>\n" +
                "    <startList>\n" +
                "        <participant>\n" +
                "            <lane>1</lane>\n" +
                "            <name>TIMETOBELUCKY</name>\n" +
                "            <baseSpeed>10</baseSpeed>\n" +
                "        </participant>\n" +
                "        <participant>\n" +
                "            <lane>2</lane>\n" +
                "            <name>CARGO DOOR</name>\n" +
                "            <baseSpeed>10</baseSpeed>\n" +
                "        </participant>\n" +
                "        <participant>\n" +
                "            <lane>3</lane>\n" +
                "            <name>HERCULES BOKO</name>\n" +
                "            <baseSpeed>10</baseSpeed>\n" +
                "        </participant>\n" +
                "        <participant>\n" +
                "            <lane>4</lane>\n" +
                "            <name>WAIKIKI SILVIO</name>\n" +
                "            <baseSpeed>10</baseSpeed>\n" +
                "        </participant>\n" +
                "    </startList>\n" +
                "    <powerUps>\n" +
                "        <loop number=\"1\">\n" +
                "            <lane number=\"1\">0</lane>\n" +
                "            <lane number=\"2\">0</lane>\n" +
                "            <lane number=\"3\">1</lane>\n" +
                "            <lane number=\"4\">3</lane>\n" +
                "        </loop>\n" +
                "        <loop number=\"2\">\n" +
                "            <lane number=\"1\">10</lane>\n" +
                "            <lane number=\"2\">0</lane>\n" +
                "            <lane number=\"3\">0</lane>\n" +
                "            <lane number=\"4\">1</lane>\n" +
                "        </loop>\n" +
                "    </powerUps>\n" +
                "</harryKart>";

        List<Participant> sortedParticipants = Arrays.asList(
                createParticipantStubObj(1, WAIKIKI_SILVIO, 10.0),
                createParticipantStubObj(2, TIMETOBELUCKY, 10),
                createParticipantStubObj(3, HERCULES_BOKO, 10)
        );
        when(raceService.calculateRank(Mockito.any(HarryKart.class))).thenReturn(sortedParticipants);

        mockMvc.perform(post("/api/v1/calculate-winner")
                        .contentType(APPLICATION_XML)
                        .content(inputXml)
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
}