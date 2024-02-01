package begin.flywayspringmaven.api.user;

import begin.flywayspringmaven.api.user.dto.UserDTO;
import begin.flywayspringmaven.common.model.User;
import begin.flywayspringmaven.common.response.APIResponse;
import begin.flywayspringmaven.common.vo.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @ApiOperation(value = "Get All Users")
    public APIResponse<PageInfo<UserDTO>> getUserList(
        @ApiParam(value = "Search Info: user's name or user's email", required = false)
        @RequestParam(value = "query", required = false) String query,
        @ApiParam(value = "Page", required = false)
        @RequestParam(value = "page", required = false) Integer page,
        @ApiParam(value = "Limit", required = false)
        @RequestParam(value = "limit", required = false) Integer limit,
        @ApiParam(value = "Sort by", required = false, allowableValues = "OLDEST_TO_NEWEST, NEWEST_TO_OLDEST")
        @RequestParam(value = "sort", required = false) String sort
    ){
        return APIResponse.okStatus(this.userService.getUserAdminsList(query, page, limit, sort));
    }

    @PostMapping("/users")
    @ApiOperation(value = "Create user")
    public APIResponse<UserDTO> createUser(
        @RequestBody User user) throws Exception {
        return APIResponse.createdStatus(this.userService.createUser(user));
    }

    @PutMapping("/{userId}")
    @ApiOperation(value = "Update user")
    public APIResponse<UserDTO> updateUser(
        @ApiParam(value = "User ID", required = true)
        @PathVariable(value = "userId", required = true) int userId,
        @RequestBody User user
    ) throws Exception {
        return APIResponse.okStatus(this.userService.updateUser(user, userId));
    }

    @DeleteMapping(value = "{userId}")
    public APIResponse<UserDTO> deleteUser(
            @ApiParam(value = "User ID" , required = true)
            @PathVariable(value = "userId", required = true) int userId
    ) throws Exception {
        return APIResponse.okStatus(this.userService.deleteUser(userId));
    }
}
