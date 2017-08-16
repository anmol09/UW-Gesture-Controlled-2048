package lab2_202_16.uwaterloo.ca.lab2_202_16;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import ca.uwaterloo.sensortoy.LineGraphView;

/**
 * Created by nicholasskupien on 2017-01-10.
 */

class accelerometerSensorHandlerCopy implements SensorEventListener {

    //Declaring several global variables and objects used within the class
    float maxX,maxY,maxZ;
    float minX, minY, minZ;
    TextView tvData, tvMax;
    int count = 0;
    float X, Y, Z;
    boolean isFull = false;
    int indexNumber = 0;

    //Latest 100 readings (filtered)
    float[][] accelData = new float[100][3];

    //Textview and LineGraphView objects declared here
    TextView errorText;
    LineGraphView graph;

    //Low-Pass Filtering constant
    private final float FILTER_CONSTANT = 8.0f;

    //FSM: Setting up FSM states and signatures
    enum myState{WAIT, RISE_A, FALL_A, FALL_B, RISE_B, DETERMINED};
    myState state = myState.WAIT;

    enum mySig{LEFT, RIGHT, UP, DOWN, UNDETERMINED};
    mySig signature = mySig.UNDETERMINED;

    //FSM: Threshold constants for determining state of FSM
    final float[] THRES_A = {0.6f, 2.0f, -0.4f};
    final float[] THRES_B = {-0.6f, -2.0f, 0.4f};

    //FSM: sample counter is implemented here
    final int SAMPLE_AMOUNT = 30;
    int sampleCounter = SAMPLE_AMOUNT;

    /**
     * Constructor method
     * @param accelData : TextView object used for all instant acceleration data
     * @param accelMax : TextView object used for all maximum acceleration data
     * @param errText : TextView object used for displaying errors associated with file IO
     * @param lineGraph : LineGraphView object used for displaying line graph
     */
    public accelerometerSensorHandlerCopy(TextView accelData, TextView accelMax, TextView errText, LineGraphView lineGraph) { //constructor
        this.tvData = accelData;
        this.tvMax = accelMax;
        this.graph = lineGraph;
        this.errorText = errText;
    }

    //Don't need this but need to follow the format
    public void onAccuracyChanged(Sensor s, int i){}

    /**
     * Method that is run when the sensor data is changed
     * @param se = array that is returned with sensor data
     */
    public void onSensorChanged(SensorEvent se){

        int tailNumber;

        //Making sure the sensor type is in fact the accelerometer
        if(se.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION){

            //A loop that counts to 100 before resetting the text that displays CSV creation
            if(this.count > 100){
                this.errorText.setText(" ");
                count = 0;
            }

            count++;

            //Changing the index number so that the accelData array wraps around from front to end
            if(this.indexNumber > 99){
                this.indexNumber = 0;
                this.isFull = true;
            }

            //Getting to correct index of looped array.  The headNumber is 1 index behind of where the next index should be
            if (this.indexNumber == 99)
                tailNumber = 0; //Needs to go 1 index after 99 which I have declared as 0
            else
                tailNumber = this.indexNumber + 1;

            //Duplicate the latest data in the array to overwrite the oldest
            this.accelData[tailNumber][0] = this.accelData[this.indexNumber][0];
            this.accelData[tailNumber][1] = this.accelData[this.indexNumber][1];
            this.accelData[tailNumber][2] = this.accelData[this.indexNumber][2];

            //Adding the averaged newest data to the accelData array to the new latest index
            this.accelData[tailNumber][0] += (se.values[0] - this.accelData[tailNumber][0]) / FILTER_CONSTANT;
            this.accelData[tailNumber][1] += (se.values[1] - this.accelData[tailNumber][1]) / FILTER_CONSTANT;
            this.accelData[tailNumber][2] += (se.values[2] - this.accelData[tailNumber][2]) / FILTER_CONSTANT;

            this.indexNumber++;

            //Finding max values within the acceleration data
            if(se.values[0] > this.maxX)
                this.maxX = se.values[0];

            if(se.values[1] > this.maxY)
                this.maxY = se.values[1];

            if(se.values[2] > this.maxZ)
                this.maxZ = se.values[2];

            if(se.values[0] < this.minX)
                this.minX = se.values[0];

            if(se.values[1] < this.minY)
                this.minY = se.values[1];

            if(se.values[2] < this.minZ)
                this.minZ = se.values[2];

            //Changing the TextViews so that they show the acceleration data and the maximum values
            this.tvData.setText(String.format("\nAccelerometer Data:\nX: %.2f\nY: %.2f\nZ: %.2f\n\nRecord-High Accelerometer Readings", se.values[0], se.values[1], se.values[2]));
            this.tvMax.setText(String.format("Maximum:\nX: %.2f\nY: %.2f\nZ: %.2f\n\nMinimum:\nX: %.2f\nY: %.2f\nZ: %.2f", this.maxX, this.maxY, this.maxZ, this.minX, this.minY, this.minZ));

            //adding acceleration data to the graph from the tailNumber index (now the newest index)
            this.graph.addPoint(this.accelData[tailNumber]);
        }
    }

    /**
     * A method that resets all maximum values in the class and the graph
     */
    public void resetData(){
        this.maxX = 0;
        this.maxY = 0;
        this.maxZ = 0;
        this.minX = 0;
        this.minY = 0;
        this.minZ = 0;
        this.graph.purge();
    }

    /**
     * A method that adds the last 100 accelerometer data points into an array
     * @param file : the file that the last accelerometer readings will be written to
     */
    public void makeFile(File file) {

        //Bringing writer into the scope of the finally block
        PrintWriter writer = null;

        //A try-catch for file IO
        try {
            //Declaring necessary objects and variables
            writer = new PrintWriter(file);
            int headNumber;

            //Making columns for the CSV file
            writer.println("x, y, z");

            //Getting to correct index of looped array.  The headNumber is 1 index behind of where the next index should be
            if (this.indexNumber == 0)
                headNumber = 99; //Needs to go 1 index before 0 which I have declared as 99
            else
                headNumber = this.indexNumber - 1;

            //resetting a counter that allows errorText to only be displayed for a small amount of time
            this.count = 0;

            //Text that is displayed when a CSV is created
            this.errorText.setText("CSV file created");

            //Print out the last 100 data points if 100 or over has been recorded
            if (this.isFull) {
                for (int i = 0; i < 100; i++) {

                    //Print out last 100 data points from accelerometer from most recent
                    writer.println(String.format("%f,%f,%f", accelData[headNumber][0], accelData[headNumber][1], accelData[headNumber][2]));

                    //Decrease the index number to get an older index each time
                    headNumber--;

                    //if the start of the array is reached, it goes back to the end (since the array theoretically
                    //wraps around to the start after it ends)
                    if (headNumber < 0) {
                        headNumber = 99;
                    }
                }
            }
            //Print out the latest data points if there are less than 100 recordings
            else {
                for (int i = headNumber; i > 0; i--) {
                    writer.println(String.format("%f,%f,%f", accelData[i][0], accelData[i][1], accelData[i][2]));
                }
            }
        }

        //Catching any exceptions and displaying an error message if they do happen to appear (should never happen)
        catch (FileNotFoundException e) {
            this.errorText.setText("Caught file not found exception");
        } catch (NullPointerException e) {
            this.errorText.setText("Caught null pointer exception");
        } finally {
            //Necessary functions to save file
            writer.flush();
            writer.close();
        }
    }
}