package willow.getSimplerocketsShip.koishi.fragment;
import android.support.v4.app.*;
import android.os.*;
import android.view.*;
import willow.getSimplerocketsShip.koishi.*;
import android.support.v7.widget.*;
import android.view.View.*;
import android.content.*;
import android.net.*;

public class GetSandbox extends Fragment
{
	private AppCompatEditText aet;
	private AppCompatButton b;
	private boolean can;
	public void pushSR(boolean loadApps)
	{
		can = loadApps;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view=inflater.inflate(R.layout.fragment_get_ship, container, false);
		aet = (AppCompatEditText)view.findViewById(R.id.frame_getship_edit);
		b = (AppCompatButton)view.findViewById(R.id.frame_getship_button);
		aet.setHint(R.string.hint_getsandbox);
		b.setText(R.string.get);
		if (!can)
		{
			b.setText(R.string.hasnot);
		}
		b.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{if (can)
					{
						startActivity(new Intent().setAction(Intent.ACTION_VIEW).setData(Uri.parse("simplerockets://03" + aet.getText())));
					}
				}
			});
		//
		return view;
	}

}
