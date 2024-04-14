package begin.flywayspringmaven.api.store.dto;

import begin.flywayspringmaven.common.model.Store;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StoreDTO {
    private Integer id;
    private String name;
    private String avatar;
    private String address;
    private  String description;
    private  LocalDateTime openTime;
    private LocalDateTime closeTime;
    private String status;
    private LocalDateTime modified;
    private Object user;

    public StoreDTO(Store store) {
        this.id = store.getId();
        this.name = store.getName();
        this.user = store.getUser();
        this.avatar = store.getAvatar();
        this.address = store.getAddress();
        this.description = store.getDescription();
        this.openTime = store.getOpenTime();
        this.closeTime = store.getCloseTime();
        this.modified = store.getModified();
    }
}
