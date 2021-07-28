package com.ludmylla.cineapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;

import javax.persistence.*;
import java.time.Instant;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Story {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    private String description;
    private String audio;
    private Instant moment;
}
