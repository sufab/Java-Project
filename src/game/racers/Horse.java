package game.racers;

import game.arenas.LandArena;
import utilities.Point;

/**
 *Airplane
 * @see Car
 * @see LandArena
 * @author sufabitton
 * ID 204512818
 * @version 0.1 Mar 29 2018
 **/
public class Horse {

    private double maxSpeed = 80;
    private double acceleration = 7;

    private String name;
    private Point currentLocation;
    private Point finish;
    private LandArena arena;
    private double currentSpeed;

    /**
     * default constructor
     * */
    public Horse(Horse a) {
        this.setName(a.getName());
        this.setCurrentLocation(a.getCurrentLocation());
        this.setFinish(a.getFinish());
        this.setArena(a.getArena());
        this.setCurrentSpeed(a.getCurrentSpeed());
    }

    /**
     * Constructor
     * @param name
     * @param maxSpeed
     * @param acceleration
     * */
    public Horse(String name, double maxSpeed, double acceleration) {
        this.setName(name);
        this.setMaxSpeed(maxSpeed);
        this.setAcceleration(acceleration);
    }

    /**
     * get max speed
     * @return max speed
     * */
    public double getMaxSpeed() {

        return this.maxSpeed;
    }

    /**
     * get acceleration
     * @return acceleration
     * */
    public double getAcceleration() {
        return this.acceleration;
    }

    /**
     * get name
     * @return name
     * */
    public String getName() {
        return this.name;
    }

    /**
     * get current location
     * @return current location
     * */
    public Point getCurrentLocation() {
        return this.currentLocation;
    }

    /**
     * get finish
     * @return finish
     * */
    public Point getFinish() {
        return this.finish;
    }

    /**
     * get arena
     * @return arena
     * */
    public LandArena getArena() {
        return this.arena;
    }

    /**
     * get current speed
     * @return current speed
     * */
    public double getCurrentSpeed() {
        return this.currentSpeed;
    }

    /**
     * set max speed
     * @param maxSpeed
     * */
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * set acceleration
     * @param acceleration
     * */
    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    /**
     * set name
     * @param name
     * */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * set current lcation
     * @param currentLocation
     * */
    public void setCurrentLocation(Point currentLocation) {
        this.currentLocation = new Point(currentLocation);
    }

    /**
     * set finish
     * @param finish
     * */
    public void setFinish(Point finish) {
        this.finish = new Point(finish);
    }

    /**
     * set arena
     * @param arena
     * */
    public void setArena(LandArena arena) {
        this.arena = new LandArena(arena.getStart(), arena.getFinish());
    }

    /**
     * set current speed
     * @param currentSpeed
     * */
    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    /**
     * init race
     * @param arena
     * @param start
     * @param finish
     **/
    public void initRace(LandArena arena, Point start, Point finish){
        this.setArena(arena);
        this.setFinish(finish);
        this.setCurrentLocation(start);
    }

    /**
     * move
     * @param friction
     * */
    public Point move(double friction) {

        //check if we can speed up
        if (this.getCurrentSpeed() < this.getMaxSpeed()) {

            double newSpeed = this.getCurrentSpeed() + (this.getAcceleration() * friction);
            this.setCurrentSpeed(newSpeed);
        }
        //move the racer
        double newLocation = this.getCurrentLocation().getX() + this.getCurrentSpeed();
        this.getCurrentLocation().setX(newLocation);

        return this.getCurrentLocation();

    }

}
