package lab2_202_16.uwaterloo.ca.lab2_202_16;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;

public class Lab2_202_16 extends AppCompatActivity{

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab2_202_16_layout);

        //Setting the background dimensions
        final int GAMEBOARD_DIMENSION = 1080;

        //Making the relative layout
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.layout1);

        //Creates the TextView for displaying the direction, with a large size for ease in visibility
        TextView dir = new TextView(getApplicationContext());
        dir.setTextColor(Color.BLACK);
        dir.setTextSize(35);
        rl.addView(dir);

        //Setting the gameboard width and height
        rl.getLayoutParams().width = GAMEBOARD_DIMENSION;
        rl.getLayoutParams().height = GAMEBOARD_DIMENSION;

        //Giving the background image to the app
        rl.setBackgroundResource(R.drawable.gameboard);

        //Creates an instance of the GameLoopTask to be called repeatedly
        GameLoopTask GameLoopTask = new GameLoopTask(this, rl, getApplicationContext());

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);                      //Sensor manager requests the usage of the sensors
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);             //Allocates the sensor variable "accelerometer" as the accelerometer sensor, excluding gravity.
        accelerometerSensorHandler accListener = new accelerometerSensorHandler(dir, GameLoopTask);                        //Makes a new accSensorEventListener object and passes in the graph and output TextView
        sensorManager.registerListener(accListener, accelerometer, SensorManager.SENSOR_DELAY_GAME);       //Registers the accelerometer variable, and sets the delay to game (low delay)

        Timer myGameLoop = new Timer(); //creating the timer
        myGameLoop.schedule(GameLoopTask, 50, 50); //50ms periodic timer – 20fps
    }

}