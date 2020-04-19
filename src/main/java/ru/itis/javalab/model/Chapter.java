package ru.itis.javalab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chapter_id")
    Long chapterId;
    @OneToMany(mappedBy = "chapter")
    List<Page> pages;
    @Column(name = "name")
    String name;
    @ManyToOne
    @JoinColumn(name = "manga_id")
    Manga manga;
}
