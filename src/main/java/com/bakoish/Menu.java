package com.bakoish;

import java.util.Scanner;

import static java.lang.System.exit;

public class Menu {
    public static void elevatorSystem() {

        int option = 0;
        ElevatorSystem elevatorSystem = createElevatorSystem();

        while (true)
        {
            option = showMenu();

            switch (option) {
                case 1 -> {
                    System.out.println("Enter new person - entering floor and destination floor:");
                    Scanner in = new Scanner(System.in);
                    System.out.println("Enter entering floor: (from 1 to " + elevatorSystem.getMaxFloor() + ")");
                    int floor = in.nextInt();
                    if (floor < 1 || floor > elevatorSystem.getMaxFloor()) {
                        System.out.println("Wrong number!");
                        break;
                    }
                    System.out.println("Enter destination floor: (from 1 to " + elevatorSystem.getMaxFloor() + ")");
                    int destination = in.nextInt();
                    if (destination < 1 || destination > elevatorSystem.getMaxFloor()) {
                        System.out.println("Wrong number!");
                        break;
                    }
                    if (floor == destination) {
                        System.out.println("The same number!");
                        break;
                    }
                    elevatorSystem.addPerson(floor, destination);
                    System.out.println("Person added! floor - " + floor + " destination - " + destination);
                }
                case 2 -> {
                    System.out.println("Executing step!");
                    elevatorSystem.step();
                }
                case 3 -> {
                    System.out.println("Elevator status:");
                    elevatorSystem.status();
                }
                case 4 -> {
                    System.out.println("People status:");
                    elevatorSystem.peopleStatus();
                }
                case 5 -> {
                    System.out.println("See you!");
                    exit(0);
                }
                default -> System.out.println("Sorry, please enter valid option");
            }
        }

    }

    private static int showMenu() {
        System.out.println("--- Elevator system ---");
        System.out.println("Please input number:");
        System.out.println("1 - Add new person");
        System.out.println("2 - Execute step");
        System.out.println("3 - Show elevator status");
        System.out.println("4 - Show people status");
        System.out.println("5 - Exit");

        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    private static ElevatorSystem createElevatorSystem() {
        System.out.println("Welcome to elevator system!");
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number of elevators: (from 1 to 16)");
        int nElevators = in.nextInt();
        if (nElevators < 1 || nElevators > 16) {
            System.out.println("Wrong number!");
            exit(1);
        }

        System.out.println("Enter number of floors: (from 1 to 100)");
        int nFloors = in.nextInt();
        if (nFloors < 1 || nFloors > 100) {
            System.out.println("Wrong number!");
            exit(1);
        }

        return new ElevatorSystem(nElevators, nFloors);
    }
}
