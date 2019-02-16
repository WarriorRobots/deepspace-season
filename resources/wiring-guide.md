# Westwood FRC Wiring Guide (2018-2019)

<!-- Author: Joshua Budd -->
<!-- Date: 2019/01/23 -->
<!-- Revised: 2019/01/23 -->

![Narwals scematic](https://www.chiefdelphi.com/uploads/default/optimized/3X/5/c/5c9037bbfb8bf6c847796320417f68c02262fb1a_2_690x500.jpeg "3128 Aluminum Narwals' scematic")

## Table of Contents

* [Battery & Breaker](#battery)
* [PDP](#pdp)
* [RoboRIO](#rio)
* [VRM](#vrm)
* [PCM](#pcm)
* [Radio](#radio)
* [Talon](#talon)
* [RSL](#rls)
* [DIO](#dio)
* [Encoder](#encoder)
* [CAN Wiring](#can)
* [Wire Gauge](#gauge)

<a id="battery"></a>
## Battery & Breaker
![Breaker image](https://camo.githubusercontent.com/0b495433d0d9e651cb5396a8a9f3491c686d3d36/68747470733a2f2f73332e616d617a6f6e6177732e636f6d2f73637265656e73746570735f6c6976652f696d6167655f6173736574732f6173736574732f3030312f3138392f3936342f6d656469756d2f63643034623564382d643137312d343235392d383764362d6431386133626461326338612e6a7067)

* **Negative** lead goes to [PDP](#pdp)
* **Positive** lead goes to breaker
    * Attaches to side reading "THERMAL CIRCUIT BREAKER"

<a id="pdp"></a>
## Power Distribution Panel (PDP)
![PDP image](https://camo.githubusercontent.com/830473ea9f270823bfd61f35b4557cb827954140/68747470733a2f2f73332e616d617a6f6e6177732e636f6d2f73637265656e73746570735f6c6976652f696d6167655f6173736574732f6173736574732f3030302f3238392f3930342f6f726967696e616c2f34373936383838382d626566312d343161622d623831652d3335653333626464373439632e706e67)

(Down is the side with the power ports.)

* **Red** 10 Amp breaker in top left
    * [RoboRIO](#rio) goes in **blue** "Vbat CONTROLLER POWER"
* **Yellow** 20 Amp breaker in top right
    * [VRM](#vrm) and [PCM](#pcm) goes in **green** "Vbat VRM PCM PWR"
* ports (0, 1, 3, 4, 12, 13, 14, 15):
    * 40 Amp breaker
    * [Talons](#talon) plug in here
* ports (4, 5, 6, 7, 8, 9, 10, 11):
    * 5, 20, or 30 Amp breakers
    * Camera plugs in here
* [CAN](#can) Cable in CAN (bottom right):
    Jumper switch must be in ON position

<a id="rio"></a>
## RoboRIO
![RIO image](https://camo.githubusercontent.com/d9bde9dbed2c45394b65d95f4e130a4581ef81f4/68747470733a2f2f73332e616d617a6f6e6177732e636f6d2f73637265656e73746570735f6c6976652f696d6167655f6173736574732f6173736574732f3030302f3238392f3930332f6f726967696e616c2f62373061653839342d653063352d343964332d623865322d3032346537366264376336632e706e67)

(Up is the the side with the USB ports.)

* 22 guage Power Cable in INPUT (left of USB ports)
    * **Positive** goes in V
    * **Negative** goes in C
* [CAN](#can) Cable in CAN (left of power cable)
    * **Yellow** goes in H
    * **Green** goes in L
* [DIO](#dio) sensors in DIO (left side)
    * Boolean sensors go in DIO ports (Boolean sensors are sensors with only on and off states.)
    * (e.g. Infrared sensors, limit switches)
    * Begin plugging in sensors at 0 and count up (not required, good wiring practice)
* [PWM](#pwm) sensors in PWM (right side)
    * Analog sensors go in PWM ports (Analog sensors are sensors with a range of outputs)
    * (e.g. ultrasonic sensors, potentiometers)
    * Begin plugging in sensors at 0 and count up
* [RSL](#rsl) light in RSL (left-most bottom port)
    * **Positive** goes in S
    * **Negative** goes in ground (the dashed lines)
* NavX in MXP (center)
    * NavX gyroscope is mounted with majority of sensor closer to USB ports

<a id="vrm"></a>
## Voltage Regulator Module (VRM)
![VRM image](https://camo.githubusercontent.com/cefd54640d976bda2b24c11119e1a0acef85805f/68747470733a2f2f73332e616d617a6f6e6177732e636f6d2f73637265656e73746570735f6c6976652f696d6167655f6173736574732f6173736574732f3030302f3238392f3930362f6f726967696e616c2f62373762363261622d623963612d346530332d393431352d6664656533373839616634322e706e673f31343833353439323130)

(Down is the side with the 12V and 5V lights)

* Power Cables in 12V (top)
* 12V/2A (upper left)
    * [Radio](#radio) Power (circular plug)

<a id="pcm"></a>
## Pneumatic Control Module (PCM)
![PCM image](https://camo.githubusercontent.com/233e3fb008c2e5af5def9a2148eaabbe69380d9e/687474703a2f2f736c696465706c617965722e636f6d2f736c6964652f373334363433342f32342f696d616765732f392f506e65756d617469632b436f6e74726f6c2b4d6f64756c652b2850434d292e6a7067)

(Left is the side with the Vin)

* [PCM](#pcm) power in Vin (left)
* [CAN](#can) Cable in CAN (top left)
* Pressure Meter Power in **blue** PRESSURE SW.
* Air compressor Power in **green** COMPRESSOR OUT
* ports (0, 1, 2, 3, 4, 5, 6, 7)
    * Solenoid Power goes here

<a id="radio"></a>
## Radio (OM5P-AC)
![Radio image](https://camo.githubusercontent.com/03df5f5152b9223cab19e8c20c678cd58acadde3/68747470733a2f2f7777772e62726f616462616e6462757965722e636f6d2f696d616765732f70726f64756374732f6f70656e6d6573682f6f6d35702d61632d372e706e673f77696474683d343030)

* Radio Power circular plug in left
* Camera ethernet in middle (not required)
* [RoboRIO](#rio) ethernet in right

<a id="talon"></a>
## Talon SRX & Victor
![Talon image](https://camo.githubusercontent.com/440f1a79acac64e95bc5f25d42493260b2a4c763/68747470733a2f2f7777772e766578726f626f746963732e636f6d2f6d656469612f636174616c6f672f70726f647563742f63616368652f312f696d6167652f39646637386561623333353235643038643665356662386432373133366539352f322f312f3231372d343335382d6f6e2d74616c6f6e2e6a7067)

* Talon & Victor power are red and black
    * plug in 40 Amp PDP port
    * ***DO NOT PLUG INTO MOTORS***
* Motor Power is green and white
* Talon only: Encoder data cable on top, covered with hex screws & plastic cover 

<a id="rsl"></a>
## Robot Signal Light (orange RSL)
![RSL image](https://camo.githubusercontent.com/7a5fef32a7def38ab32085348a5896fdeb144583/68747470733a2f2f6d696c696c616e69726f626f746963732e676974626f6f6b732e696f2f6672632d656c656374726963616c2d6269626c652f636f6e74656e742f726f626f52494f2f72736c2e6a7067)

* Power cable from [RoboRIO](#rio)
    * **Positive** in La and Lb
    * **Negative** in N

<a id="dio"></a>
## Digital Input and Output (DIO)
![DIO image](https://lh3.googleusercontent.com/OzJ9KZFlNvmwgM-J1iNCvhOCIkKYclQIC00oVAzyyDzcTzNY2mH55wp6UL3KJ8fmAI4AxLUB0Mzfha6DSqlcfTtfmkjiWtmlvXXgY4n-g8CZnZmWELl_bnS6KiJE_VdNO90tHUGoKEoxo1sqh6O_hckiu1VR5kSwLD1WmPKBULM0AmEq-ukLH91C21JikteUyu_ovyARsDxIRIKjMQlfmeI-OQKk0tjGMrLbvjYrUyRQle6O_rnz-BSFvxSQSgplKRdzi8RnIs81c-o8QD1fEvi84ArVksMs1dSYa2ViX4htxFrsG9q4mHefC_iCKVSWt772v8HTjgWG0xbF8DqS3j1N3U02ovxsQm2eAY_SjikkCU9Pj2Q35hx7qEen94LOXHctgNExF3-pgryrQYzUScuQcKI-jsZpu-AUseZzKyvzYrT-23ioi3mFcubUd5a5unmRQcsRHnfTjl0U4K0JxQmP39hQWWzbnF-x4bBCQywmfEKAk8i-WUFEodB6ONmHVsVZISBqLqG97HdTaiG9KWIN2uov5rkfxoJlWxTwl4PLXR4zAQ3VvA1BldCNtsKkNqzq4a1NHvz6Q0F10sZA2ECcxzjn7kB0mDoq4cOE8H3_Ceg0cMAnhN-9q4V0vi_hXODFbGLgx2PxRCvUEKuZNUp50tIS83GuzBbUKwxZxo_aIHB5QHiVW1XN590TdeUeWs2ffPMpg79PFrPqqw=w470-h626-no)

* Ports labled 0 through 9 on [RoboRIO](#rio)
* All digital sensors go in DIO ports
* Begin plugging in sensors at 0 and count up (not required, good wiring practice)

<a id="encoder"></a>
### Grayhill Encoder
![Encoder image](https://lh3.googleusercontent.com/wko3en9Z8KM560biqJSt4U9EOPXYX4OS94wFihy7Y_VYtULn3bLoMOG3uVmUNbGhRSesz9U6dzM2zqEcxkI3lKkwEFx2qZQA2HYIxUhzCE6-uuGjvPWB2lWq_ZhGehcfAWXISmZih_qHC784WLivPGCIq3XxNbOwJ-wGZjFV-Zo319Oc-NF-1VVtVj997Ypr6Xddl65mufv7wKHvJMcNDHTCGilkw8fQhdAvTeLzrxSh3zNnrEeCO9z3e_kNNWq2qg5g1jr6ReMof2TClRPDK4pu-fY6T9QOApqKwiSgD7cHggBgGmL5iKnbuUP8EzrNC3YUZidld7QiZicVKKXlqV0LzkpeWD8tpgK5gagZdqf9cElq1UD5t_s2frKiN0Eso_XQLqSlqBduNLA8KAeod3o-qq27gs_ncuPV4-PW1LPm-VWmq5cyRDctp7AumYGHhBkkgUCTV-GrdbPB5xJBN2lNQtaGDQDMECcyvitW74QjguA-J5sfYjoXrb86JMTEdrnxKO6t2jBPESP3HA1OZLBJK9LCyB0YrHK0cSvLloEteITxoKi7Vis2AP3fVjYs2d6EMGMQPMwJ6IYrSp7vTTH_UFCXrq88NxKHY8vl64p2OJScTVyOaU0sfrVyTMjMPdCHUCHdiOUtnOkONDhhKj1D=w470-h626-no)

(Up is the direction so DIO and the numbers are upside up)

* ***DO NOT RELY ON COLORS OF DIO CABLES***
* DO NOT BEND PINS
* Encoder has 5 pins labeled `B + A _ G` (\_ is blank)
* On 5 pin cable, connect to encoder so the missing wire goes to the \_
* On 3 pin cable, connect to [RIO](#rio) so G is on the ground (bottom pin) of an EVEN position (not required wiring practice)
* On 2 pin cable, connect to RIO so signal (A or B) is on S (top pin) of the NEXT position (not required, good wiring practice)
* (e.g. if 3 pin cable is in position 4, the 2 pin cable goes in position 5)
* There are 128 clicks in a rotation

<a id="ir"></a>
### IR sensor
![IR Sensor image]()

(Up is the direction so DIO and the numbers are upside up)

* DO NOT BEND PINS
* Blue cable from sensor goes in ground (bottom pin)
* Brown cable from sensor goes in positive (middle pin)
* Black cable from sensor goes in signal (top pin)

---

<a id="can"></a>
### CAN LOOP Instructions

* Start CAN at [PDP](#pdp)
* Connect through all [Talons](#talon), Victors, and [PCM](#pcm)
* End CAN at [RoboRIO](#rio)

<a id="gauge"></a>
### Wiring Gauge guide

* 22 gauge:
    * [CAN](#can) Loop
    * [VRM](#vrm) Power
    * [PCM](#pcm) Power
    * [Radio](#radio) Power
    * [RoboRIO](#rio) Power
    * [RSL](#rsl) Power
* 16 gauge:
    * [Talon](#talon) Power
    * Victor Power
    * Motor Power
* 6 gauge:
    * [Battery](#battery) Power
    * Breaker Power
