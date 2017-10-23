package willow.getSimplerocketsShip.koishi.fragment;
import android.content.*;
import android.content.pm.*;
import android.net.*;
import android.os.*;
import android.support.v4.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import java.util.*;
import willow.getSimplerocketsShip.koishi.*;

public class GetSRShip extends Fragment
{
 private AppCompatEditText aet;
	private Button b;
private boolean can;
	public void pushSR(boolean loadApps)
	{
		// TODO: Implement this method
		can=loadApps;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		//View viw = inflater.inflate(R.layout.fragment_title, container, false);
		View view=inflater.inflate(R.layout.fragment_get_ship,container,false);
		aet=(AppCompatEditText)view.findViewById(R.id.frame_getship_edit);
		b=(Button)view.findViewById(R.id.frame_getship_button);
		if(!can){
			b.setText(R.string.hasnot);
		}
		b.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{if(can){
					startActivity(new Intent().setAction(Intent.ACTION_VIEW).setData(Uri.parse("simplerockets://00"+aet.getText())));
				}
				}
			});
	return view;
		}
}
