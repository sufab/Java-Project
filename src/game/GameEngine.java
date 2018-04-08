package game;

import game.arenas.AerialArena;
import game.arenas.ArenaType;
import game.arenas.NavalArena;
import game.arenas.LandArena;
import game.racers.*;

/**
 * Game engine
 * @see AerialArena
 * @see LandArena
 * @see NavalArena
 * @author sufabitton
 * ID 204512818
 * @version 0.1 Mar 29 2018
 **/

public class GameEngine {

    private AerialArena airArena;
    private LandArena landArena;
    private NavalArena navalArena;
    private ArenaType activearena;

    private static GameEngine instance = null;

    private GameEngine() { }

    /**
     * Singelton
     * */
    public static GameEngine getInstance() {
        if(instance == null) {
            instance = new GameEngine();
        }
        return instance;
    }

    public String getArenaType() {

        if (this.activearena == ArenaType.AERIALARENA) {
            return "Aerial arena";
        }
        if (this.activearena == ArenaType.LANDARENA) {
            return "Land arena";
        }
        if (this.activearena == ArenaType.NEVALARENA) {
            return "Naval arena";
        }

        return "mistake";
    }

    /**
     * Set arena
     * @param arena
     * @return true the function coukd set the arena, otherwise false
     * */
    public boolean setArena(Object arena) {
        boolean res = false;

        //check if the arena is aerial
        if (arena instanceof AerialArena) {
            this.activearena = ArenaType.AERIALARENA;
            this.airArena = (AerialArena)arena;
            res = true;
        }

        //check if the arena is land
        if (arena instanceof LandArena) {
            this.activearena =ArenaType.LANDARENA;
            this.landArena = (LandArena)arena;
            res = true;
        }

        //check if the arena is naval
        if (arena instanceof NavalArena) {
            this.activearena = ArenaType.NEVALARENA;
            this.navalArena = (NavalArena)arena;
            res = true;
        }

        return res;
    }

    /**
     * add racer to the race
     * @param newRacer
     * @return true if the function could add the racer and false if it didn't
     * */
    public boolean addRacer(Object newRacer) {
        boolean res =  false;

        //add racer to aerial arena
        if (this.activearena == ArenaType.AERIALARENA) {
            if (newRacer instanceof Airplane) {
                this.airArena.addAirplane((Airplane) newRacer);
                res = true;
            }
            if (newRacer instanceof Helicopter) {
                this.airArena.addHelicopter((Helicopter)newRacer);
                res = true;
            }
        }

        //add racer to land arena
        if (this.activearena == ArenaType.LANDARENA) {
            if (newRacer instanceof Car) {
                this.landArena.addCar((Car) newRacer);
                res = true;
            }
            if (newRacer instanceof Horse) {
                this.landArena.addHorse((Horse)newRacer);
                res = true;
            }
        }

        //add racer to naval arena
        if (this.activearena == ArenaType.NEVALARENA) {
            if (newRacer instanceof RowBoat) {
                this.navalArena.addRowBoat((RowBoat)newRacer);
                res = true;
            }
            if (newRacer instanceof SpeedBoat) {
                this.navalArena.addSpeedBoat((SpeedBoat) newRacer);
                res = true;
            }
        }
        return res;
    }

    /**
     * init race
     * */
    public void initRace() {

        if (this.activearena == ArenaType.AERIALARENA)
            this.airArena.initRace();

        if (this.activearena == ArenaType.LANDARENA)
            this.landArena.initRace();

        if (this.activearena == ArenaType.NEVALARENA)
            this.navalArena.initRace();
    }

    /**
     * start race
     * */
    public void startRace() {

        //start race in aerial arena
        if (this.activearena == ArenaType.AERIALARENA) {
            while (this.airArena.hasActiveRacers()) {
                this.airArena.playTurn();
            }
            //finish race in aerial arena
            System.out.println("Aerial Race ended!");
            for (Object racer:
                    this.airArena.getFinished()) {

                if (racer instanceof Airplane)
                    System.out.println("#" + (this.airArena.getFinished().indexOf(racer)+1) + " Airplane " + ((Airplane) racer).getName() + " (" + ((Airplane) racer).getMaxSpeed() + "," + ((Airplane) racer).getAcceleration() + ")" );

                if (racer instanceof Helicopter)
                    System.out.println("#" + (this.airArena.getFinished().indexOf(racer)+1) + " Helicopter " + ((Helicopter) racer).getName() + " (" + ((Helicopter) racer).getMaxSpeed() + "," + ((Helicopter) racer).getAcceleration() + ")" );            }
        }

        //start race in land arena
        if (this.activearena == ArenaType.LANDARENA) {
            while (this.landArena.hasActiveRacers()) {
                this.landArena.playTurn();
            }
            //finish race in land arena
            System.out.println("Land Race ended!");
            for (Object racer:
                    this.landArena.getFinished()) {

                if (racer instanceof Car)
                    System.out.println("#" + (this.landArena.getFinished().indexOf(racer)+1) + " Car " + ((Car) racer).getName() + " (" + ((Car) racer).getMaxSpeed() + "," + ((Car) racer).getAcceleration() + ")" );

                if (racer instanceof Horse)
                    System.out.println("#" + (this.landArena.getFinished().indexOf(racer)+1) + " Horse " + ((Horse) racer).getName() + " (" + ((Horse) racer).getMaxSpeed() + "," + ((Horse) racer).getAcceleration() + ")" );
            }
        }

        //start race in naval arena
        if (this.activearena == ArenaType.NEVALARENA) {
            while (this.navalArena.hasActiveRacers()) {
                this.navalArena.playTurn();
            }
            //finish race in naval arena
            System.out.println("Naval Race ended!");
            for (Object racer:
                    this.navalArena.getFinished()) {

                if (racer instanceof RowBoat)
                    System.out.println("#" + (this.navalArena.getFinished().indexOf(racer)+1) + " RowBoat " + ((RowBoat) racer).getName() + " (" + ((RowBoat) racer).getMaxSpeed() + "," + ((RowBoat) racer).getAcceleration() + ")" );

                if (racer instanceof SpeedBoat)
                    System.out.println("#" + (this.navalArena.getFinished().indexOf(racer)+1) + " SpeegBoat " + ((SpeedBoat) racer).getName() + " (" + ((SpeedBoat) racer).getMaxSpeed() + "," + ((SpeedBoat) racer).getAcceleration() + ")" );
            }
        }
    }


}
