#include <Wire.h>

#define V1_IN_OPEN 1
#define V1_IN_CLOSE 2
#define V1_OUT_OPEN 3
#define V1_OUT_CLOSE 4
#define V2_IN_OPEN 5
#define V2_IN_CLOSE 6
#define V2_OUT_OPEN 7
#define V2_OUT_CLOSE 8

#define VALVES_CONTROLLER 8

byte i = 1;

void setup() {
  Wire.begin();
}

void loop() {
  //TODO getting floating switchers state
  if(i > 8) {
    i = 1;
  }
  
  sendCommandToValvesController(i);

  i++;  
  delay(1000);
}

void sendCommandToValvesController(byte command){
  Wire.beginTransmission(VALVES_CONTROLLER);
  Wire.write(command);
  Wire.endTransmission();
}
