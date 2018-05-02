package willow.getSimplerocketsShip.koishi.view;
import android.view.*;
import android.content.*;
import android.util.*;
import android.graphics.*;
import willow.getSimplerocketsShip.koishi.*;
import java.util.*;
import android.graphics.drawable.*;
import android.hardware.*;

public class StaffView extends View
{Context context;
float width,gravity_x,gravity_y;
Bitmap snowBmp;
List<Snow> snowList=new ArrayList<Snow>();
	long time;

	private SensorManager sm;
	public StaffView(Context c){
		super(c);
		context=c;
				
	}
	public StaffView(Context c,AttributeSet as){
		super(c,as);
		context=c;
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom)
	{
		// TODO: Implement this method
		super.onLayout(changed, left, top, right, bottom);
	width=getWidth()/100f;
		snowBmp=((BitmapDrawable)getResources().getDrawable(R.drawable.snow)).getBitmap();
			}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		canvas.drawColor(0xff0090ff);
		if(time==1){
			sm = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
			sm.registerListener(myAccelerometerListener, sm.getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_NORMAL); 
		}
		if(time<=240&&time%8==0){
			for(int i=5;i<8;i++){
			Snow snow=new Snow();
			snow.x=(float)(Math.random()*width*100);
			int wh=(int)(getWidth()/25f+Math.random()*getWidth()/30);
			Bitmap m=Bitmap.createScaledBitmap(snowBmp,wh,wh,false);
			snow.map=m;
			snow.y=-snow.map.getHeight();
			snow.vy=getHeight()/100f;
			snowList.add(snow);
			}
		}
		for(int i=0;i<snowList.size();i++){
			Snow snow=snowList.get(i);
			canvas.drawBitmap(snow.map,snow.x,snow.y,new Paint());
			snow.y=snow.y+snow.vy*width*3.3333f/(float)snow.map.getHeight();
			snow.x=snow.x-gravity_x*(float)snow.map.getWidth()/getWidth()*15f;
			if(snow.y>getHeight()+snow.map.getHeight()){
				snow.y=-snow.map.getHeight();
			}
			if(snow.x<-snow.map.getWidth()){
				snow.x=getWidth()+snow.map.getWidth();
			}
			if(snow.x>getWidth()+snow.map.getWidth()){
				snow.x=-snow.map.getWidth();
			}
		}
		canvas.drawText(gravity_x+"y="+gravity_y,30,39,new Paint());
		time++;
		invalidate();
	}
	class Snow{
		Bitmap map;
		float x,y,r;
		float vx,vy;
	}
	final SensorEventListener myAccelerometerListener = new SensorEventListener(){
        public void onSensorChanged(SensorEvent sensorEvent)
		{ 
			gravity_x = sensorEvent.values[0]; 
			gravity_y = sensorEvent.values[1]; 
			//gravity_z = sensorEvent.values[2];    
        } 
        public void onAccuracyChanged(Sensor sensor , int accuracy)
		{ 
        } 
    };
	@Override
	protected void onDetachedFromWindow()
	{sm.unregisterListener(myAccelerometerListener); 
		super.onDetachedFromWindow();
	}
}
