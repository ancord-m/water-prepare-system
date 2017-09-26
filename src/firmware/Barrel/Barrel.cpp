#include "Barrel.h"
#include "WProgram.h"

Barrel::Barrel(int bottom_switch_pin, int top_switch_pin){
    this.bottom_switch_pin = bottom_switch_pin;
    this.top_switch_pin = top_switch_pin;
}