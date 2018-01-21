package org.usfirst.frc.team226.robot.vision;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class VisionRun {
	
	static UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(0);
	public static final int IMG_WIDTH = 320;
	public static final int IMG_HEIGHT = 240;
	public static Object midpointLock = new Object();
	public static int midpoint;
	public static VisionThread thread;

	public void start() {
		camera1.setResolution(IMG_WIDTH, IMG_HEIGHT);
		camera1.setFPS(30);
		
		thread = new VisionThread(camera1, new GripPipeline(), pipeline -> {
			int largestIdx = -1;
			double largestArea = -1;
			for (int i = 0; i < pipeline.filterContoursOutput().size(); i++) {
				Rect rect = Imgproc.boundingRect(pipeline.filterContoursOutput().get(i));
				if(rect.area() > largestArea) {
					largestIdx = i;
					largestArea = rect.area();
				}
			}
			
			if(largestIdx >= 0) {
				synchronized(midpointLock) {
					Rect rect = Imgproc.boundingRect(pipeline.filterContoursOutput().get(largestIdx));
					midpoint = rect.x + rect.width / 2;
					SmartDashboard.putNumber("midpoint", midpoint);
				}
			}
		});
		thread.start();;
	}
}

