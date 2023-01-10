package pl.envelo.meetek.model.event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class RecurringEventSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventSetId;
    @OneToMany
    private Set<Event> events;
    private int eventFrequency;
    private int recursiveCount;

}
