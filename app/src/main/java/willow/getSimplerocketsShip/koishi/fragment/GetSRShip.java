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
import android.support.design.widget.TextInputLayout;
import willow.getSimplerocketsShip.koishi.view.*;
import android.text.*;
import android.content.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v4.app.*;
import android.support.v7.app.*;
import android.text.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import willow.getSimplerocketsShip.koishi.*;
import android.widget.ExpandableListView.*;
import android.widget.TextView.*;
import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;

public class GetSRShip extends Fragment
{
	private  EditText aet;
	private AppCompatButton b;
	private boolean can;
	
	public void pushSR(boolean loadApps)
	{
		// TODO: Implement this method
		can = loadApps;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		//View viw = inflater.inflate(R.layout.fragment_title, container, false);
		View view=inflater.inflate(R.layout.fragment_get_ship, container, false);
		aet = (EditText)view.findViewById(R.id.frame_getship_edit);
		b = (AppCompatButton)view.findViewById(R.id.frame_getship_button);
  final  TextInputLayout til=(TextInputLayout)view.findViewById(R.id.fragment_til);
		//til.setHintEnabled(true);
		til.setHint(this.getString(R.string.hint_getship));
		//aet.setHint(R.string.hint_getship);
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
						startActivity(new Intent().setAction(Intent.ACTION_VIEW).setData(Uri.parse("simplerockets://00" + aet.getText())));
					}
					}
				}
			});
		return view;
	}
	public boolean noEmpty(CharSequence s){
		return TextUtils.isEmpty(s);
	}
}
