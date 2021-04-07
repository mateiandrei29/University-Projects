#include <LiquidCrystal.h>


float resolutionADC = 0.0049;
float resolutionSensor = 0.01;
volatile float tempCool = 26.0; // temperatura implicita pentru pornire sistem racire (cand sa se raceasca in camera)
LiquidCrystal lcd(7, 6, 5, 4, 3, 2);

void setup() {
  Serial.begin(9600);
  pinMode(5, OUTPUT); // pin 1 motor
  pinMode(6, OUTPUT); // pin 2 motor
  pinMode(20, INPUT_PULLUP); // + pe termostat
  pinMode(21, INPUT_PULLUP); // - pe termostat

  attachInterrupt(digitalPinToInterrupt(20), functieUnu, RISING);
  attachInterrupt(digitalPinToInterrupt(21), functieDoi, RISING);
  lcd.begin(16, 2);
}

void loop() {
  float temp = readTemp(10, 0);

  if (temp - (int)temp <= 0.3) {
    temp = floor(temp);
  }
  else if (temp - (int)temp >= 0.7) {
    temp = ceil(temp);
  }
  else {
    temp = floor(temp) + 0.5;
  }
  lcd.setCursor(5, 0);
  lcd.print("Temp: ");
  lcd.setCursor(5, 1);
  lcd.print(temp);
  Serial.print(temp);
  Serial.println();

  if (temp >= tempCool) {
    StartMotor (5, 6, 85);;
  }
  else{
    StartMotor(5,6,0);
  }
  delay(1000);
  lcd.clear();
}

void functieUnu()
{

  lcd.setCursor(5, 0);
  lcd.print("Temp zi: ");
  lcd.setCursor(5, 1);
  lcd.print(tempHeat);
  
}

void functieDoi()
{
  
  lcd.setCursor(5, 0);
  lcd.print("Temp cool: ");
  lcd.setCursor(5, 1);
  lcd.print(tempCool);
}

float readTemp(int count, int pin) {
  float sumTemp = 0;
  for (int i = 0; i < count; i++) {
    int reading = analogRead(pin);
    float voltage = reading * resolutionADC;
    float tempCelsius = (voltage - 0.5) / resolutionSensor;
    sumTemp += tempCelsius;
  }
  return sumTemp / (float)count;
}



void StartMotor (int m1, int m2, int speed)
{
  digitalWrite(5, 0);
  analogWrite(6, speed);
}
