package ru.itis.javalab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_id")
    Long pageId;
    @Column(name = "img")
    String imgSource;
    @Column(name = "name")
    String name;
    @ManyToOne
    @JoinColumn(name = "chapter_id")
    Chapter chapter;

}
