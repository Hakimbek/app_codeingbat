package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.User;
import uz.pdp.appcodingbat.payload.Result;
import uz.pdp.appcodingbat.payload.UserDto;
import uz.pdp.appcodingbat.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    /**
     * GET USER LIST
     *
     * @return USER LIST
     */
    public List<User> get() {
        return userRepository.findAll();
    }


    /**
     * GET ONE USER BY ID
     *
     * @param id INTEGER
     * @return ONE USER
     */
    public User getById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }


    /**
     * DELETE USER BY ID
     *
     * @param id INTEGER
     * @return RESULT
     */
    public Result delete(Integer id) {
        try {
            userRepository.deleteById(id);
            return new Result("Successfully deleted", true);
        } catch (Exception e) {
            return new Result("Error", false);
        }
    }


    /**
     * ADD USER
     *
     * @param userDto EMAIL (String), PASSWORD (String)
     * @return RESULT
     */
    public Result add(UserDto userDto) {
        boolean existsByEmail = userRepository.existsByEmail(userDto.getEmail());
        if (existsByEmail) {
            return new Result("User already exist", false);
        }

        User user = new User(userDto.getEmail(), userDto.getPassword());
        userRepository.save(user);
        return new Result("Successfully added", true);
    }


    /**
     * EDIT USER BY ID
     *
     * @param id          INTEGER
     * @param userDto EMAIL (String), PASSWORD (String)
     * @return RESULT
     */
    public Result edit(Integer id, UserDto userDto) {
        boolean existsByEmailAndIdNot = userRepository.existsByEmailAndIdNot(userDto.getEmail(), id);
        if (existsByEmailAndIdNot) {
            return new Result("User already exist", false);
        }

        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return new Result("User not found", false);
        }

        User editedUser = optionalUser.get();
        editedUser.setEmail(userDto.getEmail());
        editedUser.setPassword(userDto.getPassword());
        userRepository.save(editedUser);
        return new Result("Successfully edited", true);
    }
}
