package codingon_kdt.spring_boot_default._02_dto_vo.dto;

// DTO (Data Transfer Object)
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private int age;

    // 기본 생성자
    public UserDTO() {
    }

    // 모든 필드를 포함한 생성자
    public UserDTO(Long id, String username, String email, int age) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.age = age;
    }

    // getter
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    // setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // toString 메서드 오버라이드
    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
