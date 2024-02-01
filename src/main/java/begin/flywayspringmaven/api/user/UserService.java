package begin.flywayspringmaven.api.user;

import begin.flywayspringmaven.api.user.dto.UserDTO;
import begin.flywayspringmaven.common.base.BaseService;
import begin.flywayspringmaven.common.model.User;
import begin.flywayspringmaven.common.repository.UserRepository;
import begin.flywayspringmaven.common.vo.PageInfo;
import begin.flywayspringmaven.util.FlywaySpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends BaseService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Get list of User Admin
     *
     * @param query Search Info: user's name or user's email
     * @param page current page
     * @param limit amount record in a page
     * @param strSort Sort by ascending or descending
     * @return {@link PageInfo} {@link UserDTO}
     */
    public PageInfo<UserDTO> getUserAdminsList(String query, Integer page, Integer limit, String strSort) {
        Sort sort = Sort.by(Sort.Order.asc("user.id"));
        String resultQuery = StringUtils.isEmpty(query) ? "" : query;
        PageRequest pageRequest = this.buildPageRequest(page, limit, sort);
        Page<UserDTO> data = this.userRepository.findAllUser(pageRequest);
        return FlywaySpringUtils.pagingResponse(data);
    }

    public UserDTO createUser(User userBody) throws Exception{
        User user = new User();
        user = this.userRepository.save(userBody);
        UserDTO saveUser = new UserDTO(user);
        return saveUser;
    }

    public UserDTO updateUser(User userBody, Integer userId) throws Exception {
       User user = this.getUserById(userId);
       user.setName(userBody.getName());
       user.setAge(userBody.getAge());
       user.setPhone(userBody.getPhone());
       user.setGender(userBody.getGender());
       user.setEmail(user.getEmail());
       this.userRepository.save(user);
       UserDTO saveUser = new UserDTO(user);
       return saveUser;
    }

    public UserDTO deleteUser(Integer userId) throws Exception {

    }

    private User getUserById(Integer userId) throws Exception {
        return this.userRepository.findUserById(userId).orElse(null);
    }
}
