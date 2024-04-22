package begin.flywayspringmaven.api.voucher.dto;

import begin.flywayspringmaven.common.model.Voucher;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VoucherDTO {
    private String name;
    private Float percentReduction;
    private Integer quantity;
    private LocalDateTime startDay;
    private LocalDateTime endDay;
    private Integer storeId;

    public VoucherDTO(Voucher voucher) {
        this.name = voucher.getName();
        this.percentReduction = voucher.getPercentReduction();
        this.quantity = voucher.getQuantity();
        this.startDay = voucher.getStartDay();
        this.endDay = voucher.getEndDay();
    }
}
