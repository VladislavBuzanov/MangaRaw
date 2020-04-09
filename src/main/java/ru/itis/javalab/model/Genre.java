package ru.itis.javalab.model;

import javax.persistence.Entity;
import java.io.Serializable;

public enum Genre implements Serializable {
    Action, Adventure, Comedy, Drama, SliceOfLife, Fantasy, Magic,
    Supernatural, Horror, Mystery, Psychological, Romance, SciFi;
}
