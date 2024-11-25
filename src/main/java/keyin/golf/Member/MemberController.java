package keyin.golf.Member;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        Member savedMember = memberService.addMember(member);
        return ResponseEntity.ok(savedMember);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Optional<Member> member = memberService.getMemberById(id);
        return member.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }


    @GetMapping("/search")
    public ResponseEntity<List<Member>> searchMembers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String keyword) {
        if (name != null) {
            return ResponseEntity.ok(memberService.searchMembersByName(name));
        } else if (phoneNumber != null) {
            return ResponseEntity.ok(memberService.searchMembersByPhoneNumber(phoneNumber));
        } else if (startDate != null) {
            try {
                LocalDate parsedDate = LocalDate.parse(startDate);
                return ResponseEntity.ok(memberService.searchMembersByStartDate(parsedDate));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(List.of());
            }
        } else if (keyword != null) {
            return ResponseEntity.ok(memberService.searchMembersByKeyword(keyword));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}

