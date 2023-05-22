package com.bakoish;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class ElevatorSystem {
    ArrayList<Elevator> elevators;
    ArrayList<LinkedHashSet<Person>> waitingPeopleOnFloor;
    LinkedHashSet<Person> waitingPeopleLinkedHashSet;
    int maxFloor;

    // number of elevators = nElevators, floor system from 0 to maxFloor
    public ElevatorSystem(int nElevators, int maxFloor) {
        elevators = new ArrayList<>();
        waitingPeopleOnFloor = new ArrayList<>();
        waitingPeopleLinkedHashSet = new LinkedHashSet<>();

        for (int i = 0; i < nElevators; i++) {
            elevators.add(new Elevator());
        }

        for (int i = 0; i < maxFloor; i++) {
            waitingPeopleOnFloor.add(new LinkedHashSet<>());
        }
        this.maxFloor = maxFloor;
    }

    public void addPerson(int floor, int destinationFloor) {
        Person person = new Person(floor, destinationFloor);
        waitingPeopleLinkedHashSet.add(person);
        waitingPeopleOnFloor.get(floor).add(person); //on n floor - unique destinations
    }

    //Display elevators status
    public void status() {
        elevators.forEach(System.out::println);
    }

    public void peopleStatus() {
        System.out.println("There are: " + waitingPeopleLinkedHashSet.size() + " waiting.");
    }

    //Execute step of all elevator system
    public void step() {

        //Elevator system:
        //If Someone waiting AND free elevator - give him one
        //If Someone waiting AND not free elevator - try wait and if elevator on the same floor and same destination UP or DOWN get in and set new longer destination
        //Else wait

        //1.If Someone waiting AND free elevator - give him one
        elevators.forEach(elevator -> {
                    if(elevator.getElevatorState() == ElevatorState.FREE) {
                        Iterator<Person> iterator = waitingPeopleLinkedHashSet.iterator();
                        if (iterator.hasNext()) {
                            Person firstPerson = iterator.next();
                            elevator.setPickupPerson(firstPerson);

                            //delete form que and waiting floor:
                            waitingPeopleOnFloor.get(firstPerson.startFloor()).remove(firstPerson);
                            waitingPeopleLinkedHashSet.remove(firstPerson);
                        }
                    }
                });

        //If Someone waiting AND not free elevator - try wait and if elevator on the same floor and same destination UP or DOWN get in and set new longer destination
        elevators.forEach(elevator -> {
            if(elevator.getElevatorState() == ElevatorState.DELIVERING) {

                Iterator<Person> iterator = waitingPeopleOnFloor.get(elevator.getFloor()).iterator();

                while(iterator.hasNext()) {
                    Person firstPerson = iterator.next();

                    //take parson if going the same direction down
                    if(elevator.isGoingDown() && firstPerson.isGoingDown()) {
                        if(firstPerson.destinationFloor() < elevator.getDestinationFloor()) {
                            //take to elevator
                            elevator.setPickupPerson(firstPerson);
                        }
                        waitingPeopleOnFloor.get(elevator.getFloor()).remove(firstPerson);
                        waitingPeopleLinkedHashSet.remove(firstPerson);
                        continue;
                    }

                    //take parson if going the same direction up
                    if(elevator.isGoingUp() && firstPerson.isGoingUp()) {
                        if(firstPerson.destinationFloor() > elevator.getDestinationFloor()) {
                            //take to elevator
                            elevator.setPickupPerson(firstPerson);
                        }
                        waitingPeopleOnFloor.get(elevator.getFloor()).remove(firstPerson);
                        waitingPeopleLinkedHashSet.remove(firstPerson);
                    }
                }
            }
        });
        //simulate move
        elevators.forEach(Elevator::move);
    }

    public int getMaxFloor() {
        return maxFloor;
    }
}
