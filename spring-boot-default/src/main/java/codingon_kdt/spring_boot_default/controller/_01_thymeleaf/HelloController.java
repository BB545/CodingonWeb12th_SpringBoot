package codingon_kdt.spring_boot_default.controller._01_thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

// @Controller
// - Spring MVC 에 Controller 클래스로 인식되어 Spring MVC 가 제공하는 다양한 어노테이션 사용
@Controller
public class HelloController {
    // 클라이언트 요청에 대한 처리는 각 메서드 작성

    // @GetMapping 어노테이션
    // - GET 요청에 대한 URL 을 매핑시켜줌
    // GET localhost:PORT/hi 요청이 들어오면 여기로 매핑됨
    @GetMapping("hi")
    public String getHi(Model model) {
        // Model model
        // - Spring MVC 가 제공하는 타입 (View 에 값을 전달하는 상자 역할일 뿐, MVC 의 Model 과는 별개)
        // - View 에서 참조할 수 있는 객체 저장 기능
        // - Controller 클래스의 메서드가 파라미터로 받을 수 있는 객체
        
        // ver1
        // model.addAttribute("msg", "Hi~");

        // ver2. Thymeleaf 표현식과 문법
        Person p = new Person("코딩온", 10);
        List<String> names = Arrays.asList("kim", "lee", "jeong", "park");

        model.addAttribute("hello", "Spring World");
        model.addAttribute("uText", "<u>HELLO!</u>");
        model.addAttribute("value", "이름을 입력하세요");
        model.addAttribute("withValue", "minion");
        model.addAttribute("link", "hi");
        model.addAttribute("imgSrc", "image/img1.png");
        model.addAttribute("userRole", "admin");
        model.addAttribute("isAdmin", false);
        model.addAttribute("names", names);
        model.addAttribute("person", p);

        // 템플릿 파일명 변환
        // src/resources/templates/_01_thymeleaf/hi.html 을 반환
        return "_01_thymeleaf/hi";
    }

    class Person {
        private int age;
        private String name;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }
    }
}
