package keyin.golf.Tournament;

import keyin.golf.Member.Member;

import keyin.golf.Member.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;

    private MemberRepository memberRepository;

    public TournamentService(TournamentRepository tournamentRepository, MemberRepository memberRepository) {
        this.tournamentRepository = tournamentRepository;
        this.memberRepository = memberRepository;

    }

    public Tournament addTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public Optional<Tournament> getTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public List<Tournament> searchTournamentsByLocation(String location) {
        return tournamentRepository.findByLocationIgnoreCase(location);
    }

    public List<Tournament> searchTournamentsByStartDate(LocalDate startDate) {
        return tournamentRepository.findByStartDate(startDate);
    }

    public List<Tournament> searchTournamentsByDateRange(LocalDate startDate, LocalDate endDate) {
        return tournamentRepository.findByStartDateBetween(startDate, endDate);
    }

    public Tournament getTournamentWithMembers(Long tournamentId) {
        return tournamentRepository.findTournamentWithMembers(tournamentId);
    }

    public void addMemberToTournament(Long tournamentId, Long memberId) throws Exception {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new Exception("Tournament not found"));
        System.out.println("Tournament found: " + tournament);

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new Exception("Member not found"));
        System.out.println("Member found: " + member);

        tournament.addMember(member);

        tournamentRepository.save(tournament);
    }



}

