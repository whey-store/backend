package begin.flywayspringmaven.api.store;

import begin.flywayspringmaven.api.store.dto.StoreDTO;
import begin.flywayspringmaven.api.store.dto.StoreRequestDTO;
import begin.flywayspringmaven.api.user.dto.UserDTO;
import begin.flywayspringmaven.common.base.BaseService;
import begin.flywayspringmaven.common.model.Store;
import begin.flywayspringmaven.common.model.User;
import begin.flywayspringmaven.common.repository.StoreRepository;
import begin.flywayspringmaven.common.repository.UserRepository;
import begin.flywayspringmaven.common.service.MessageService;
import begin.flywayspringmaven.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StoreService extends BaseService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final MessageService messageService;
    public StoreService(UserRepository userRepository,
                        StoreRepository storeRepository,
                        MessageService messageService) {
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
        this.messageService = messageService;
    }


    /**
     * Create store
     *
     * @param storeRequestDTO
     * @return
     * @throws Exception
     */
    @Transactional
    public StoreDTO createStore(StoreRequestDTO storeRequestDTO) throws NotFoundException {
        Store store = new Store();
        store.setName(storeRequestDTO.getName());
        store.setAddress(storeRequestDTO.getAddress());
        store.setDescription(storeRequestDTO.getDescription());
        store.setOpenTime(storeRequestDTO.getOpenTime());
        store.setCloseTime(storeRequestDTO.getCloseTime());
        store.setStatus(storeRequestDTO.getStatus());
        int userId = storeRequestDTO.getUserId();
        User user = userRepository.findUserById(userId).orElseThrow(
                ()-> new NotFoundException(NotFoundException.ERROR_ROLE_NOT_FOUND,
                        messageService.buildMessages
                                ("error.msg.not-found", "info.user.action.create-user"))
        );
        store.setUser(user);
        storeRepository.save(store);
        StoreDTO saveStore = new StoreDTO(store);
        return saveStore;
    }

}
