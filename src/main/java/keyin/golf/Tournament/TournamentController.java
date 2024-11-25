package keyin.golf.Tournament;

import keyin.golf.Member.Member;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    public ResponseEntity<Tournament> addTournament(@RequestBody Tournament tournament) {
        Tournament savedTournament = tournamentService.addTournament(tournament);
        return ResponseEntity.ok(savedTournament);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable Long id) {
        Optional<Tournament> tournament = tournamentService.getTournamentById(id);
        return tournament.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Tournament>> getAllTournaments() {
        List<Tournament> tournaments = tournamentService.getAllTournaments();
        return ResponseEntity.ok(tournaments);
    }

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

    @PostMapping("/{tournamentId}/members/{memberId}")
    public ResponseEntity<String> addMemberToTournament(
            @PathVariable Long tournamentId,
            @PathVariable Long memberId) {
        System.out.println("Received request to add member to tournament.");
        System.out.println("Tournament ID: " + tournamentId);
        System.out.println("Member ID: " + memberId);

        try {
            System.out.println("Invoking service to add member to tournament...");
            tournamentService.addMemberToTournament(tournamentId, memberId);
            System.out.println("Member successfully added to the tournament.");
            return ResponseEntity.ok("Member added to tournament successfully");
        } catch (Exception e) {
            System.err.println("Error while adding member to tournament: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }





    @GetMapping("/{tournamentId}/members")
    public ResponseEntity<List<Member>> getMembersInTournament(@PathVariable Long tournamentId) {
        Tournament tournament = tournamentService.getTournamentWithMembers(tournamentId);
        return ResponseEntity.ok(tournament.getMembers().stream().toList());
    }
}
