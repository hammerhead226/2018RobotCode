# 2018RobotCode
Code for team 226's Power Up robot.

## Dependencies
#### SharkMacro
1. Download latest release of SharkMacro from [here](https://github.com/hammerhead226/SharkMacro/releases).
2. Copy the downloaded .jar file to `C:\Users\<YourUser>\wpilib\user\java\lib`.
3. Right click on 2018RobotCode in Eclipse > Build Path > Add External Archives...
4. Select the SharkMacro jar file you copied to the userlib directory.
5. Download Apache Commons Lang from [here](http://apache.spinellicreations.com//commons/lang/binaries/commons-lang3-3.7-bin.zip).
5. Download OpenCSV from [here](https://sourceforge.net/projects/opencsv/files/latest/download).
6. Repeat steps 2-4 with `commons-lang3-3.7.jar` and `opencsv-4.1.jar`

#### HammerheadLib
1. Download latest release of HammerheadLib from [here](https://github.com/minchingtonak/HammerheadLib/releases).
2. Copy the downloaded .jar file to `C:\Users\<YourUser>\wpilib\user\java\lib`.
3. Right click on 2018RobotCode in Eclipse > Build Path > Add External Archives...
4. Select the Hammerhead jar file you copied to the userlib directory.

## Developtment Software
#### JDK and Eclipse
1. Install the latest [Java SE Development Kit (Windows x64)](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
2. Install the latest [Eclipse IDE for Java Develoeprs (64 bit)](https://www.eclipse.org/downloads/eclipse-packages/).
3. Follow the [2018 FRC Control System](https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599681-installing-eclipse-c-java) Eclipse setup instructions **for Java only.**
    * The code deployment error `roboRIO Image does not match plugin, allowed image version: ##` means you need to update your Eclipse plugins.
    * Numerous `(class) cannot be resolved to a type` errors may indicate a bad project build path, you should re-create the project.
4. Install the latest [CTRE Phoenix Framework](http://www.ctr-electronics.com/control-system/hro.html#product_tabs_technical_resources) (for the Talon SRX motor controller).
5. Install the latest [Kauai Labs Libraries](https://www.pdocs.kauailabs.com/navx-mxp/software/roborio-libraries/java/) (for the navX-MXP gyro/accelerometer).

#### Installing National Instruments Software (FRC Driver Station)
1. Uninstall all existing National Instruments products form the "National Instruments Software" installer.
2. Create a [National Instruments account](http://www.ni.com/myni/dashboard/).
3. Install the [FRC 2018 Update Suite](http://www.ni.com/download/first-robotics-software-2017/7183/en/) (you can skip the LabVIEW product) using the serial number from your most recent kit of parts. Activate the software when prompted.
    * If asked for a password it is `pLaY&4%R3aL!`.
    * You may need to enable [.NET Framework 3.5](https://wpilib.screenstepslive.com/s/currentCS/m/getting_started/l/599670-installing-the-frc-update-suite-all-languages).
    * You may need to update [.NET Framework 4.6](https://wpilib.screenstepslive.com/s/currentCS/m/getting_started/l/599670-installing-the-frc-update-suite-all-languages).
4. Run "NI Update Service" ([manual download](http://search.ni.com/nisearch/app/main/p/bot/no/ap/tech/lang/en/pg/1/sn/catnav:du/q/ni%20update%20service/)) and install all critical updates, but no patches.
5. Open FRC Driver Station and set your team number on the Setup tab.

#### Importing the Eclipse Project
1. Switch your workspace to the cloned folder (where this README is).
2. Import the Java project into the workspace.
3. Troubleshooting:
    * `Unable to find a javac compiler` / `com.sun.tools.javac.Main is not on the classpath` - JDK needs to be configured in the global build path (above WPILib instructions).
    * `Property 'team-number' doesn't exist in this project` - follow the instructions in the error message.
    * `The import java.* cannot be resolved` - "JRE System Library" needs to be added to the project's build path libraries.
    
## Additional
#### Updating the roboRIO
1. Follow the [Imaging your roboRIO](https://wpilib.screenstepslive.com/s/currentCS/m/getting_started/l/144984-imaging-your-roborio) guide.
2. Follow the [Installing Java 8 on the roboRIO using the FRC roboRIO Java Installer](https://wpilib.screenstepslive.com/s/currentCS/m/java/l/288822-installing-java-8-on-the-roborio-using-the-frc-roborio-java-installer-java-only) guide.
3. Follow the [Installing Phoenix Framework onto your FRC robot](https://github.com/CrossTheRoadElec/Phoenix-Documentation#installing-phoenix-framework-onto-your-frc-robot) guide.

#### Configuring the Robot Radio
* [OpenMesh OM5P-AC / OM5P-AN](https://wpilib.screenstepslive.com/s/currentCS/m/getting_started/l/144986-programming-your-radio) (2017 / 2016). Troubleshooting:
    * `Bridge firmware load failed: Error finding NPF device name for adapter` - disable all other network adapters and try again. 
* [D-Link DAP-1522](http://wpilib.screenstepslive.com/s/3120/m/8559/l/91405-programming-your-radio-for-home-use) (2015) (NI install with tools no longer available).
