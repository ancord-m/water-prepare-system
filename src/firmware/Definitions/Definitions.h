#ifndef Definitions_h
#define Definitions_h

#include "WProgram.h"
#include Arduino.h

#define SetBit(result, bit_num) result |= (1<<bit_num)
#define ClearBit(result, bit_num) (result &= (~(1<<bit_num)))

//definitions of pins:
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
#define PIPE_TILT_ANGLE 30

//Real-Time Clock
#define ? ?

//misc
#define COMMON_DELAY 3000


#endif