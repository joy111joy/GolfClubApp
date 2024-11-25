package keyin.golf.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByNameIgnoreCase(String name);

    List<Member> findByPhoneNumber(String phoneNumber);

    List<Member> findByStartDate(LocalDate startDate);

    @Query("SELECT m FROM Member m WHERE LOWER(m.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Member> searchByKeyword(String keyword);
}