package keyin.golf.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // Find members by name (case-insensitive)
    List<Member> findByNameIgnoreCase(String name);

    // Find members by phone number
    List<Member> findByPhoneNumber(String phoneNumber);

    // Find members by membership start date
    List<Member> findByStartDate(LocalDate startDate);

    // Find members whose membership type or email contains a specific term
    @Query("SELECT m FROM Member m WHERE LOWER(m.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Member> searchByKeyword(String keyword);
}