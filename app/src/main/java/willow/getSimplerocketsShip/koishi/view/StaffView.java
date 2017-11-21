package willow.getSimplerocketsShip.koishi.view;
import android.view.*;
import android.content.*;
import android.util.*;
import android.graphics.*;
import willow.getSimplerocketsShip.koishi.*;

public class StaffView extends View
{Context context;
	public StaffView(Context c){
		super(c);
		context=c;
	}
	public StaffView(Context c,AttributeSet as){
		super(c,as);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		canvas.drawColor(0xff0090ff);
	}
	
}
