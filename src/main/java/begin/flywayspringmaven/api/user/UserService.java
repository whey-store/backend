package begin.flywayspringmaven.api.user;

import begin.flywayspringmaven.api.user.dto.UserDTO;
import begin.flywayspringmaven.api.user.dto.UserRequestDTO;
import begin.flywayspringmaven.common.base.BaseService;
import begin.flywayspringmaven.common.model.Role;
import begin.flywayspringmaven.common.model.User;
import begin.flywayspringmaven.common.repository.RoleRepository;
import begin.flywayspringmaven.common.repository.UserRepository;
import begin.flywayspringmaven.common.service.MessageService;
import begin.flywayspringmaven.common.vo.PageInfo;
import begin.flywayspringmaven.exception.NotFoundException;
import begin.flywayspringmaven.util.FlywaySpringUtils;
import begin.flywayspringmaven.util.constants.Constants;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService extends BaseService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final MessageService messageService;

    public UserService (UserRepository userRepository,
                        RoleRepository roleRepository,
                        BCryptPasswordEncoder bCryptPasswordEncoder,
                        MessageService messageService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.messageService = messageService;
    }


    /**
     * Get list of User Admin
     * @param query Search Info: user's name or user's email
     * @param page current page
     * @param limit amout recode in a page
     * @param strSort Sort by asc or desc
     * @return
     */
    public PageInfo<UserDTO> getUserAdminsList(String query, Integer page, Integer limit, String strSort) {
        Sort sort = Sort.by(Sort.Order.asc("user.id"));
        switch (strSort) {
            case Constants.SORT_OLDEST_TO_NEWEST:
                sort = Sort.by(Sort.Order.asc("user.modified"));
                break;

            case Constants.SORT_NEWEST_TO_OLDEST:
                sort = Sort.by(Sort.Order.desc("user.modified"));
                break;
            default:
                break;
        }
        String resultQuery = StringUtils.isEmpty(query) ? "" : query;
        PageRequest pageRequest = this.buildPageRequest(page,limit,sort);
        Page<UserDTO> data = userRepository.findAllUserByConditions(resultQuery, pageRequest).map(userDTO -> new UserDTO(userDTO));
        return FlywaySpringUtils.pagingResponse(data);
    }

    /**
     * Create user
     *
     * @param userRequestDTO
     * @return
     * @throws Exception
     */
    @Transactional
    public User createUser(UserRequestDTO userRequestDTO) throws NotFoundException {
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setGender(userRequestDTO.getGender());
        int roleId = userRequestDTO.getRoleId();
        Role role = roleRepository.findRoleById(roleId).orElseThrow(
                ()-> new NotFoundException(NotFoundException.ERROR_ROLE_NOT_FOUND,
                        messageService.buildMessages
                                ("error.msg.not-found", "info.user.action.create-user"))
        );
        user.setRole(role);
        user.setPhone(userRequestDTO.getPhone());
        user.setPassword(bCryptPasswordEncoder.encode(userRequestDTO.getPassword()));
        userRepository.save(user);
        return user;
    }

    public UserDTO updateUser(UserRequestDTO userBody, Integer userId) throws Exception {
       User user = this.getUserById(userId);
       user.setUsername(userBody.getUsername() != null ? userBody.getUsername() : user.getUsername());
       user.setAge(userBody.getAge() != null ? userBody.getAge() : user.getAge());
       user.setPhone(userBody.getPhone() != null ? userBody.getPhone() : user.getPhone());
       user.setGender(userBody.getGender() != null ? userBody.getGender() : user.getGender());
       user.setEmail(userBody.getEmail() != null ? userBody.getEmail() : user.getEmail());
       if(userBody.getRoleId() != null) {
           Role role = roleRepository.findRoleById(userBody.getRoleId()).orElseThrow(
                   ()-> new NotFoundException(NotFoundException.ERROR_ROLE_NOT_FOUND,
                           this.messageService.buildMessages
                                   ("error.msg.not-found", "info.user.action.create-user"))
           );
           user.setRole(role);
       }
       userRepository.save(user);
       UserDTO saveUser = new UserDTO(user);
       return saveUser;
    }

    public UserDTO deleteUser(Integer userId) throws Exception {
        User user = this.getUserById(userId);
        user.setDeleted(true);
        userRepository.save(user);
        UserDTO savaUser = new UserDTO(user);
        return savaUser;
    }

    private User getUserById(Integer userId) throws Exception {
        return userRepository.findUserById(userId).orElse(null);
    }
}
