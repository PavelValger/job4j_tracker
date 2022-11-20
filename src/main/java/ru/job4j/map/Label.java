package ru.job4j.map;

public record Label(String name, double score) implements Comparable<Label> {

    @Override
    public double score() {
        return score;
    }

    @Override
    public int compareTo(Label o) {
        return Double.compare(this.score, o.score);
    }

    @Override
    public String toString() {
        return "Label{"
                + "name='" + name + '\''
                + ", score=" + score
                + '}';
    }
}
