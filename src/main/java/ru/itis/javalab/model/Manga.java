package ru.itis.javalab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.model.enumerated.Genre;
import ru.itis.javalab.model.enumerated.Language;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manga_id")
    private Long mangaId;
    @Column(name = "name")
    private String name;
    @Column(name = "author")
    private String author;
    @OneToMany(mappedBy = "manga")
    private List<Chapter> chapters;
    @ElementCollection(targetClass = Genre.class)
    @Enumerated(EnumType.STRING)
    private List<Genre> genres;
    @Enumerated(EnumType.STRING)
    private Language language;

}
