package com.bakoish;

public class Elevator {
    private int floor;
    private int destinationFloor;
    Person waitingPerson;
    ElevatorState elevatorState;

    public Elevator() {
        floor = 0;
        destinationFloor = 0;
        elevatorState = ElevatorState.FREE;
    }

    public void setPickupPerson(Person person) {

        if(elevatorState == ElevatorState.FREE) {
            destinationFloor = person.destinationFloor();
            waitingPerson = person;
            elevatorState = ElevatorState.RESERVED;
            return;
        }
        //new person enter while delivering - set new destination and person
        destinationFloor = person.destinationFloor();
        waitingPerson = person;
    }

    public void move() {

        if(elevatorState == ElevatorState.FREE) {
            return;
        }

        if(elevatorState == ElevatorState.RESERVED) {
            if(isGoingUp()) floor++;
            else if(isGoingDown()) floor--;

            if(floor == waitingPerson.startFloor()) {
                destinationFloor = waitingPerson.destinationFloor();
                elevatorState = ElevatorState.DELIVERING;
            }
        }
        else if(elevatorState == ElevatorState.DELIVERING) {
            if(isGoingUp()) floor++;
            else if(isGoingDown()) floor--;

            if(floor == waitingPerson.destinationFloor()) {
                elevatorState = ElevatorState.FREE;
            }
        }
    }
    public boolean isGoingDown() {
        return floor > destinationFloor;
    }

    public boolean isGoingUp() {
        return floor < destinationFloor;
    }

    public int getFloor() {
        return floor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public ElevatorState getElevatorState() {
        return elevatorState;
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "floor=" + floor +
                ", destinationFloor=" + destinationFloor +
                ", elevatorState=" + elevatorState +
                '}';
    }
}
