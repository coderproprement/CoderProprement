package coderproprement.lpiem.com.projetcoderproprement.Model;

import java.util.Objects;

public class ComicCreator {
    private String name;
    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComicCreator that = (ComicCreator) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, role);
    }

    @Override
    public String toString() {
        return "ComicCreator{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public ComicCreator(String name, String role) {
        this.name = name;
        this.role = role;
    }
}
