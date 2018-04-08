package utilities;

/**
 *Point
 * @author sufabitton
 * ID 204512818
 * @version 0.1 Mar 29 2018
 **/
public class Point {

    private double x;
    private double y;

    /**
     * Consrtructor
     **/
    public Point(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     * Consrtuctor
     * @param x
     * @param y
     **/
    public Point(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    /**
     * get x
     * @return x
     **/
    public double getX() { return this.x; }

    /**
     * get y
     * @return y
     **/
    public double getY() { return this.y; }

    /**
     * set x
     * @param x
     * @return true if set the x, false if cant sert the x
     **/
    public boolean setX(double x){
        if (x >= 0 && x <= 10000000) {
            this.x = x;
            return true;
        }
        return false;
    }

    /**
     * set y
     * @param y
     * @return true if set the y, false if cant sert the y
     **/
    public boolean setY(double y) {
        if (y >= 0 && y <= 800) {
            this.y = y;
            return true;
        }
        return false;
    }
}
