#include "AFMotor.h" //to work with L239D 4-M shield
#include <EEPROM.h>

#define SetBit(result, bit_num) result |= (1<<bit_num)
#define ClearBit(result, bit_num) (result &= (~(1<<bit_num)))

//definition of pins:
//floating switches
#define FB_BS 4 //First Barrel Bottom Switch
#define FB_TS 5 //First Barrel Top Switch
#define SB_BS 6 //Second ..
#define SB_TS 7

//electromechanical valves
#define FB_IN 8
#define FB_OUT 9
#define SB_IN 10
#define SB_OUT 11

//dosing pump
#define NACLO 12

//Real-Time Clock
#define ? ?

//misc
#define COMMON_DELAY 3000

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
