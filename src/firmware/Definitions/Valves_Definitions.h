/*Below goes mnemonic definitions of OPEN and CLOSE
 * signals for each valve.
 * For example V1_IN_O (Input valve of first barrel, Open signal)
 * or V2_OUT_C (Output valve of second barrel, Close signal)
 */
#define V1_IN 1 //Input valve of first barrel
#define V1_IN_O 2 
#define V1_IN_C 9

#define V1_OUT 2 //Output valve of first barrel
#define V1_OUT_O 20 //Use analogRead. Zero when button pressed.
#define V1_OUT_C 21 //Use analogRead. Zero when button pressed.

#define V2_IN 3
#define V2_IN_O 17
#define V2_IN_C 16

#define V2_OUT 4
#define V2_OUT_O 15
#define V2_OUT_C 14

//List of commands to be received from Master
#define V1_IN_OPEN 1
#define V1_IN_CLOSE 2
#define V1_OUT_OPEN 3
#define V1_OUT_CLOSE 4
#define V2_IN_OPEN 5
#define V2_IN_CLOSE 6
#define V2_OUT_OPEN 7
#define V2_OUT_CLOSE 8

//Other
#define VALVES_CONTROLLER 8