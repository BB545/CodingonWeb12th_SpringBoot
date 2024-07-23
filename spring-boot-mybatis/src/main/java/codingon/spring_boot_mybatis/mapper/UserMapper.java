package codingon.spring_boot_mybatis.mapper;

import codingon.spring_boot_mybatis.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper // 해당 인터페이스가 MyBatis mapper 임을 나타냄
public interface UserMapper {

    // case1. 어노테이션 기반 매퍼
    // - 간단한 쿼리의 경우 사용하기 편리
    // - Java 코드 내에서 SQL 을 직접 볼 수 있어서 즉각적인 이해 가능
    // - @Select, @Insert, @Update, @Delete 어노테이션 사용
    // @Select("select * from users")
    // List<User> findAll(); // 모든 사용자 조회

    //
    //@Select("select * from users where id = #{id}")
    //User findById(Long id); // 특정 ID 의 사용자를 조회
    //
    //@Insert("insert into users (username, email) values (#{username}, #{email})")
    //@Options(useGeneratedKeys = true, keyProperty = "id")
    //// Insert 작업에 대한 추가 옵션 설정
    //// - useGeneratedKeys = true; DB 에서 auto-increment pk 를 사용하겠다는 의미
    //// - keyProperty = "id"; 생성된 User 객체의 어떤 속성에 설정할지 지정
    //// 만약, id(pk) 를 수동으로 설정한다면 @Options 어노테이션은 필요하지 않음
    //void insert(User user); // 새 사용자를 추가, Options 어노테이션으로 생성된 키를 User 객체에 설정
    //
    //@Update("update users set username = #{username}, email = #{email} where id = #{id}")
    //void update(User user); // 사용자 정보를 업데이트
    //
    //@Delete("delete from users where id = #{id}")
    //void delete(Long id); // 특정 ID 의 사용자를 삭제


    // case2. XML 기반 매퍼
    // - 복잡한 SQL 쿼리를 쉽게 관리 가능
    // - SQL 과 Java 코드를 분리해서 관리 가능
    // - 대규모 프로젝트나 복잡한 데이터 조작에 적합
    List<User> findAll(); // 모든 사용자 조회

    User findById(Long id); // 특정 ID 의 사용자를 조회

    void insert(User user); // 새 사용자를 추가, Options 어노테이션으로 생성된 키를 User 객체에 설정

    void update(User user); // 사용자 정보를 업데이트

    void delete(Long id); // 특정 ID 의 사용자를 삭제


}
