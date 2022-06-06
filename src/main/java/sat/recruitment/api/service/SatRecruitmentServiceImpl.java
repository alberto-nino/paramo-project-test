package sat.recruitment.api.service;

import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sat.recruitment.api.dto.UserDTO;
import sat.recruitment.api.entity.User;
import sat.recruitment.api.map.RecruitmentMapper;
import sat.recruitment.api.repository.IUserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class SatRecruitmentServiceImpl implements SatRecruitmentService {

    @Autowired
    IUserRepository repository;

    private RecruitmentMapper mapper = Mappers.getMapper(RecruitmentMapper.class);

    public static final int MONEY = 100;
    public static final double PORCENTAJE_OCHO = 0.8;
    public static final double PORCENTAJE_DOCE = 0.12;
    public static final double PORCENTAJE_VEINTE = 0.20;
    public static final int FACTOR = 2;

    @Override
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        log.info("Enter to service with name {}", userDTO.getName());
        switchMethod(userDTO);
        List<UserDTO> usersResult = this.findAll();

        Boolean isDuplicated = false;
        for (UserDTO user : usersResult) {

            if (user.getEmail().equals(userDTO.getEmail()) || user.getPhone().equals(userDTO.getPhone())) {
                isDuplicated = true;
            } else if (user.getName().equals(userDTO.getName())) {
                if (user.getAddress().equals(userDTO.getAddress())) {
                    isDuplicated = true;
                }

            }
        }
        if (isDuplicated) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is duplicated");
        }
        User valueResult = repository.save(RecruitmentMapper.INSTANCE.userDTOTOUser(userDTO));
        return RecruitmentMapper.INSTANCE.userToUserDTO(valueResult);
    }



    @Override
    public List<UserDTO> findAll() {
        log.info("Service call repository to find all users");
        List<User> result = repository.findAll();
        return RecruitmentMapper.INSTANCE.usersToUsersDTO(result);
    }


    private void extracted(UserDTO userDTO, double value) {
        Double percentage = PORCENTAJE_DOCE;
        // If new user is normal and has more than USD100
        var gif = userDTO.getMoney() * percentage;
        userDTO.setMoney(userDTO.getMoney() + gif);
    }

    private void switchMethod(UserDTO userDTO) {
        switch(userDTO.userType)
        {
            case NORMAL :
                if (userDTO.getMoney() > MONEY) {
                    extracted(userDTO, PORCENTAJE_DOCE);
                }
                if (userDTO.getMoney() < MONEY) {
                    if (Double.valueOf(userDTO.getMoney()) > 10) {
                        extracted(userDTO, PORCENTAJE_OCHO);
                    }
                }
                break;
            case SUPERUSER :
                if (userDTO.getMoney() > MONEY) {
                    extracted(userDTO, PORCENTAJE_VEINTE);
                }
                break;
            case PREMIUN :
                if (userDTO.getMoney() > MONEY) {
                    Double gif = userDTO.getMoney() * FACTOR;
                    userDTO.setMoney(userDTO.getMoney() + gif);
                }
                break;
            default :
        }
    }
}
