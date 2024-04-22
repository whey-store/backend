package begin.flywayspringmaven.api.store;

import begin.flywayspringmaven.api.store.dto.StoreDTO;
import begin.flywayspringmaven.api.store.dto.StoreRequestDTO;
import begin.flywayspringmaven.common.response.APIResponse;
import begin.flywayspringmaven.exception.NotFoundException;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class StoreController {
    private final StoreService storeService;
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/stores")
    @ApiOperation(value = "Create store")
    public APIResponse<StoreDTO> createStore(
            @RequestBody StoreRequestDTO storeRequestDTO) throws NotFoundException {
        return APIResponse.createdStatus(storeService.createStore(storeRequestDTO));
    }
}
