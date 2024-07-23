package codingon_kdt.spring_boot_jpa.service;

import codingon_kdt.spring_boot_jpa.domain.User;
import codingon_kdt.spring_boot_jpa.dto.UserDTO;
import codingon_kdt.spring_boot_jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();

        for(User user: users) {
            UserDTO userDTO = convertToDto(user);
            userDTOs.add(userDTO);
        }

        return userDTOs;
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return convertToDto(user);
    }

    public void createUser(UserDTO userDto) {
        User user = convertToEntity(userDto);
        userRepository.save(user);
    }

    public void updateUser(Long id, UserDTO userDto) {
        // 업데이트 시 userDto 는 username, email 정보만 가지고 있음
        User user = convertToEntityWithId(id, userDto);
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    ////////////////////////////////////////////

    // 1. 사용자 이름으로 n 명 조회
    public List<UserDTO> getUserByUsername(String username) {
        List<User> users = userRepository.findByUsername(username);
        List<UserDTO> userDTOs = new ArrayList<>(); // 프론트에 보낼때 dto 로 바꿔서 보내야함
        System.out.println(userDTOs);
        for (User user: users) {
            userDTOs.add(convertToDto(user));
        }

        return userDTOs;
    }

    // 2. 검색어를 보냈을 때 사용자 이름/이메일에 특정 문자열이 포함된 모든 사용자 n명 찾기
    public List<UserDTO> searchUsers(String keyword) {
        // keyword 에 대해서 username, email 모두에서 검색하고 싶기 때문에 매개변수 두개 모두 보내줌

        // case1.
        // List<User> users = userRepository.findByUsernameContainingOrEmailContaining(keyword, keyword);
        // case2.
        List<User> users = userRepository.findByUsernameContainingOrEmailContaining(keyword);
        List<UserDTO> userDTOs = new ArrayList<>(); // 프론트에 보낼때 dto 로 바꿔서 보내야함
        for (User user: users) {
            userDTOs.add(convertToDto(user));
        }

        return userDTOs;
    }

    // 3. 이름이 존재하는지 조회
    public boolean isUsernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    ////////////////////////////////////////////

    // 빌더 패턴
    private UserDTO convertToDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .no((int) (user.getId() + 100))
                .build();
    }

    private User convertToEntity(UserDTO dto) {
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .build();
    }

    private User convertToEntityWithId(Long id, UserDTO dto) {
        return User.builder()
                .id(id)
                .username(dto.getUsername())
                .email(dto.getEmail())
                .build();
    }

}
