package ru.job4j.oop;

public class Triangle {
    private final Point ap;
    private final Point bp;
    private final Point cp;

    public Triangle(Point ap, Point bp, Point cp) {
        this.ap = ap;
        this.bp = bp;
        this.cp = cp;
    }

    public double semiPerimeter(double a, double b, double c) {
        return (a + b + c) / 2;
    }

    public boolean exist(double ab, double ac, double bc) {
        return (ab + ac) > bc && (ac + bc) > ab && (ab + bc) > ac;
    }

    public double area() {
        double rsl = -1;
        double ab = ap.distance(bp);
        double ac = ap.distance(cp);
        double bc = bp.distance(cp);
        if (this.exist(ab, ac, bc)) {
            double p = semiPerimeter(ab, ac, bc);
            rsl = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
        }
        return rsl;
    }
}