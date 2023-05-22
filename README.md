# ElevatorSystem
Bakoish's ElevatorSystem in Java

# How to build and run?
Use gradle wrapper for Java 17 Gradle application
 - go to project's folder (cd ./)
 - ./gradlew build 
 - ./gradlew run

### For windows use ./gradlew.bat 

# Elevator system description
Command system to manage people and elevators. After run Main() you need to set number of elevators and max floor (from 0 to 100 max).
Then with command line you can add person - floor where he/she enters and destination floor.
System automatically reserves a free elevator to n waiting persons to max out usage of all elevators.
If all of them are in usage, it delivers someone the system will try to take additional people going the same direction.

The elevator has 3 states:
- FREE (waiting for free person)
- RESERVED (going directly for reserved person) 
- DELIVERING (going with person and it will try take with them more people if there are no free elevators)
