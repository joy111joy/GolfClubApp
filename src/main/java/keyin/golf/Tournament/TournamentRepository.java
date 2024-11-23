package keyin.golf.Tournament;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    // Find tournaments by location (case-insensitive)
    List<Tournament> findByLocationIgnoreCase(String location);

    // Find tournaments by start date
    List<Tournament> findByStartDate(LocalDate startDate);

    // Find tournaments with start date between two dates
    List<Tournament> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

    // Find tournaments and fetch participating members
    @Query("SELECT t FROM Tournament t JOIN FETCH t.members WHERE t.id = :tournamentId")
    Tournament findTournamentWithMembers(Long tournamentId);
}
