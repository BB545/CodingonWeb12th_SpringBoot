package codingon_kdt.spring_boot_jpa.service;

import codingon_kdt.spring_boot_jpa.domain.Community;
import codingon_kdt.spring_boot_jpa.domain.User;
import codingon_kdt.spring_boot_jpa.dto.CommunityDTO;
import codingon_kdt.spring_boot_jpa.dto.UserDTO;
import codingon_kdt.spring_boot_jpa.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommunityService {
    @Autowired
    private CommunityRepository communityRepository;

    public List<CommunityDTO> getAllComs() {
        List<Community> coms = communityRepository.findAll();
        List<CommunityDTO> communityDTOs = new ArrayList<>();

        for(Community com: coms) {
            CommunityDTO communityDTO = convertToDto(com);
            communityDTOs.add(communityDTO);
        }

        return communityDTOs;
    }

    public void createUser(CommunityDTO communityDto) {
        Community community = convertToEntity(communityDto);
        communityRepository.save(community);
    }

    public void updateUser(int id, CommunityDTO communityDto) {
        // 업데이트 시 userDto 는 username, email 정보만 가지고 있음
        Community community = convertToEntityWithId(id, communityDto);
        communityRepository.save(community);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private CommunityDTO convertToDto(Community com) {
        return CommunityDTO.builder()
                .id(com.getId())
                .title(com.getTitle())
                .content(com.getContent())
                .writer(com.getWriter())
                .no((int) (com.getId() + 100))
                .build();
    }

    private Community convertToEntity(CommunityDTO dto) {
        return Community.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
    }

    private Community convertToEntityWithId(int id, CommunityDTO dto) {
        return Community.builder()
                .id(id)
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
    }
}
