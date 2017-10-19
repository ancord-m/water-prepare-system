#include "Valves_Definitions.h"
#include "AFMotor.h"

#define OPEN BACKWARD 
#define CLOSE FORWARD



AF_DCMotor BarrelOne_IN(V1_IN);
AF_DCMotor BarrelOne_OUT(V1_OUT); 
AF_DCMotor BarrelTwo_IN(V2_IN);
AF_DCMotor BarrelTwo_OUT(V2_OUT);  

void setup() {
  systemInit();
  
  Serial.begin(9600);
  
  pinMode(V2_IN_O, INPUT);
  digitalWrite(V2_IN_O, HIGH);
  pinMode(V2_IN_C, INPUT);
  digitalWrite(V2_IN_C, HIGH);
  
  /*pinMode(V1_OUT_O, INPUT);
  digitalWrite(V1_OUT_O, HIGH);
  pinMode(V1_OUT_C, INPUT);
  digitalWrite(V1_OUT_C, HIGH);*/ 

  valve.setSpeed(200);  
  
}

void loop() {
  delay(1000);
  
  
  
  int opened = digitalRead(V2_IN_O); 
  delay(500);
  int closed = digitalRead(V2_IN_C);
  Serial.print(opened);
  Serial.print('\t');
  Serial.println(closed);


  valve.run(BACKWARD); 
  delay(2000);
  valve.run(FORWARD);
  delay(2000);
  valve.run(RELEASE);
  delay(2000);
  

/*  while(opened){  
    opened = digitalRead(V1_IN_O);
    valve.run(BACKWARD);       
  }

  valve.run(RELEASE);
 // delay(3000);

  while(closed){  
    closed = digitalRead(V1_IN_C);
    valve.run(FORWARD);        
  }

  valve.run(RELEASE);
 // delay(3000);*/
}

void selectValveAction(){
  byte action = 0;
  action = Wire.read();

  switch(action){
    case V1_IN_OPEN:
                  Serial.print("Valve 1 is opened");
                  break;
    case V1_IN_CLOSE:
                  Serial.print("Valve 1 is closed");    
                  break;
    case V1_OUT_OPEN:
                  Serial.print("Valve 2 is opened");
                  break;
    case V1_OUT_CLOSE:
                  Serial.print("Valve 2 is closed");
                  break;
    case V2_IN_OPEN:
                  Serial.print("Valve 3 is opened");
                  break;
    case V2_IN_CLOSE:
                  Serial.print("Valve 3 is closed");
                  break;
    case V2_OUT_OPEN:
                  Serial.print("Valve 4 is opened");
                  break;
    case V2_OUT_CLOSE:
                  Serial.print("Valve 4 is closed");
                  break; 
  }
  Serial.print('\n');
}

void open_valve(AF_DCMotor *valve){
  
}

void close_valve(AF_DCMotor *valve){
  valve.run(CLOSE);
}

void systemInit(){
  Wire.begin(VALVES_CONTROLLER);
  Wire.onReceive(selectValveAction); 
  initValvesSwitches();
}

void initValvesSwitches(){
  pinMode(V1_IN_O, INPUT);
  digitalWrite(V1_IN_O, HIGH);
  pinMode(V1_IN_C, INPUT);
  digitalWrite(V1_IN_C, HIGH);
  
  pinMode(V1_OUT_O, INPUT);
  digitalWrite(V1_OUT_O, HIGH);
  pinMode(V1_OUT_C, INPUT);
  digitalWrite(V1_OUT_C, HIGH);
  
  pinMode(V2_IN_O, INPUT);
  digitalWrite(V2_IN_O, HIGH);
  pinMode(V2_IN_C, INPUT);
  digitalWrite(V2_IN_C, HIGH);

  pinMode(V2_OUT_O, INPUT);
  digitalWrite(V2_OUT_O, HIGH);
  pinMode(V2_OUT_C, INPUT);
  digitalWrite(V2_OUT_C, HIGH);
}
