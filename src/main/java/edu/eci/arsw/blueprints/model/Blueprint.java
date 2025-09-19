package edu.eci.arsw.blueprints.model;

import java.util.ArrayList;
import java.util.List;

public class Blueprint {
    private String author;
    private String name;
    private List<Point> points = new ArrayList<>();

    public Blueprint() {}

    public Blueprint(String author, String name, List<Point> points) {
        this.author = author;
        this.name = name;
        if (points != null) this.points = points;
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Point> getPoints() { return points; }
    public void setPoints(List<Point> points) { this.points = points; }
}
