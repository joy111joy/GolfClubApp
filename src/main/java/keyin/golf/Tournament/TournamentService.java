package keyin.golf.Tournament;

import keyin.golf.Member.Member;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;

    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    // Add a new tournament
    public Tournament addTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    // Get a tournament by ID
    public Optional<Tournament> getTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }

    // Get all tournaments
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    // Search tournaments by location (case-insensitive)
    public List<Tournament> searchTournamentsByLocation(String location) {
        return tournamentRepository.findByLocationIgnoreCase(location);
    }

    // Search tournaments by start date
    public List<Tournament> searchTournamentsByStartDate(LocalDate startDate) {
        return tournamentRepository.findByStartDate(startDate);
    }

    // Search tournaments within a date range
    public List<Tournament> searchTournamentsByDateRange(LocalDate startDate, LocalDate endDate) {
        return tournamentRepository.findByStartDateBetween(startDate, endDate);
    }

    // Find a tournament and fetch its members
    public Tournament getTournamentWithMembers(Long tournamentId) {
        return tournamentRepository.findTournamentWithMembers(tournamentId);
    }

    // Add a member to a tournament
    public void addMemberToTournament(Long tournamentId, Member member) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new RuntimeException("Tournament not found with ID: " + tournamentId));
        tournament.addMember(member);
        tournamentRepository.save(tournament);
    }
}

