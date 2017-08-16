package lab2_202_16.uwaterloo.ca.lab2_202_16;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

/**
 * Created by nicholasskupien on 2017-01-10.
 */

class rotationHandler implements SensorEventListener {

    //Declaring several global variables and objects used within the class
    float maxX,maxY,maxZ;
    float minX, minY, minZ;
    TextView tvData, tvMax;

    /**
     * Constructor method
     * @param rotData : TextView object used for all instant sensor data
     * @param rotMax : TextView object used for all maximum sensor data
     */
    public rotationHandler(TextView rotData, TextView rotMax) { //constructor
        this.tvData = rotData;
        this.tvMax = rotMax;
    }

    //Don't need this but need to follow the format
    public void onAccuracyChanged(Sensor s, int i){}

    /**
     * Method that is run when the sensor data is changed
     * @param se = array that is returned with sensor data
     */
    public void onSensorChanged(SensorEvent se){

        //Making sure the sensor type is in fact the rotation vector
        if(se.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR){

            //Finding max values within the rotation vector data
            if(se.values[0] > maxX)
                maxX = se.values[0];

            if(se.values[1] > maxY)
                maxY = se.values[1];

            if(se.values[2] > maxZ)
                maxZ = se.values[2];

            if(se.values[0] < minX)
                minX = se.values[0];

            if(se.values[1] < minY)
                minY = se.values[1];

            if(se.values[2] < minZ)
                minZ = se.values[2];

            //Changing the TextViews so that they show the rotation vector data and the maximum values
            tvData.setText(String.format("\nRotation Vector Data:\nX: %.2f\nY: %.2f\nZ: %.2f\n\nRecord-High Rotation Vector Readings", se.values[0], se.values[1], se.values[2]));
            tvMax.setText(String.format("Maximum:\nX: %.2f\nY: %.2f\nZ: %.2f\n\nMinimum:\nX: %.2f\nY: %.2f\nZ: %.2f", maxX, maxY, maxZ, minX, minY, minZ));
        }
    }

    /**
     * A method that resets all maximum values in the class
     */
    public void resetData(){
        maxX = 0;
        maxY = 0;
        maxZ = 0;
        minX = 0;
        minY = 0;
        minZ = 0;
    }
}