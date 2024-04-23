package begin.flywayspringmaven.api.user_voucher.dto;

import begin.flywayspringmaven.common.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserVoucherRequestDTO {
    private List<Integer> listVoucher;
}
