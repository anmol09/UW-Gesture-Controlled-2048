package lab2_202_16.uwaterloo.ca.lab2_202_16;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.RelativeLayout;

import java.util.TimerTask;

/**
 * Created by nicholasskupien on 2017-03-05.
 */

public class GameLoopTask extends TimerTask {
    //Private variables for storing the activity, the context and the layout
    private Activity activity;
    private Context context;
    private RelativeLayout rl;

    //Private variable for storing the instance of the created GameBlock
    private GameBlock GameBlock;

    //Enum created for communicating the direction of motion
    public enum gameDirection{UP, DOWN, LEFT, RIGHT, NO_MOVEMENT}

    //Variable created for tracking the direction of motion
    public gameDirection currentGameDirection;

     @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
     public GameLoopTask(Activity myActivity, RelativeLayout myrl, Context myContext){
         //Stores the activity,  context and layout of the main method in its private variables
         this.activity = myActivity;
         this.rl = myrl;
         this.context = myContext;
         //Creates a GameBlock
         GameBlock = createBlock();
     }

    //The method required by the abstract nature of the TimerTask method, will be the method that runs periodically
    @Override
    public void run() {
        //Function that will allow the enclosed statements to be run on the main activity
        this.activity.runOnUiThread(
                new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
                    //Statements that will run when the timer is called
                    public void run(){
                        //Log.d("text", "Direction: " + currentGameDirection);
                        GameBlock.move(); //Run the move method that will animate the block moving
                    }
                }
        );
    }


    public void setDirection(gameDirection newDirection){
        this.currentGameDirection = newDirection;           //Updates the private field for tracking the movement
        this.GameBlock.setBlockDirection(newDirection);     //Updates the private field in the GameBlock method for tracking the movement
    }

    //Function that creates blocks and adds them to the layout
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private GameBlock createBlock(){
        //(-25, -25) is top left
        //(780, -25) is top right
        //(-25, 780) is bottom left
        //(780, 780) is bottom right
        GameBlock newBlock = new GameBlock(context, 780, 780);
        rl.addView(newBlock);

        return newBlock;
    }
}
