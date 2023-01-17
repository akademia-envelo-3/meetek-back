package pl.envelo.meetek.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.envelo.meetek.repository.HashtagRepo;

@AllArgsConstructor
@Service
public class HashtagService {

    private final HashtagRepo hashtagRepo;
}
