package game.arenas;

import game.racers.RowBoat;
import game.racers.SpeedBoat;
import utilities.Point;

import java.util.ArrayList;

/**
 * Naval arena for races with row boats and speed boats
 * @see RowBoat
 * @see SpeedBoat
 * @author sufabitton
 * ID 204512818
 * @version 0.1 Mar 29 2018
 **/
public class NavalArena {

    final private double FRICTION = 0.5;
    final private int MAX_RACERS = 8;

    private ArrayList<RowBoat> rowBoats;
    private ArrayList<SpeedBoat> speedBoats;
    private ArrayList<Object> finished;
    private Point start;
    private Point finish;


    public NavalArena(NavalArena n) {
        this.setStart(n.getStart());
        this.setFinish(n.getFinish());
        for (RowBoat rowBoat :
                n.getRowBoat()) {
            this.addRowBoat(rowBoat);
        }
        for (SpeedBoat speedBoat :
                n.getSpeedBoat()) {
            this.addSpeedBoat(speedBoat);
        }
        for (Object racer :
                n.getFinished()) {
            this.finished.add(racer);

        }
    }
    /**
     * constructor
     * @param finish
     * @param start
     */
    public NavalArena(Point start, Point finish) {
        this.setStart(start);
        this.setFinish(finish);
        this.rowBoats = new ArrayList<>();
        this.speedBoats = new ArrayList<>();
        this.finished = new ArrayList<>();
    }

    /**
     * get friction
     * @return friction*/
    public double getFRICTION() {
        return this.FRICTION;
    }

    /**
     *get maximum racers
     * @return max racers*/
    public int getMAX_RACERS() {
        return this.MAX_RACERS;
    }

    /**
     * getlist of airplanes
     * @return list of airplanes*/
    public ArrayList<RowBoat> getRowBoat() {
        return this.rowBoats;
    }

    /**
     * get list of helicopters
     * @return list of helicopters*/
    public ArrayList<SpeedBoat> getSpeedBoat() {
        return this.speedBoats;
    }

    /**
     *get list of finished racers
     * @return finished */
    public ArrayList<Object> getFinished() {
        return this.finished;
    }

    /**
     * get start point
     * @return start point*/
    public Point getStart() {
        return this.start;
    }

    /**
     * get finish point
     * @return finish point*/
    public Point getFinish() {
        return this.finish;
    }


    /**
     * set start point
     * @param start
     * */
    public void setStart(Point start) {
        this.start = new Point(start);
    }

    /**
     * sert finish point
     * @param finish
     * */
    public void setFinish(Point finish) {
        this.finish = new Point(finish);
    }


    /**
     * add airplane to the race
     * @param newRacer
     * @return if added or not
     * */
    public boolean addRowBoat(RowBoat newRacer) {
        boolean res = false;

        if(canAddRacer()) {

            this.rowBoats.add(newRacer);
            res = true;
        }

        return res;
    }


    /**
     * add helicopter to the race
     * @param newRacer
     * @return if added or not
     * */
    public boolean addSpeedBoat(SpeedBoat newRacer) {
        boolean res = false;

        if(canAddRacer()) {

            this.speedBoats.add(newRacer);
            res = true;
        }

        return res;
    }

    /**
     * racer of an airplane type cross the finish line
     * @param racer
     * @return his place
     * */
    public int crossFinishLine(RowBoat racer) {
        this.finished.add(racer);
        return (this.finished.size() + 1);
    }

    /**
     * racer of an helicopter type cross the finish line
     * @param racer
     * @return his place
     * */
    public int crossFinishLine(SpeedBoat racer) {
        this.finished.add(racer);
        return (this.finished.size() + 1);
    }

    /**
     * init race
     * */
    public void initRace() {
        for(int i = 0; i < this.getRowBoat().size(); i++) {
            this.rowBoats.get(i).initRace(this, this.getStart(), this.getFinish());
        }
        for(int i = 0; i < this.getSpeedBoat().size(); i++) {
            this.speedBoats.get(i).initRace(this, this.getStart(), this.getFinish());
        }
    }

    /**
     * play turn
     * */
    public void playTurn(){

        for (RowBoat rowBoat :
               new ArrayList<RowBoat>(this.getRowBoat())) {
            rowBoat.move(this.getFRICTION());
            if (rowBoat.getCurrentLocation().getX() >= this.getFinish().getX()) {
                this.crossFinishLine(rowBoat);
                this.rowBoats.remove(rowBoat);
            }
        }
        for (SpeedBoat speedBoat :
               new ArrayList<SpeedBoat>(this.getSpeedBoat())) {
            speedBoat.move(this.getFRICTION());
            if (speedBoat.getCurrentLocation().getX() >= this.getFinish().getX()) {
                this.crossFinishLine(speedBoat);
                this.speedBoats.remove(speedBoat);
            }
        }
    }

    /**
     * tells if there is steel active racers
     * @return true if there is stell racers and false if not
     * */
    public boolean hasActiveRacers() {
        boolean res = false;
        if (!this.getRowBoat().isEmpty() || !this.getSpeedBoat().isEmpty()) {
            res = true;
        }
        return res;
    }

    /**
     * private method for checking if we can add more racers
     * @return true if we can add more racers and false if not
     * */
    private boolean canAddRacer() {
        boolean res = false;
        int amount = this.getRowBoat().size() + this.getSpeedBoat().size();

        if (amount < this.getMAX_RACERS())
            res = true;

        return res;
    }

}
