package keyin.golf.Tournament;

import keyin.golf.Member.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    // Add a new tournament
    @PostMapping
    public ResponseEntity<Tournament> addTournament(@RequestBody Tournament tournament) {
        Tournament savedTournament = tournamentService.addTournament(tournament);
        return ResponseEntity.ok(savedTournament);
    }

    // Get a tournament by ID
    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable Long id) {
        Optional<Tournament> tournament = tournamentService.getTournamentById(id);
        return tournament.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all tournaments
    @GetMapping
    public ResponseEntity<List<Tournament>> getAllTournaments() {
        List<Tournament> tournaments = tournamentService.getAllTournaments();
        return ResponseEntity.ok(tournaments);
    }

    // Search tournaments
    @GetMapping("/search")
    public ResponseEntity<List<Tournament>> searchTournaments(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {
        if (location != null) {
            return ResponseEntity.ok(tournamentService.searchTournamentsByLocation(location));
        } else if (startDate != null && endDate != null) {
            return ResponseEntity.ok(tournamentService.searchTournamentsByDateRange(startDate, endDate));
        } else if (startDate != null) {
            return ResponseEntity.ok(tournamentService.searchTournamentsByStartDate(startDate));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // Add a member to a tournament
    @PostMapping("/{tournamentId}/members")
    public ResponseEntity<Void> addMemberToTournament(@PathVariable Long tournamentId, @RequestBody Member member) {
        tournamentService.addMemberToTournament(tournamentId, member);
        return ResponseEntity.ok().build();
    }

    // Get all members in a tournament
    @GetMapping("/{tournamentId}/members")
    public ResponseEntity<List<Member>> getMembersInTournament(@PathVariable Long tournamentId) {
        Tournament tournament = tournamentService.getTournamentWithMembers(tournamentId);
        return ResponseEntity.ok(tournament.getMembers().stream().toList());
    }
}
