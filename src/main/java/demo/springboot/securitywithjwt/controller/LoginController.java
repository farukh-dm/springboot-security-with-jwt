package demo.springboot.securitywithjwt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.springboot.securitywithjwt.dto.AuthenticationRequestDto;
import demo.springboot.securitywithjwt.dto.AuthenticationResponseDto;
import demo.springboot.securitywithjwt.util.JwtUtil;

@Controller
@CrossOrigin
@RequestMapping(value="/user")
public class LoginController {
	
	private static final Logger LOGGER = (Logger)LoggerFactory.getLogger(LoginController.class);
	
	// Provided by MySecurityConfig#authenticationManagerBean()
	@Autowired
	private AuthenticationManager authenticationManager; 
	
	@Autowired
	private JwtUtil jwtUtil; 
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<AuthenticationResponseDto> authenticateRequest(
		@ModelAttribute("authenticationRequest") AuthenticationRequestDto requestDto,
		HttpServletRequest request, HttpServletResponse response) throws Exception {

		LOGGER.debug("in authenticateRequest()  ---->>>>");

		AuthenticationResponseDto responseDto = null;
		HttpStatus httpStatus = HttpStatus.OK;

		try {

			Authentication authentication = new UsernamePasswordAuthenticationToken(requestDto.getUsername(),
					requestDto.getPassword());

			Authentication authenticated = authenticationManager.authenticate(authentication);

			SecurityContextHolder.getContext().setAuthentication(authenticated);

			UserDetails user = (UserDetails) authenticated.getPrincipal();
			
			responseDto = new AuthenticationResponseDto("success", jwtUtil.generateToken(user));

		} catch (BadCredentialsException e) {

			responseDto = new AuthenticationResponseDto("Incorrect username and/or password!", null);
			httpStatus = HttpStatus.BAD_REQUEST;

		} catch (Exception e) {

			responseDto = new AuthenticationResponseDto("Error processing your request!", null);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			LOGGER.error("Exception: ", e);

		}

		return new ResponseEntity<AuthenticationResponseDto>(responseDto, httpStatus);
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, 
		consumes = MediaType.APPLICATION_JSON_VALUE, 
		produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<AuthenticationResponseDto> authenticateJsonRequest(
		@RequestBody AuthenticationRequestDto requestDto) throws Exception {

		LOGGER.debug("in authenticateJsonRequest()  ---->>>>");

		AuthenticationResponseDto responseDto = null;
		HttpStatus httpStatus = HttpStatus.OK;

		try {

			Authentication authentication = new UsernamePasswordAuthenticationToken(requestDto.getUsername(),
					requestDto.getPassword());

			Authentication authenticated = authenticationManager.authenticate(authentication);

			SecurityContextHolder.getContext().setAuthentication(authenticated);

			// AppUserDetailsDto principal = (AppUserDetailsDto) authenticated.getPrincipal();

			responseDto = new AuthenticationResponseDto("success", "TODO");

		} catch (BadCredentialsException e) {

			responseDto = new AuthenticationResponseDto("Incorrect username and/or password!", null);
			httpStatus = HttpStatus.BAD_REQUEST;

		} catch (Exception e) {

			responseDto = new AuthenticationResponseDto("Error processing your request!", null);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

		}

		return new ResponseEntity<AuthenticationResponseDto>(responseDto, httpStatus);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(
		Model model, String error, String logout, 
		HttpServletRequest request, HttpServletResponse response) {
		
        return "login";
    }
	
}
