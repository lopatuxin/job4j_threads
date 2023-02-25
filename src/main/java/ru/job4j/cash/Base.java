package ru.job4j.cash;

public class Base {
    private final int id;
    private final int version;
    private String name;

    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Base base = (Base) o;

        if (id != base.id) return false;
        if (version != base.version) return false;
        return name != null ? name.equals(base.name) : base.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + version;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
