package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class PointBean implements Serializable {
    private List<Point> array = new ArrayList<>();
    public PointBean() {}

    public List<Point> getArray() {
        return array;
    }

    public void setArray(List<Point> array) {
        this.array = array;
    }

    public void addValue(double x, double y, double r, boolean is){
//        Point[] points = new Point[getArray().size()+1];
//        points = getArray().toArray(points);
//        points[points.length-1] = new Point(x,y,r,is);
//        Collections.addAll(array, points);
        array.add(new Point(x,y,r,is));
    }
}
