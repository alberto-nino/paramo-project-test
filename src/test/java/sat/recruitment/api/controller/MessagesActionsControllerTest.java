package sat.recruitment.api.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import sat.recruitment.api.dto.UserDTO;
import sat.recruitment.api.enums.UserTypeEnum;
import sat.recruitment.api.service.SatRecruitmentService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessagesActionsControllerTest {

    @MockBean
    SatRecruitmentService satRecruitmentService;

    @Autowired
    private SatRecruitmentController controller;

    private List<UserDTO> users = new ArrayList<UserDTO>();

    @Before
    public void setupUsers() {

        // First user
        UserDTO user1 = new UserDTO();

        // Second user
        UserDTO user2 = new UserDTO();

        //Initialization First user
        user1.setName("Luis");
        user1.setMoney(100.7);
        user1.setUserType(UserTypeEnum.NORMAL);
        user1.setAddress("CABA");
        user1.setEmail("Luis@Luis.com");
        user1.setPhone("1234567");

        //Initialization Second user
        user2.setName("Pablo");
        user2.setMoney(104.7);
        user2.setUserType(UserTypeEnum.PREMIUN);
        user2.setAddress("CABA");
        user2.setEmail("Pablo@Pablo.com");
        user2.setPhone("5433222");

        // Add values to List
        users.add(user1);
        users.add(user2);

    }


    @Test
    public void createUser() throws Exception {
        UserDTO response = new UserDTO("Pablo", "Pablo@pablo.com", "+5491126620078", "CABA", UserTypeEnum.NORMAL, 1234.0);
        UserDTO request = new UserDTO("Pablo", "Pablo@pablo.com", "+5491126620078", "CABA", UserTypeEnum.NORMAL, 1234.0);
        when(satRecruitmentService.createUser(request)).thenReturn(response);

        //Operations
        ResponseEntity<UserDTO> result = controller.createUser(request);
        assertNotNull(result);
        assertThat(result).isNotNull();
    }

    @Test
    public void obtainAllUsers() {

        when(satRecruitmentService.findAll()).thenReturn(users);

        // Operations
        List<UserDTO> response = controller.findAll();

        assertNotNull(response);
        assertThat(response).isNotNull();
        assertThat(response.get(0).getMoney()).isEqualTo(100.7);
        assertThat(response.get(0).getAddress()).isEqualTo("CABA");
        assertThat(response.get(0).getName()).isEqualTo("Luis");
        assertThat(response.get(0).getEmail()).isEqualTo("Luis@Luis.com");
        assertThat(response.get(0).getPhone()).isEqualTo("1234567");
        assertThat(response.get(0).getUserType()).isEqualTo(UserTypeEnum.NORMAL);

        assertThat(response.get(1).getMoney()).isEqualTo(104.7);
        assertThat(response.get(1).getAddress()).isEqualTo("CABA");
        assertThat(response.get(1).getName()).isEqualTo("Pablo");
        assertThat(response.get(1).getEmail()).isEqualTo("Pablo@Pablo.com");
        assertThat(response.get(1).getPhone()).isEqualTo("5433222");
        assertThat(response.get(1).getUserType()).isEqualTo(UserTypeEnum.PREMIUN);

    }

}
