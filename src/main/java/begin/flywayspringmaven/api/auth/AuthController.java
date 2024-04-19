package begin.flywayspringmaven.api.auth;

import begin.flywayspringmaven.api.auth.dto.AuthDTO;
import begin.flywayspringmaven.api.user.UserService;
import begin.flywayspringmaven.api.user.dto.UserDTO;
import begin.flywayspringmaven.api.user.dto.UserRequestDTO;
import begin.flywayspringmaven.common.base.BaseService;
import begin.flywayspringmaven.common.model.User;
import begin.flywayspringmaven.common.response.APIResponse;
import begin.flywayspringmaven.common.vo.JwtToken;
import begin.flywayspringmaven.exception.NotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final BaseService baseService;
    private final UserService userService;

    public AuthController(AuthService authService,
                          AuthenticationManager authenticationManager,
                          BaseService baseService,
                          UserService userService) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.baseService = baseService;
        this.userService = userService;
    }

    /**
     * Sign in
     *
     * @param authDTO
     * @return
     */
    @PostMapping("/auth/sign-in")
    @ApiOperation(value = "Sign in")
    public APIResponse<JwtToken> signIn(
            @ApiParam(value = "User login information")
            @Valid
            @RequestBody AuthDTO authDTO
    ) throws AuthenticationException {
        Optional<User> userOptional = authService.findUserByEmailLogin(authDTO.getUsername());
        User user = userOptional.get();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getId(), authDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return APIResponse.okStatus(baseService.jwtForAPIResponse(authentication, authDTO.isRememberMe(), true));
    }

    @PostMapping("/auth/sign-up")
    @ApiOperation(value = "Sign up")
    public APIResponse<UserDTO> signUp(
            @ApiParam(value = "Information to create new user")
            @Valid
            @RequestBody UserRequestDTO userRequestDTO
            ) throws NotFoundException {
        User user = userService.createUser(userRequestDTO);
        UserDTO userDTO = new UserDTO(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        userDTO.setJwtToken(baseService.jwtForAPIResponse(authentication, true, false));
        return APIResponse.okStatus(userDTO);
    }
}
