package keyin.golf.Tournament;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import keyin.golf.Member.Member;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tournaments")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "entry_fee")
    private Double entryFee;

    @Column(name = "cash_prize")
    private Double cashPrize;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "tournament_members",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    @JsonManagedReference
    private Set<Member> members = new HashSet<>();

    public Tournament() {}

    public Tournament(LocalDate startDate, LocalDate endDate, String location, Double entryFee, Double cashPrize) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.entryFee = entryFee;
        this.cashPrize = cashPrize;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(Double entryFee) {
        this.entryFee = entryFee;
    }

    public Double getCashPrize() {
        return cashPrize;
    }

    public void setCashPrize(Double cashPrize) {
        this.cashPrize = cashPrize;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public void addMember(Member member) {
        this.members.add(member);
        member.getTournaments().add(this);
    }

    public void removeMember(Member member) {
        this.members.remove(member);
        member.getTournaments().remove(this);
    }
}
