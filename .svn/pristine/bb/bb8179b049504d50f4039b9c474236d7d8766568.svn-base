package lab2_202_16.uwaterloo.ca.lab2_202_16;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.widget.TextView;

/**
 * Created by nicholasskupien on 2017-01-10.
 */

class magneticFieldSensorHandler extends rotationHandler {

    /**
     * Constructor method
     * @param magData : TextView object used for all instant magnetic field data
     * @param magMax  : TextView object used for all maximum magnetic field data
     */
    public magneticFieldSensorHandler(TextView magData, TextView magMax) { //constructor
        super(magData, magMax);
    }

    /**
     * Method that is run when the magnetic field data is changed
     * @param se = array that is returned with magnetic field data
     */
    public void onSensorChanged(SensorEvent se) {

        //Making sure the sensor type is in fact the magnetic field
        if (se.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {

            //Finding max values within the magnetic field data
            if (se.values[0] > maxX)
                maxX = se.values[0];

            if (se.values[1] > maxY)
                maxY = se.values[1];

            if (se.values[2] > maxZ)
                maxZ = se.values[2];

            if (se.values[0] < minX)
                minX = se.values[0];

            if (se.values[1] < minY)
                minY = se.values[1];

            if (se.values[2] < minZ)
                minZ = se.values[2];

            //Changing the TextViews so that they show the magnetic field data and the maximum values
            tvData.setText(String.format("\nMagnetic Field Data:\nX: %.2f\nY: %.2f\nZ: %.2f\n\nRecord-High Magnetic Field Readings", se.values[0], se.values[1], se.values[2]));
            tvMax.setText(String.format("Maximum:\nX: %.2f\nY: %.2f\nZ: %.2f\n\nMinimum:\nX: %.2f\nY: %.2f\nZ: %.2f", maxX, maxY, maxZ, minX, minY, minZ));
        }
    }
}