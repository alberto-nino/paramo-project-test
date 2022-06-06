package sat.recruitment.api.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sat.recruitment.api.service.SatRecruitmentService;
import sat.recruitment.api.dto.UserDTO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1", produces = { MediaType.APPLICATION_JSON_VALUE })
@Slf4j
public class SatRecruitmentController {

	@Autowired
	SatRecruitmentService service;

	@PostMapping(value = "/create-user", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Create new record")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Created", response = UserDTO.class),
			@ApiResponse(code = 404, message = "Resource not found", response = UserDTO.class),
			@ApiResponse(code = 500, message = "Error internal from server", response = UserDTO.class)
	})
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO messageBody) {
		log.info("Call service with name {}", messageBody.getName());
		return new ResponseEntity(service.createUser(messageBody), HttpStatus.CREATED);
	}


	@GetMapping(value = "/find", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Find all users")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = UserDTO.class),
			@ApiResponse(code = 404, message = "Resource not found", response = UserDTO.class),
			@ApiResponse(code = 500, message = "Error internal from server", response = UserDTO.class)
	})
	public List<UserDTO> findAll(){
		log.info("Controller obtain all users");
		return service.findAll();
	}
}
