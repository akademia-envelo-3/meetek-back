package pl.envelo.meetek.integration.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import pl.envelo.meetek.domain.event.SingleEventController;
import pl.envelo.meetek.domain.event.SingleEventRepo;
import pl.envelo.meetek.domain.event.model.SingleEventCreateDto;
import pl.envelo.meetek.domain.hashtag.Hashtag;
import pl.envelo.meetek.domain.hashtag.HashtagCreateDto;
import pl.envelo.meetek.domain.hashtag.HashtagDto;
import pl.envelo.meetek.domain.hashtag.HashtagRepo;
import pl.envelo.meetek.domain.user.StandardUserRepo;
import pl.envelo.meetek.domain.user.model.StandardUser;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SingleEventControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StandardUserRepo userRepo;

    @Autowired
    private HashtagRepo hashtagRepo;

    @Autowired
    private SingleEventRepo eventRepo;

    @Value("/${app.prefix}/${app.version}/events")
    String prefix;

    String slash = "/";


    @Test
    void createEvent_whenDataValid_Integration_Test() throws Exception {
        // given
        StandardUser user = userRepo.save(new StandardUser());
        SingleEventCreateDto eventCreateDto = new SingleEventCreateDto(
                0L,
                null,
                null,
                "Name of test event",
                "https://linksail.pl/#/login",
                "testtesttesttesttesttesttesttesttesttesttest",
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(3L),
                null,
                null,
                "testLocation",
                null,
                true,
                false,
                true,
                false,
                50,
                null,
                null
                );
        // when
        MvcResult mvcResult = mockMvc.perform(post(prefix)
                        .param("userId", user.getParticipantId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventCreateDto)))
                .andDo(print())
                .andExpect(status().is(201))
                .andReturn();
        // then
        assertThat(mvcResult.getResponse().getRedirectedUrl()).isNotNull();
    }

    @Test
    void createEvent_whenUserIsInvalid_Integration_Test() throws Exception {
        // given
        SingleEventCreateDto eventCreateDto = new SingleEventCreateDto(
                0L,
                null,
                null,
                "Name of test event",
                "https://linksail.pl/#/login",
                "testtesttesttesttesttesttesttesttesttesttest",
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(3L),
                null,
                null,
                "testLocation",
                null,
                true,
                false,
                true,
                false,
                50,
                null,
                null
        );
        // when
        MvcResult mvcResult = mockMvc.perform(post(prefix)
                        .param("userId", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventCreateDto)))
                .andDo(print())
                .andExpect(status().is(404))
                .andReturn();
        // then
        assertThat(mvcResult.getResponse().getContentAsString()).isNotNull();
    }

    @Test
    void createEvent_whenDataInvalid_Integration_Test() throws Exception {
        // given
        StandardUser user = userRepo.save(new StandardUser());
        SingleEventCreateDto eventCreateDto = new SingleEventCreateDto(
                0L,
                null,
                null,
                "a",
                "b",
                "c",
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(3L),
                null,
                null,
                "testLocation",
                null,
                true,
                false,
                true,
                false,
                50,
                null,
                null
        );
        // when
        MvcResult mvcResult = mockMvc.perform(post(prefix)
                        .param("userId", user.getParticipantId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventCreateDto)))
                .andDo(print())
                .andExpect(status().is(400))
                .andReturn();
        // then
        assertThat(mvcResult.getResponse().getContentAsString()).isNotNull();
    }


}
