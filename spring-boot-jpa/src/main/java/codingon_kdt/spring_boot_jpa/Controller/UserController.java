package codingon_kdt.spring_boot_jpa.Controller;

import codingon_kdt.spring_boot_jpa.dto.UserDTO;
import codingon_kdt.spring_boot_jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> listUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return userDTO;
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
//        userDTO.setId(id);
        userService.updateUser(id, userDTO);
        return userDTO;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    //////////////////////////////////////////////

    // 1. 사용자 이름으로 n 명 조회
    @GetMapping("/byUsername")
    public List<UserDTO> getUserByUsername(@RequestParam String username) {
        if (userService.isUsernameExists(username)) {
            return userService.getUserByUsername(username);
        }
        return userService.getUserByUsername("");
    }

    // 2. 검색어를 보냈을 때 사용자 이름/이메일에 특정 문자열이 포함된 모든 사용자 n명 찾기
    @GetMapping("/search")
    public List<UserDTO> searchUsers(@RequestParam String keyword) {
        return userService.searchUsers(keyword);
    }

    // 3. 이름이 존재하는지 조회
    @GetMapping("/exists")
    public boolean isUsernameExists(@RequestParam String username) {
        return userService.isUsernameExists(username);
    }
}
