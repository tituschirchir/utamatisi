package com.utamatisi.app.models.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tree")
@NamedQueries({
        @NamedQuery(name = "Tree.findAll", query = Tree.GENERIC_SELECT)
})
public class Tree{
    public static final String GENERIC_SELECT = "select t from Tree t";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "botanical_name", nullable = false)
    private String botanicalName;

    @Column(name = "description")
    private String description;

    public Tree() {
    }

    public Tree(String name, String botanicalName, String description) {

        this.name = name;
        this.botanicalName = botanicalName;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tree)) {
            return false;
        }

        final Tree that = (Tree) o;

        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.description, that.description) &&
                Objects.equals(this.botanicalName, that.botanicalName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, botanicalName, description);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBotanicalName() {
        return botanicalName;
    }

    public void setBotanicalName(String botanicalName) {
        this.botanicalName = botanicalName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
