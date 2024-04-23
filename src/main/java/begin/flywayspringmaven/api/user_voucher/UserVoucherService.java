package begin.flywayspringmaven.api.user_voucher;

import begin.flywayspringmaven.api.user.dto.UserDTO;
import begin.flywayspringmaven.api.user_voucher.dto.UserVoucherDTO;
import begin.flywayspringmaven.api.user_voucher.dto.UserVoucherRequestDTO;
import begin.flywayspringmaven.api.voucher.dto.VoucherDTO;
import begin.flywayspringmaven.common.base.BaseService;
import begin.flywayspringmaven.common.model.User;
import begin.flywayspringmaven.common.model.Voucher;
import begin.flywayspringmaven.common.repository.UserRepository;
import begin.flywayspringmaven.common.repository.VoucherRepository;
import begin.flywayspringmaven.config.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserVoucherService extends BaseService {

    private final VoucherRepository voucherRepository;
    private final UserRepository userRepository;

    public UserVoucherService(VoucherRepository voucherRepository,
                              UserRepository userRepository) {
        this.voucherRepository = voucherRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public UserVoucherDTO createUserVoucher(UserVoucherRequestDTO userVoucherRequestDTO) throws Exception {
        List<Integer> listVoucherIds = userVoucherRequestDTO.getListVoucher();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        Integer userId = userPrincipal.getUserId();
        User user = this.getUserById(userId);

        List<Voucher> listVoucher = null;
        if (!listVoucherIds.isEmpty()) {
            listVoucher = this.voucherRepository.findVoucherByListId(listVoucherIds);
            user.getVouchers().addAll(listVoucher);
        }
        userRepository.save(user);
        UserVoucherDTO saveUserVoucher = new UserVoucherDTO(user);
        return saveUserVoucher;
    }
}
