#include "AFMotor.h" //to work with L239D 4-M shield
#include <EEPROM.h>
#include "Definitions.h"

int ssCode = 0; // system state code

void setup(){
    pinMode(NACLO, OUTPUT);
    pinMode(FB_BS, INPUT);
    pinMode(FB_TS, INPUT);
    pinMode(SB_BS, INPUT);
    pinMode(SB_TS, INPUT);
}

void loop(){
    getSystemState();

    switch(ssCode) {
        case 0:
            doseNaClO();
            fillFirstBarrel();
            doseNaClO();
            fillSecondBarrel();
            //open an OUT valve
            break;
        case 3:
            switchToSecondBarrel();
            break;
        case 12:
            switchToFirstBarrel();
            break;
    }

    delay(COMMON_DELAY);
}

void getSystemState(){
    digitalRead(FB_BS) == 1 ? SetBit(ssCode, 3) : ClearBit(ssCode, 3);
    digitalRead(FB_TS) == 1 ? SetBit(ssCode, 2) : ClearBit(ssCode, 2);
    digitalRead(SB_BS) == 1 ? SetBit(ssCode, 1) : ClearBit(ssCode, 1);
    digitalRead(SB_TS) == 1 ? SetBit(ssCode, 0) : ClearBit(ssCode, 0);
}

void doseNaClO(){
    tiltPipe( ? );
    if( ! is_it_time_of_wash? ){
        digitalWrite(NACLO, HIGH);
        delay(300);
        digitalWrite(NACLO, LOW);
    }
}

void switchToFirstBarrel(){

}

void switchToSecondBarrel(){

}
