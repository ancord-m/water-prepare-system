#ifndef Barrel_h
#define Barrel_h

#include "WProgram.h"
#include Arduino.h

class Barrel(){
    public:
        Barrel(int bottom_switch_pin, int top_switch_pin);
        bool isWasting();
    private:
        bool wasting;
        int bottom_switch_pin;
        int top_switch_pin;
}

#endif