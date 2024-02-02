package begin.flywayspringmaven.api.user;

import begin.flywayspringmaven.api.user.dto.UserDTO;
import begin.flywayspringmaven.common.model.User;
import begin.flywayspringmaven.common.response.APIResponse;
import begin.flywayspringmaven.common.vo.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @ApiOperation(value = "Get All Users")
    public APIResponse<List<UserDTO>> getUserList(){
        return APIResponse.okStatus(this.userService.getUserAdminsList());
    }

    @PostMapping("/users")
    @ApiOperation(value = "Create user")
    public APIResponse<UserDTO> createUser(
        @RequestBody User user) throws Exception {
        return APIResponse.createdStatus(this.userService.createUser(user));
    }

    @PutMapping("/users/{userId}")
    @ApiOperation(value = "Update user")
    public APIResponse<UserDTO> updateUser(
        @ApiParam(value = "User ID", required = true)
        @PathVariable(value = "userId", required = true) int userId,
        @RequestBody User user
    ) throws Exception {
        return APIResponse.okStatus(this.userService.updateUser(user, userId));
    }

    @DeleteMapping(value = "/users/{userId}")
    public APIResponse<UserDTO> deleteUser(
            @ApiParam(value = "User ID" , required = true)
            @PathVariable(value = "userId", required = true) int userId
    ) throws Exception {
        return APIResponse.okStatus(this.userService.deleteUser(userId));
    }
}
