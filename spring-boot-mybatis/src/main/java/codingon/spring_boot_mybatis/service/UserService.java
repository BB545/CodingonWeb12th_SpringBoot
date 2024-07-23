package codingon.spring_boot_mybatis.service;

import codingon.spring_boot_mybatis.domain.User;
import codingon.spring_boot_mybatis.dto.UserDTO;
import codingon.spring_boot_mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service // 이 클래스가 서비스 계층의 컴포넌트임을 나타냄
public class UserService {
    // UserMapper 인터페이스의 구현체를 자동으로 주입받음
    @Autowired
    private UserMapper userMapper;

    // 모든 사용자의 정보를 UserDTO 리스트로 반환
    public List<UserDTO> getAllUsers() {
        List<User> users = userMapper.findAll(); // 모든 User 리스트 가져옴
        List<UserDTO> userDTOs = new ArrayList<>(); // 새로운 DTO 객체 리스트 생성
        
        for(User user: users) {
            // for 루프를 이용해 각 User 객체를 UserDTO 로 변환하고 리스트에 추가
            UserDTO userDTO = convertToDto(user); // domain to dto
            userDTOs.add(userDTO); // dto 를 리스트에 추가
        }

        return userDTOs;
    }

    // 특정 ID 의 사용자 정보를 UserDTO 로 반환
    public UserDTO getUserById(Long id) {
        User user = userMapper.findById(id);
        return convertToDto(user);
    }

    // 새 사용자 생성
    public void createUser(UserDTO userDto) {
        User user = convertToEntity(userDto);
        userMapper.insert(user);
    }

    // 사용자 정보 업데이트
    public void updateUser(UserDTO userDto) {
        User user = convertToEntity(userDto);
        userMapper.update(user);
    }

    // 특정 ID 의 사용자 삭제
    public void deleteUser(Long id) {
        userMapper.delete(id);
    }

    // dto to domain
    private User convertToEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());

        return user;
    }

    // 외부에서 사용할 필요가 없기 때문에 그냥 private 으로 정의
    // domain to dto
    private UserDTO convertToDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setNo((int) (user.getId() + 100)); // int 로 형변환

        return dto;
    }
}
