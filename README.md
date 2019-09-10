# parking_lot

## Problem Description
A parking lot that can hold up to 'n' cars at any given point 
in time. Each slot is given a number starting at 1 increasing 
with increasing distance from the entry point in steps of one. 

This is an automated ticketing system that allows parking lot 
without human intervention.

### Features

* **Create Parking Lot:** Setup a lot with n > 0 parking slots   
* **Park vehicle:** Park vehicle(car) at nearest empty parking slot
* **Unpark/leave vehicle:** Unpark or remove vehicle from occupied parking slot  
* ```Query```**Lot Status:** Lists all the vehicle info of occupied parking slots
* ```Query```**Reg# from colour:** List all the reg # of parked vehicles of a given colour 
* ```Query```**Slot# from colour:** List all the parking slot numbers of parked vehicles of 
a given colour
* ```Query```**Slot# from reg#:** returns parked slot # of a parked vehicle with given reg#

### Implementation

* Language: Java
* Build tool: Maven
* ```Interfaces```
    * IParkingLot: interface for the feature methods
* ```Classes```
    * ParkingLot: Implements IParkingLot
    * ParkingSlot:  
        * slotId :: Integer
        * vehicle :: Vehicle
    *  Vehicle: Abstract class extensible for various types of vehicles
    *  Car: Extends Vehicle Class
          * Reg # :: String
          * Color :: Enum
    * Enums: 
        * CarColor :: {Black, Blue, Brown, Green, Red, White< Yellow}
        * Queries  
            * create_parking_lot
            * park
            * leave
            * status
            * registration_numbers_for_cars_with_colour
            * slot_numbers_for_cars_with_colour
            * slot_number_for_registration_number
            * exit
    * Main: Driver class executing required methods based on the input
    * UnitTests: Junit tests for all the features are implemented 
    in test directory

### Build & Execution

Commands required to install required dependencies, compiling the 
source code, executing unit tests and executing the Main(Driver) class 
can be found in  ``.\bin\setup`` script

```shell script
# run code interactively
./bin/setup 

# run code from inputs given in a file
./bin/setup ./bin/parking_lot_file_inputs.txt
```