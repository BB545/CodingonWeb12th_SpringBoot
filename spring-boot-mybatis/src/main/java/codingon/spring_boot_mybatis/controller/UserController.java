package codingon.spring_boot_mybatis.controller;

import codingon.spring_boot_mybatis.dto.UserDTO;
import codingon.spring_boot_mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 해당 클래스의 모든 메서드의 반환값이 JSON 형식으로 변환되어 HTTP 응답 본문에 포함
@RequestMapping("/api/users") // 해당 컨트롤러의 기본 경로 설정
public class UserController {

    // UserService 의존성을 자동 주입
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
        // @RequestBody
        // - HTTP 요청 본문 내용을 자바 객체로 변환
        userService.createUser(userDTO);
        return userDTO;
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        userDTO.setId(id);
        userService.updateUser(userDTO);
        return userDTO;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }


}
