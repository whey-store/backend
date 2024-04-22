package begin.flywayspringmaven.api.voucher;

import begin.flywayspringmaven.api.voucher.dto.VoucherDTO;
import begin.flywayspringmaven.common.response.APIResponse;
import begin.flywayspringmaven.exception.NotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class VoucherController {

    private final VoucherService voucherService;

    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @PostMapping("/vouchers")
    public APIResponse<VoucherDTO> createVoucher(@RequestBody VoucherDTO voucherDTO) throws NotFoundException {
        return APIResponse.okStatus(this.voucherService.createVoucher(voucherDTO));
    }
}
