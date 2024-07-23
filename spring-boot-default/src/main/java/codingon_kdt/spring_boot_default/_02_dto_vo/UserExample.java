package codingon_kdt.spring_boot_default._02_dto_vo;

import codingon_kdt.spring_boot_default._02_dto_vo.dto.UserDTO;

public class UserExample {
    public static void main(String[] args) {
        // UserDTO 객체 생성 (기본 생성자 사용)
        UserDTO u1 = new UserDTO();
        System.out.println("u1 = " + u1); // u1 = UserDTO{id=null, username='null', email='null', age=0}
        u1.setId(1L);
        u1.setUsername("JhonDoe");
        u1.setEmail("jhondoe@example.com");
        u1.setAge(30);

        // UserDTO 객체 정보 출력
        System.out.println("u1 = " + u1); // u1 = UserDTO{id=1, username='JhonDoe', email='jhondoe@example.com', age=30}

        // UserDTO 객체 생성 (모든 필드를 포함한 생성자 사용)
        UserDTO u2 = new UserDTO(2L, "JaneSmith", "janesmith@example.com", 25);
        System.out.println("u2 = " + u2);
        // u2 = UserDTO{id=2, username='JaneSmith', email='janesmith@example.com', age=25}

        // getter 를 이용해 특정 정보 접근
        System.out.println("u2.getUsername() = " + u2.getUsername());
        System.out.println("u2.getEmail() = " + u2.getEmail());

        // setter 를 이용해 정보 수정
        u2.setAge(26);
        System.out.println("u2 = " + u2);
        // u2 = UserDTO{id=2, username='JaneSmith', email='janesmith@example.com', age=26}
    }
}
