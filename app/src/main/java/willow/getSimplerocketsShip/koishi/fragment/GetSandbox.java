package willow.getSimplerocketsShip.koishi.fragment;
import android.support.v4.app.*;
import android.os.*;
import android.view.*;
import willow.getSimplerocketsShip.koishi.*;
import android.support.v7.widget.*;
import android.view.View.*;
import android.content.*;
import android.net.*;
import android.support.design.widget.*;
import android.widget.*;
import android.text.*;
import willow.getSimplerocketsShip.koishi.view.*;

public class GetSandbox extends Fragment
{
	private TextInputEditText aet;
	private AppCompatButton b;
	private boolean can;
	private TextInputLayout til;
	public void pushSR(boolean loadApps)
	{
		can = loadApps;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view=inflater.inflate(R.layout.fragment_get_ship, container, false);
		aet = (TextInputEditText)view.findViewById(R.id.frame_getship_edit);
		b = (AppCompatButton)view.findViewById(R.id.frame_getship_button);
		til=(TextInputLayout)view.findViewById(R.id.fragment_til);
		til.setHint(this.getString(R.string.hint_getsandbox));
		//aet.setHint(R.string.hint_getsandbox);
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
						if(noEmpty(aet.getText())){
						WiToast.showToast(getContext(),R.string.noId, 3000);
						}
						else if(aet.getText().length()!=6){
						WiToast.showToast(getActivity(),R.string.noLong,3000);
						}
						else{
						startActivity(new Intent().setAction(Intent.ACTION_VIEW).setData(Uri.parse("simplerockets://03" + aet.getText())));
					}
						}
				}
			});
		//
		return view;
	}
public boolean noEmpty(CharSequence s){
	return TextUtils.isEmpty(s);
}
}
