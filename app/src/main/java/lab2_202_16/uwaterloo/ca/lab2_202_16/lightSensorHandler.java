package lab2_202_16.uwaterloo.ca.lab2_202_16;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

/**
 * Created by nicholasskupien on 2017-01-10.
 */

class lightSensorHandler implements SensorEventListener {

    //Declaring objects used within the class
    TextView tv1;

    /**
     * Constructor method
     * @param lightSensor : TextView object that is used to display light sensor readings
     */
    public lightSensorHandler(TextView lightSensor){
        this.tv1 = lightSensor;
    }

    //Don't need this but need to follow the format
    public void onAccuracyChanged(Sensor s, int i){}

    /**
     * Method that is run when the light sensor data is changed
     * @param se = array that is returned with light sensor data
     */
    public void onSensorChanged(SensorEvent se){

        //need to make sure only getting sensor data from the light sensor
        if(se.sensor.getType() == Sensor.TYPE_LIGHT) {
            tv1.setText("Light Intensity: " + se.values[0]);
        }

    }

}