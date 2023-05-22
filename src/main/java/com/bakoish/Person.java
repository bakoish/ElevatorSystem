package com.bakoish;

public record Person(int startFloor, int destinationFloor) {

    public boolean isGoingDown() {
        return startFloor > destinationFloor;
    }

    public boolean isGoingUp() {
        return startFloor < destinationFloor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return startFloor == person.startFloor && destinationFloor == person.destinationFloor;
    }

}
