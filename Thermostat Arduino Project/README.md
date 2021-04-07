# Arduino - Thermostat with cooling system

######  Using a temperature sensor to monitor the room temperature and a DC motor in order to cool the room.

## Circuit scheme

![Arduino UNO Project Scheme](https://cdn.docsie.io/boo_u0t9o4OrlrqJykRnW/dd323eb5-6a01-b238-bf5f-d52f6d0437b8sceheme.png "Arduino UNO Project Scheme")

Note: The project only uses one DC motor ( couldn't remove the second one from the scheme) and also the LCD is on-circuit since it is a learning shield

## Requirements

Hardware

* Arduino Uno/Mega

* DC Motor with H Bridge

* USB A-B

* LM35/55 temperature sensor

* Buttons

* Wheel

* Jumpers

* 9V 1A power source

* LCD Shield

Software

* Arduino IDE

* Arduino Libraries (for the LCD) 

## Description

This project simulates a real-life thermostat and also a cooling system for the room. You can press the two buttons to see the needed temperatures in order for the cooling system to start. When the temperature in the room is too hot, the cooling system starts and the motor spins a wheel in order for the temperature to cool down. 

## User Manual

After uploading the project into the Arduino Uno, unplug the USB and connect the power source so that the motor has enough power to start. You can check the temperatures required for the systems to start(cooling).

## Explanations

The project is pretty self-explanatory, the buttons are connected to the Arduino pins, as well as the H-bridge and the motor pins. The sensor is connected to the analog pin in order to gather the information from outside and it is also using an average from the data in order to show accurate values. In order for the cooling system to start, we check if the temperature gathered from the sensor is larger or equal than the set cooling temperature. 

