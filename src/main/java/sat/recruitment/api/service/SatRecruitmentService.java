package sat.recruitment.api.service;

import sat.recruitment.api.dto.UserDTO;

import java.util.List;


public interface SatRecruitmentService {

    UserDTO createUser(UserDTO userDTO);

    List<UserDTO> findAll();

}
