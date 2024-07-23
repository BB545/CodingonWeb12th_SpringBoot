package codingon_kdt.spring_boot_default.controller.vo;

import lombok.Getter;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Getter
public class UserVO {
    private String name;
    private int age;
    private String id;
    private String password;
    private String gender;
    private String year;
    private String month;
    private String day;
    private List<String> hobby;
}

// vo 객체 특징
// - 불변(immutable) 객체
// - setter 메소드 없음
