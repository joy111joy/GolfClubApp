package keyin.golf.Member;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // Add a new member
    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    // Get a member by ID
    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    // Get all members
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // Search members by name (case-insensitive)
    public List<Member> searchMembersByName(String name) {
        return memberRepository.findByNameIgnoreCase(name);
    }

    // Search members by phone number
    public List<Member> searchMembersByPhoneNumber(String phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber);
    }

    // Search members by start date
    public List<Member> searchMembersByStartDate(LocalDate startDate) {
        return memberRepository.findByStartDate(startDate);
    }

    // Search members by keyword in email
    public List<Member> searchMembersByKeyword(String keyword) {
        return memberRepository.searchByKeyword(keyword);
    }
}

