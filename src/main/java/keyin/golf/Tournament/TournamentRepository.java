package keyin.golf.Tournament;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    List<Tournament> findByLocationIgnoreCase(String location);

    List<Tournament> findByStartDate(LocalDate startDate);

    List<Tournament> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT t FROM Tournament t JOIN FETCH t.members WHERE t.id = :tournamentId")
    Tournament findTournamentWithMembers(Long tournamentId);
}
