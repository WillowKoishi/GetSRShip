package willow.getSimplerocketsShip.koishi.fragment;
import android.support.v4.app.*;
import android.os.*;
import android.view.*;
import willow.getSimplerocketsShip.koishi.*;
import android.support.design.widget.*;
import android.widget.*;
import android.text.*;
import android.view.textservice.*;

public class DeltaVCalculater extends Fragment
{private EditText isp,m,m0,g;
private TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{View view=inflater.inflate(R.layout.fragment_deltav,container,false);
	isp=(EditText)view.findViewById(R.id.fragment_deltav_isp);
	m=(EditText)view.findViewById(R.id.fragment_deltav_m);
	m0=(EditText)view.findViewById(R.id.fragment_deltav_m0);
	g=(EditText)view.findViewById(R.id.fragment_deltav_g);
	tv=(TextView)view.findViewById(R.id.fragmentdeltavTextView1);
	h.postDelayed(r,1);
	return view;
	}
	Runnable r=new Runnable(){

		@Override
		public void run()
		{
			h.postDelayed(r,100);
			if(!(isEmpty(isp)||isEmpty(m)||isEmpty(m0)||isEmpty(g))){
				if(isp.getText().toString()=="."){}
				else if(m.getText().toString()=="."){}
				else if(m0.getText().toString()=="."){}
				else if(g.getText().toString()=="."){}
				else{
				double iisp=Double.valueOf(isp.getText().toString());
				double mm=Double.valueOf(m.getText().toString());
				double mm0=Double.valueOf(m0.getText().toString());
				double gg=Double.valueOf(g.getText().toString());
				double v=iisp*gg*Math.log(mm/(mm0));
				tv.setText("∆V="+v);
				}
			}
		}
	};
	Handler h=new Handler(){

		@Override
		public void handleMessage(Message msg)
		{
			// TODO: Implement this method
			super.handleMessage(msg);
		}
		};
		public boolean isEmpty(EditText edit){
			return TextUtils.isEmpty(edit.getText().toString());
		}
}
