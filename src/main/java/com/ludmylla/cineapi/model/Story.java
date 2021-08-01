package com.ludmylla.cineapi.model;

import com.ludmylla.cineapi.model.enums.Category;
import com.ludmylla.cineapi.model.enums.StoryStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;
import java.time.Instant;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    private String description;
    private String audio;
    private String image;
    private Instant moment;

    @ManyToOne
    @JoinColumn(name = "PERIOD_ID")
    private Period period;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private StoryStatus storyStatus;
}
