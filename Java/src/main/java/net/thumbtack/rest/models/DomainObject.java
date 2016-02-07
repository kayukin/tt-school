package net.thumbtack.rest.models;


public class DomainObject {
    private int id;

    public DomainObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DomainObject that = (DomainObject) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
