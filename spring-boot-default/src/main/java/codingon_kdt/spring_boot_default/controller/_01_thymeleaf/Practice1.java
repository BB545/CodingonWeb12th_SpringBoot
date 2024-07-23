package codingon_kdt.spring_boot_default.controller._01_thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Practice1 {
    @GetMapping ("practice1")
    public String getPrac(Model model) {
        model.addAttribute("age", 20);

        List<Person> p = new ArrayList<>();
        p.add(new Person("홍길동", 10));
        p.add(new Person("성춘향", 20));
        p.add(new Person("이몽룡", 30));
        p.add(new Person("김영희", 40));
        p.add(new Person("김철수", 50));

        model.addAttribute("list", p);

        return "_01_thymeleaf/practice1";
    }

    class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
