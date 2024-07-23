package codingon_kdt.spring_boot_jpa.repository;

import codingon_kdt.spring_boot_jpa.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Integer> {
}
