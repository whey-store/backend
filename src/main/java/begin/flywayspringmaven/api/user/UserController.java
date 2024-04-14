package begin.flywayspringmaven.api.user;

import begin.flywayspringmaven.api.user.dto.UserDTO;
import begin.flywayspringmaven.api.user.dto.UserRequestDTO;
import begin.flywayspringmaven.common.response.APIResponse;
import begin.flywayspringmaven.common.vo.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @ApiOperation(value = "Get All Users")
    public APIResponse<PageInfo<UserDTO>> getUserList(
            @ApiParam(value = "Search info: user's name or user's email", required = false)
            @RequestParam(value = "query", required = false) String query,
            @ApiParam(value = "Page", required = false)
            @RequestParam(value = "page", required = false) Integer page,
            @ApiParam(value = "Limit", required = false)
            @RequestParam(value = "limit", required = false) Integer limit,
            @ApiParam(value = "Sort by", required = true, allowableValues = "OLDEST_TO_NEWEST, NEWEST_TO_OLDEST")
            @RequestParam(value = "sort", required = true) String sort
    ){
        return APIResponse.okStatus(userService.getUserAdminsList(query, page, limit, sort));
    }

    @PutMapping("/users/{userId}")
    @ApiOperation(value = "Update user")
    public APIResponse<UserDTO> updateUser(
        @ApiParam(value = "User ID", required = true)
        @PathVariable(value = "userId", required = true) int userId,
        @RequestBody UserRequestDTO userRequestDTO
    ) throws Exception {
        return APIResponse.okStatus(userService.updateUser(userRequestDTO, userId));
    }

    @DeleteMapping(value = "/users/{userId}")
    public APIResponse<UserDTO> deleteUser(
            @ApiParam(value = "User ID" , required = true)
            @PathVariable(value = "userId", required = true) int userId
    ) throws Exception {
        return APIResponse.okStatus(userService.deleteUser(userId));
    }
}
