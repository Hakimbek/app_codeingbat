package uz.pdp.appcodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entity.Language;
import uz.pdp.appcodingbat.entity.User;
import uz.pdp.appcodingbat.payload.LanguageDto;
import uz.pdp.appcodingbat.payload.Result;
import uz.pdp.appcodingbat.payload.UserDto;
import uz.pdp.appcodingbat.service.LanguageService;
import uz.pdp.appcodingbat.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;


    /**
     * GET USER LIST
     *
     * @return USER LIST
     */
    @GetMapping
    public ResponseEntity<List<User>> get() {
        List<User> users = userService.get();
        return ResponseEntity.ok(users);
    }


    /**
     * GET ONE USER BY ID
     *
     * @param id INTEGER
     * @return ONE USER
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Integer id) {
        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }


    /**
     * DELETE USER BY ID
     *
     * @param id INTEGER
     * @return RESULT
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id) {
        Result result = userService.delete(id);
        return ResponseEntity.status(result.isSuccess() ? 200 : 405).body(result);
    }


    /**
     * ADD USER
     *
     * @param userDto EMAIL (String), PASSWORD (String)
     * @return RESULT
     */
    @PostMapping
    public ResponseEntity<Result> add(@RequestBody UserDto userDto) {
        Result result = userService.add(userDto);
        return ResponseEntity.status(result.isSuccess() ? 201 : 409).body(result);
    }


    /**
     * EDIT USER BY ID
     *
     * @param id          INTEGER
     * @param userDto EMAIL (String), PASSWORD (String)
     * @return RESULT
     */
    @PutMapping("/{id}")
    public ResponseEntity<Result> edit(@PathVariable Integer id, @RequestBody UserDto userDto) {
        Result result = userService.edit(id, userDto);
        return ResponseEntity.status(result.isSuccess() ? 202 : 409).body(result);
    }
}
