package codingon_kdt.spring_boot_default.controller._02_restapi;

import codingon_kdt.spring_boot_default.controller.vo.UserVO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class Practice1Controller {
    @GetMapping("/prac1")
    public String getReq() {
        return "_02_restapi/practice1";
    }

    @GetMapping("/introduce/{name}")
    @ResponseBody
    public String getResPrac1(@PathVariable String name) {
        return "내 이름은 " + name;
    }

    @GetMapping("/introduce2")
    @ResponseBody
    public String getResPrac2(@RequestParam String name, @RequestParam int age) {
        return "내 이름은 " + name + System.lineSeparator() + "내 나이는 " + age;
    }

    @PostMapping("/introduce3")
    @ResponseBody
    public String getResPrac3(@RequestParam String name,
                              @RequestParam String gender,
                              @RequestParam String year,
                              @RequestParam String month,
                              @RequestParam String day,
                              @RequestParam String hobby) {
        return "이름: " + name + "<br>"
                + "성별: " + gender + "<br>"
                + "생년월일: " + year + "-" + month + "-" + day + "<br>"
                + "관심사: " + hobby;
    }

    // ==== Axios with VO ====
    @PostMapping("/axios/signup")
    @ResponseBody
    public String axiosVoPrac1(@RequestBody UserVO userVO) {
        String hobbies = String.join(", ", userVO.getHobby());
        return userVO.getName();
    }

    @PostMapping("/crud/signup")
    @ResponseBody
    public String crudPrac1(@RequestBody UserVO userVO) {
        return userVO.getName();
    }

    @PostMapping("/crud/login")
    @ResponseBody
    public String crudPrac2(@RequestBody UserVO userVO) {
        return userVO.getName();
    }

    @PostMapping("/crud/modify")
    @ResponseBody
    public String crudPrac3(@RequestBody UserVO userVO) {
        return userVO.getName();
    }

    @PostMapping("/crud/drop")
    @ResponseBody
    public String crudPrac4(@RequestBody UserVO userVO) {
        return userVO.getName();
    }
}
