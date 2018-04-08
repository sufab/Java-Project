package game.arenas;

import game.racers.Airplane;
import game.racers.Helicopter;
import utilities.Point;

import java.util.ArrayList;

/**
 * Air arena for races with airplanes and helicopters
 * @see Airplane
 * @see Helicopter
 * @author sufabitton
 * ID 204512818
 * @version 0.1 Mar 29 2018
 **/
public class AerialArena {

    final private double FRICTION = 0.4;
    final private int MAX_RACERS = 6;

    private ArrayList<Airplane> airplanes;
    private ArrayList<Helicopter> helicopters;
    private ArrayList<Object> finished;
    private Point start;
    private Point finish;

    public AerialArena(AerialArena a) {
        this.setStart(a.getStart());
        this.setFinish(a.getFinish());
        for (Airplane airplane :
                a.getAirplanes()) {
            this.addAirplane(airplane);
        }
        for (Helicopter helicopter :
                a.getHelicopters()) {
            this.addHelicopter(helicopter);
        }
        for (Object racer :
                a.getFinished()) {
            this.finished.add(racer);

        }
    }

    /**
     * constructor
     * @param finish
     * @param start
     */
    public AerialArena(Point start, Point finish) {
        this.setStart(start);
        this.setFinish(finish);
        this.airplanes = new ArrayList<>();
        this.helicopters = new ArrayList<>();
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
    public ArrayList<Airplane> getAirplanes() {
        return this.airplanes;
    }

    /**
     * get list of helicopters
     * @return list of helicopters*/
    public ArrayList<Helicopter> getHelicopters() {
        return this.helicopters;
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
    public boolean addAirplane(Airplane newRacer) {
        boolean res = false;

        if(canAddRacer()) {

            this.airplanes.add(newRacer);
            res = true;
        }

        return res;
    }


    /**
     * add helicopter to the race
     * @param newRacer
     * @return if added or not
     * */
    public boolean addHelicopter(Helicopter newRacer) {
        boolean res = false;

        if(canAddRacer()) {

            this.helicopters.add(newRacer);
            res = true;
        }

        return res;
    }

    /**
     * racer of an airplane type cross the finish line
     * @param racer
     * @return his place
     * */
    public int crossFinishLine(Airplane racer) {
        this.finished.add(racer);
        return (this.finished.size() + 1);
    }

    /**
     * racer of an helicopter type cross the finish line
     * @param racer
     * @return his place
     * */
    public int crossFinishLine(Helicopter racer) {
        this.finished.add(racer);
        return (this.finished.size() + 1);
    }

    /**
     * init race
     * */
    public void initRace() {
        for(int i = 0; i < this.getAirplanes().size(); i++) {
            this.airplanes.get(i).initRace(this, this.getStart(), this.getFinish());
        }
        for(int i = 0; i < this.getHelicopters().size(); i++) {
            this.helicopters.get(i).initRace(this, this.getStart(), this.getFinish());
        }
    }

    /**
     * play turn
     * */
    public void playTurn(){
        for (Airplane airplane :
                new ArrayList<Airplane>(this.getAirplanes())) {
            airplane.move(this.getFRICTION());
            if (airplane.getCurrentLocation().getX() >= this.getFinish().getX()) {
                this.crossFinishLine(airplane);
                this.airplanes.remove(airplane);
            }
        }
        for (Helicopter helicopter :
                new ArrayList<Helicopter>(this.getHelicopters())) {
            helicopter.move(this.getFRICTION());
            if (helicopter.getCurrentLocation().getX() >= this.getFinish().getX()) {
                this.crossFinishLine(helicopter);
                this.helicopters.remove(helicopter);
            }
        }
    }

    /**
     * tells if there is steel active racers
     * @return true if there is stell racers and false if not
     * */
    public boolean hasActiveRacers() {
        boolean res = false;
        if (!this.getAirplanes().isEmpty() || !this.getHelicopters().isEmpty()) {
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
        int amount = this.getAirplanes().size() + this.getHelicopters().size();

        if (amount < this.getMAX_RACERS())
            res = true;

        return res;
    }

}
