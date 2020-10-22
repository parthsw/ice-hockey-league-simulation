package com.statemachine.states;

import com.statemachine.StateMachine;

public class DummyState extends State {
    private int seasonNumber;

    public DummyState(StateMachine stateMachine, int seasonNumber) {
        super(stateMachine);
        this.seasonNumber = seasonNumber;
    }

    @Override
    public State onRun() {
        this.printSeasonSimulation(this.seasonNumber);
        return null;
    }

    private void printSeasonSimulation(int seasonNumber) {
        StateUtils.printMessage("Simulating the season number: " + seasonNumber);
    }
}
