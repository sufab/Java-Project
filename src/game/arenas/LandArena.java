package game.arenas;

import game.racers.Car;
import game.racers.Horse;
import utilities.Point;

import java.util.ArrayList;

/**
 * Land arena for races with cars and horses
 * @see Car
 * @see Horse
 * @author sufabitton
 * ID 204512818
 * @version 0.1 Mar 29 2018
 **/
public class LandArena {

    final private double FRICTION = 0.7;
    final private int MAX_RACERS = 5;

    private ArrayList<Car> cars;
    private ArrayList<Horse> horses;
    private ArrayList<Object> finished;
    private Point start;
    private Point finish;


    public LandArena(LandArena l) {
        this.setStart(l.getStart());
        this.setFinish(l.getFinish());
        for (Car car :
                l.getCars()) {
            this.addCar(car);
        }
        for (Horse horse :
                l.getHorses()) {
            this.addHorse(horse);
        }
        for (Object racer :
                l.getFinished()) {
            this.finished.add(racer);

        }
    }
    /**
     * constructor
     * @param finish
     * @param start
     */
    public LandArena(Point start, Point finish) {
        this.setStart(start);
        this.setFinish(finish);
        this.cars = new ArrayList<>();
        this.horses = new ArrayList<>();
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
    public ArrayList<Car> getCars() {
        return this.cars;
    }

    /**
     * get list of helicopters
     * @return list of helicopters*/
    public ArrayList<Horse> getHorses() {
        return this.horses;
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
    public boolean addCar(Car newRacer) {
        boolean res = false;

        if(canAddRacer()) {

            this.cars.add(newRacer);
            res = true;
        }

        return res;
    }


    /**
     * add helicopter to the race
     * @param newRacer
     * @return if added or not
     * */
    public boolean addHorse(Horse newRacer) {
        boolean res = false;

        if(canAddRacer()) {

            this.horses.add(newRacer);
            res = true;
        }

        return res;
    }

    /**
     * racer of an airplane type cross the finish line
     * @param racer
     * @return his place
     * */
    public int crossFinishLine(Car racer) {
        this.finished.add(racer);
        return (this.finished.size() + 1);
    }

    /**
     * racer of an helicopter type cross the finish line
     * @param racer
     * @return his place
     * */
    public int crossFinishLine(Horse racer) {
        this.finished.add(racer);
        return (this.finished.size() + 1);
    }

    /**
     * init race
     * */
    public void initRace() {
        for(int i = 0; i < this.getCars().size(); i++) {
            this.cars.get(i).initRace(this, this.getStart(), this.getFinish());
        }
        for(int i = 0; i < this.getHorses().size(); i++) {
            this.horses.get(i).initRace(this, this.getStart(), this.getFinish());
        }
    }

    /**
     * play turn
     * */
    public void playTurn(){

        for (Car car :
                new ArrayList<Car>(this.getCars())) {
            car.move(this.getFRICTION());
            if (car.getCurrentLocation().getX() >= this.getFinish().getX()) {
                this.crossFinishLine(car);
                this.cars.remove(car);
            }
        }
        for (Horse horse :
                new ArrayList<Horse>(this.getHorses())) {
            horse.move(this.getFRICTION());
            if (horse.getCurrentLocation().getX() >= this.getFinish().getX()) {
                this.crossFinishLine(horse);
                this.horses.remove(horse);
            }
        }
    }

    /**
     * tells if there is steel active racers
     * @return true if there is stell racers and false if not
     * */
    public boolean hasActiveRacers() {
        boolean res = false;
        if (!this.getCars().isEmpty() || !this.getHorses().isEmpty()) {
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
        int amount = this.getCars().size() + this.getHorses().size();

        if (amount < this.getMAX_RACERS())
            res = true;

        return res;
    }
}
