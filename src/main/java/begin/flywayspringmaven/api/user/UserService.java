package begin.flywayspringmaven.api.user;

import begin.flywayspringmaven.api.user.dto.UserDTO;
import begin.flywayspringmaven.common.base.BaseService;
import begin.flywayspringmaven.common.model.User;
import begin.flywayspringmaven.common.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends BaseService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Get list of User Admin
     */
    public List<UserDTO> getUserAdminsList() {
        List<UserDTO> userDTOList = new ArrayList<>();
        List<User> users = this.userRepository.findAllUser();
        for (User user : users) {
            userDTOList.add(new UserDTO(user));
        }
        return userDTOList;
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
        User user = this.getUserById(userId);
        user.setDeleted(true);
        this.userRepository.save(user);
        UserDTO savaUser = new UserDTO(user);
        return savaUser;
    }

    private User getUserById(Integer userId) throws Exception {
        return this.userRepository.findUserById(userId).orElse(null);
    }
}
