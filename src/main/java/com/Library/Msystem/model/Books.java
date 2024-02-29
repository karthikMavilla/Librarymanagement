package com.Library.Msystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookName;
    private String author;
    private String year;

    private boolean purchased;
    private boolean cancelled;

    private LocalDateTime purchasedTime;
    private LocalDateTime canceledTime;

    private String location;

    @ManyToOne
    private User user;
}
