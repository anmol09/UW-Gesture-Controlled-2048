package lab2_202_16.uwaterloo.ca.lab2_202_16;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;

/**
 * Created by nicholasskupien on 2017-03-05.
 */

public class GameBlock extends ImageView {
    //The scaling that must be done to the image that is being used
    private final float IMAGE_SCALE = .7f;

    //Variables that are used for tracking the current and desired x, y coordinate values
    private int currCoordX, targetCoordX;
    private int currCoordY, targetCoordY;

    //Variable for tracking the velocity (pixels/tick) and the acceleration (pixels/tick/tick)
    private int velocity;
    private final float ACCELERATION = 10.0f;

    //The target direction of the block
    private GameLoopTask.gameDirection targetDirection;

    //Variables that dictate the maximum and minimum x,y values that the GameBlock can be set to
    private final int XYMIN = -25, XYMAX = 780;

    //Constructor method of the GameBlock
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    GameBlock(Context myContext, int coordX, int coordY){
        super(myContext);                                //Retrieves the context and states that its for the super class

        //Sets the image to the specified x,y coordinates, and updates the current x,y position
        this.currCoordX = coordX;
        this.currCoordY = coordY;
        this.setX(coordX);
        this.setY(coordY);

        this.setImageResource(R.drawable.gameblock);     //Sets the image resource for the GameBlock
        this.setScaleX(IMAGE_SCALE);                     //Sets the image scale
        this.setScaleY(IMAGE_SCALE);                     //Sets the image scale
        targetDirection = GameLoopTask.gameDirection.NO_MOVEMENT;   //Defaults the target movement to no direction to eliminate null pointer errors
    }

    //Function that sets the new direction of the GameBlock to the private field that tracks the movement of the GameBlock
    public void setBlockDirection(GameLoopTask.gameDirection inDir){
        targetDirection = inDir;
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void move(){
        //Switch statement to find out what direction the block will be moving
        switch(targetDirection){
            //Only commenting one case as they are all identical (except for direction)
            case LEFT:
                //Setting the target coordinate to the maximum bound in the X or Y direction
                targetCoordX = XYMIN;

                //If the block is not at (or passed) the target coordinate
                if(currCoordX > targetCoordX){
                    //If the block will be passed the target coordinate once moving it due to the velocity
                    if((currCoordX - velocity) <= targetCoordX){
                        //setting the current coordinate to the target (moving block directly to target)
                        currCoordX = targetCoordX;
                        //resetting the velocity
                        velocity = 0;
                    }
                    //If the block will not be at (or pass) the target coordinate
                    else {
                        //moving the position due to the block's velocity
                        currCoordX -= velocity;
                        //increasing the velocity due to the acceleration
                        velocity += ACCELERATION;
                    }
                }

                break;

            case RIGHT:

                targetCoordX = XYMAX;

                if(currCoordX < targetCoordX){
                    if((currCoordX + velocity) >= targetCoordX){
                        currCoordX = targetCoordX;
                        velocity = 0;
                    }
                    else {
                        currCoordX += velocity;
                        velocity += ACCELERATION;
                    }
                }

                break;

            case UP:

                targetCoordY = XYMIN;

                if(currCoordY > targetCoordY){
                    if((currCoordY - velocity) <= targetCoordY){
                        currCoordY = targetCoordY;
                        velocity = 0;
                    }
                    else {
                        currCoordY -= velocity;
                        velocity += ACCELERATION;
                    }
                }

                break;

            case DOWN:

                targetCoordY = XYMAX;

                if(currCoordY < targetCoordY){
                    if((currCoordY + velocity) >= targetCoordY){
                        currCoordY = targetCoordY;
                        velocity = 0;
                    }
                    else {
                        currCoordY += velocity;
                        velocity += ACCELERATION;
                    }
                }

                break;

            default:
                break;

        }

        //Setting the current coordinates to the block
        this.setX(currCoordX);
        this.setY(currCoordY);

        //Setting the state to no movement if no velocity (if velocity was reset by the block getting to the target)
        if(velocity == 0)
            targetDirection = GameLoopTask.gameDirection.NO_MOVEMENT;

    }




}
