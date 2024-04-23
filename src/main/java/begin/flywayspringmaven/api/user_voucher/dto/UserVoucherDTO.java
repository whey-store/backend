package begin.flywayspringmaven.api.user_voucher.dto;

import begin.flywayspringmaven.common.model.User;
import begin.flywayspringmaven.common.model.Voucher;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserVoucherDTO {
    private Integer id;
    private String username;
    private String email;
    private String phone;
    private  String avatar;
    private  Integer gender;
    private Integer age;
    private List<Voucher> vouchers;
    public UserVoucherDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.avatar = user.getAvatar();
        this.gender = user.getGender();
        this.vouchers = user.getVouchers();
    }
}
