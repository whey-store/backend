package begin.flywayspringmaven.api.user;

import begin.flywayspringmaven.api.user.dto.UserDTO;
import begin.flywayspringmaven.common.response.APIResponse;
import begin.flywayspringmaven.common.vo.PageInfo;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;
    public APIResponse<PageInfo<UserDTO>> getUserList(
        @ApiParam(value = "Search Info: user's name or user's email", required = false)
        @RequestParam(value = "query", required = false) String query,
        @ApiParam(value = "Page", required = false)
        @RequestParam(value = "page", required = false) Integer page,
        @ApiParam(value = "Limit", required = false)
        @RequestParam(value = "limit", required = false) Integer limit,
        @ApiParam(value = "Sort by", required = true, allowableValues = "OLDEST_TO_NEWEST, NEWEST_TO_OLDEST")
        @RequestParam(value = "sort", required = true) String sort
    ){
        return APIResponse.okStatus(this.userService.getUserAdminsList(query, page, limit, sort));
    }

}
