package begin.flywayspringmaven.api.voucher;

import begin.flywayspringmaven.api.voucher.dto.VoucherDTO;
import begin.flywayspringmaven.api.voucher.dto.VoucherRequestDTO;
import begin.flywayspringmaven.common.base.BaseService;
import begin.flywayspringmaven.common.model.Store;
import begin.flywayspringmaven.common.model.Voucher;
import begin.flywayspringmaven.common.repository.VoucherRepository;
import begin.flywayspringmaven.common.service.MessageService;
import begin.flywayspringmaven.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class VoucherService extends BaseService {

    private final VoucherRepository voucherRepository;
    private final MessageService messageService;
    public VoucherService(VoucherRepository voucherRepository,
                          MessageService messageService) {
        this.voucherRepository = voucherRepository;
        this.messageService = messageService;
    }

    @Transactional(rollbackFor = Exception.class)
    public VoucherDTO createVoucher(VoucherRequestDTO voucherDTO) throws NotFoundException {
        Store store = this.getStoreById(voucherDTO.getStoreId());
        if(store == null) {
            throw new NotFoundException(NotFoundException.ERROR_STORE_NOT_FOUND,
                this.messageService.buildMessages("error.msg.not-found", "info.store"));
        }
        Voucher voucher = new Voucher();
        voucher.setName(voucherDTO.getName());
        voucher.setQuantity(voucherDTO.getQuantity());
        voucher.setStartDay(voucherDTO.getStartDay());
        voucher.setEndDay(voucherDTO.getEndDay());
        voucher.setPercentReduction(voucherDTO.getPercentReduction());
        voucher.setStore(store);
        voucherRepository.save(voucher);
        VoucherDTO saveVoucher = new VoucherDTO(voucher);
        return saveVoucher;
    }
}
