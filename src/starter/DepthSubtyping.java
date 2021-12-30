package starter;

import java.util.ArrayList;
import java.util.List;

class Point {}

class ColorPoint extends Point {}

public class DepthSubtyping {

    public static void set(Point[] ps) {
        ps[0] = new Point();
    }

    public static void setList(List<Point> pointList) {
        pointList.add(new Point());
    }

    public static void main(String[] args) {
        ColorPoint[] cps = new ColorPoint[10];
        set(cps);
        List<ColorPoint> colorPointList = new ArrayList<>();
        // will not type check
        //setList(colorPointList);
    }
}
