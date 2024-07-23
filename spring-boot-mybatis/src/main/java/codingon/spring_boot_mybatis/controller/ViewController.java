package codingon.spring_boot_mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    // GET / 요청 시 /users 리다이렉트
    @GetMapping("/")
    public String redirectToUsers() {
        return "redirect:/users";
    }

    // GET /users 요청 시 userList.html 템플릿 뷰 반환
    @GetMapping("/users")
    public String listUsers() {
        return "userList";
    }

    // GET /users/new 요청 시 userForm.html 템플릿 뷰 반환 (생성 시)
    @GetMapping("/users/new")
    public String newUserForm() {
        return "userForm";
    }

    // GET /users/{id}/edit 요청 시 userForm.html 템플릿 뷰 반환 (수정 시)
    @GetMapping("/users/{id}/edit")
    public String editUserForm() {
        return "userForm";
    }


}
