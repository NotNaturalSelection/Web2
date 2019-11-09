package beans;

import static java.lang.Math.pow;

public class Point {
    private double x;
    private double y;
    private double r;
    private boolean hit;

    public Point(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = isHit(x,y,r);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", hit=" + hit +
                '}';
    }

    public String toJson() {
        return
                "{" + "\"x\":\"" + x + "\"," +
                        "\"y\":\"" + y + "\"," +
                        "\"r\":\"" + r + "\"," +
                        "\"result\":\"" + hit + "\"" +
                        "}";
    }

    public static boolean isHit(double numberX, double numberY, double numberR) {
        boolean result = false;
        if (numberX <= 0 && numberY >= 0 && numberY - numberX <= numberR) {
            result = true;
        }
        if (numberX <= 0 && numberY <= 0 && pow(numberX, 2d) + pow(numberY, 2d) <= pow(numberR / 2, 2d)) {
            result = true;
        }
        if (numberX >= 0 && numberY >= 0 && numberX <= numberR && numberY <= numberR / 2) {
            result = true;
        }
        return result;
    }
}
