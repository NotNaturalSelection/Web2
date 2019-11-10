package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PointBean implements Serializable {
    private List<Point> array = new ArrayList<>();

    public PointBean() {
    }

    public List<Point> getArray() {
        return array;
    }

    public void setArray(List<Point> array) {
        this.array = array;
    }

    public void addValue(double x, double y, double r, boolean hit) {
        array.add(new Point(x, y, r, hit));
    }
}
