package begin.flywayspringmaven.api.user_voucher;

import begin.flywayspringmaven.api.user.dto.UserDTO;
import begin.flywayspringmaven.api.user_voucher.dto.UserVoucherDTO;
import begin.flywayspringmaven.api.user_voucher.dto.UserVoucherRequestDTO;
import begin.flywayspringmaven.common.response.APIResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserVoucherController {

    private final UserVoucherService userVoucherService;

    public UserVoucherController(UserVoucherService userVoucherService) {
        this.userVoucherService = userVoucherService;
    }

    @PostMapping("/user-vouchers")
    public APIResponse<UserVoucherDTO> createUserVoucher(
        @RequestBody UserVoucherRequestDTO userVoucherRequestDTO
    ) throws Exception {
        return APIResponse.okStatus(this.userVoucherService.createUserVoucher(userVoucherRequestDTO));
    }
}
