package willow.getSimplerocketsShip.koishi.fragment;
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

public class DeltaVCalculater extends Fragment
{private EditText isp,m,m0,g;
	private TextInputLayout ti,tm,tm0,tg;
	private TextView tv,tv2;
	private View view;
	private Button refer;
	private AlertDialog.Builder b;
	private AlertDialog a;
	private List<String> groupList;
    private List<List<String>> childList;
	private List<List<String>> ispDAta;
	private HTextView ht;
	private DeltaVCalculater.FuckingMan fuckAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{ view = inflater.inflate(R.layout.fragment_deltav, container, false);
		isp = (EditText)view.findViewById(R.id.fragment_deltav_isp);
		m = (EditText)view.findViewById(R.id.fragment_deltav_m);
		m0 = (EditText)view.findViewById(R.id.fragment_deltav_m0);
		g = (EditText)view.findViewById(R.id.fragment_deltav_g);
		ht=(HTextView)view.findViewById(R.id.text2);
		ht.setAnimateType(HTextViewType.FALL);
		isp.addTextChangedListener(tListener);
		m.addTextChangedListener(tListener);
		m0.addTextChangedListener(tListener);
		g.addTextChangedListener(tListener);
		//tv = (TextView)view.findViewById(R.id.fragmentdeltavTextView1);
		tv2=(TextView)view.findViewById(R.id.fragmentdeltavTextView1);
		ti = (TextInputLayout)view.findViewById(R.id.fragmentdeltavTextInputLayout1);
		tm = (TextInputLayout)view.findViewById(R.id.fragmentdeltavTextInputLayout2);
		tm0 = (TextInputLayout)view.findViewById(R.id.fragmentdeltavTextInputLayout3);
		tg = (TextInputLayout)view.findViewById(R.id.fragmentdeltavTextInputLayout4);
		refer = (Button)view.findViewById(R.id.reference);
		h.postDelayed(r, 1000);
		View view2 = View.inflate(getActivity(), R.layout.dialog_reference, null);
		b = new AlertDialog.Builder(getActivity());
		b.setView(view2).setTitle(R.string.reference).setMessage(R.string.refer_intro);
		ExpandableListView referData=(ExpandableListView)view2.findViewById(R.id.dialogreferenceListView1);
		referData.setOnChildClickListener(new OnChildClickListener(){

				@Override
				public boolean onChildClick(ExpandableListView p1, View p2, int p3, int p4, long p5)
				{
					isp.setText(ispDAta.get(p3).get(p4));
					a.dismiss();
					return true;
				}
			});
		groupList = new ArrayList<>();
        childList = new ArrayList<>();
		ispDAta = new ArrayList<>();
		addData(getString(R.string.simplerockets), getResources().getStringArray(R.array.sr_engine), getResources().getStringArray(R.array.sr_isp));
		addData(getString(R.string.ksp), getResources().getStringArray(R.array.ksp_engine), getResources().getStringArray(R.array.ksp_isp));
		fuckAdapter = new FuckingMan(getActivity(), groupList, childList, ispDAta);
		referData.setAdapter(fuckAdapter);
		a = b.create();
		refer.setOnClickListener(lisener);
		return view;
	}
	TextWatcher tListener=new TextWatcher(){

		@Override
		public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4)
		{
			// TODO: Implement this method
		}

		@Override
		public void onTextChanged(CharSequence p1, int p2, int p3, int p4)
		{
			// TODO: Implement this method
		}

		@Override
		public void afterTextChanged(Editable p1)
		{cdv();
			// TODO: Implement this method
		}
	};
	private void addData(String group, String[] engine, String[] ispdata)
	{
        groupList.add(group);
        List<String> childitem = new ArrayList<>();
		List<String> ispDATA=new ArrayList<>();
        for (int i = 0; i < engine.length; i++)
		{
            childitem.add(engine[i]);
			ispDATA.add(ispdata[i]);

        }
        childList.add(childitem);
		ispDAta.add(ispDATA);
    }
	Runnable r=new Runnable(){

		@Override
		public void run()
		{
			//h.postDelayed(r, 100);
			
		}
	};
	 void cdv(){
		if (isp.getText().toString().indexOf(".") == 0)
		{
			ti.setErrorEnabled(true);
			ti.setError(getString(R.string.format_error));

		}
		else
		{
			ti.setErrorEnabled(false);
			ti.setError("");
		} 
		if (m.getText().toString().indexOf(".") == 0)
		{
			tm.setErrorEnabled(true);
			tm.setError(getString(R.string.format_error));

		}
		else
		{
			tm.setErrorEnabled(false);
			tm.setError("");
		}
		if (m0.getText().toString().indexOf(".") == 0)
		{
			tm0.setErrorEnabled(true);
			tm0.setError(getString(R.string.format_error));

		}
		else
		{
			tm0.setErrorEnabled(false);
			tm0.setError("");
		} 
		if (g.getText().toString().indexOf(".") == 0)
		{
			tg.setErrorEnabled(true);
			tg.setError(getString(R.string.format_error));
		}
		else
		{
			tg.setErrorEnabled(false);
			tg.setError("");
		}
		if (!(isEmpty(isp) || isEmpty(m) || isEmpty(m0) || isEmpty(g))
			&& !(g.getText().toString().indexOf(".") == 0 ||
			isp.getText().toString().indexOf(".") == 0 ||
			m.getText().toString().indexOf(".") == 0  ||
			m0.getText().toString().indexOf(".") == 0))
		{
			double iisp=Double.valueOf(isp.getText().toString());
			double mm=Double.valueOf(m.getText().toString());
			double mm0=Double.valueOf(m0.getText().toString());
			double gg=Double.valueOf(g.getText().toString());
			double v=iisp * gg * Math.log(mm / (mm0));
			//tv.setText("∆V=" + v);
			tv2.setVisibility(View.GONE);
			 if(ht.getVisibility()==View.GONE){
			 ht.setVisibility(View.VISIBLE);
			 ht.animateText(getText(R.string.input_data));
			 }
			 ht.animateText("∆V=" + v);
		}
	}
	Handler h=new Handler(){

		@Override
		public void handleMessage(Message msg)
		{
			// TODO: Implement this method
			super.handleMessage(msg);
		}
	};
	public boolean isEmpty(EditText edit)
	{
		return TextUtils.isEmpty(edit.getText().toString());
	}
	private View.OnClickListener lisener=new View.OnClickListener(){

		@Override
		public void onClick(View p1)
		{
			a.show();
		}
	};
	class FuckingMan extends BaseExpandableListAdapter
	{
		private List<String> groupList;//外层的数据源
		private List<List<String>> childList;//里层的数据源
		private List<List<String>> ispdata;
		private Context context;

		public FuckingMan(Context context, List<String> groupList, List<List<String>> childList, List<List<String>> ispData)
		{
			this.context = context;
			this.groupList = groupList;
			this.childList = childList;
			this.ispdata = ispData;
		}

		@Override
		public int getGroupCount()
		{
			return groupList.size();
		}
		@Override
		public int getChildrenCount(int groupPosition)
		{
			return childList.get(groupPosition).size();
		}
		public int getGroupispCount(int g)
		{
			// TODO: Implement this method
			return ispdata.get(g).size();
		}

		@Override
		public Object getGroup(int groupPosition)
		{
			return groupPosition;
		}

		@Override
		public Object getChild(int groupPosition, int childPosition)
		{
			return childList.get(groupPosition).get(childPosition);
		}
		public Object getispdata(int gp, int cp)
		{
			return ispdata.get(gp).get(cp);
		}
		@Override
		public long getGroupId(int groupPosition)
		{
			return groupPosition;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition)
		{
			return childPosition;
		}
		public long getispid(int gp, int cp)
		{
			return cp;
		}
		@Override
		public boolean hasStableIds()
		{
			return true;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
		{
			convertView = View.inflate(context, R.layout.item_game, null);
			//分组名字
			TextView textView = (TextView) convertView.findViewById(R.id.group_textview);

			//TextView number = (TextView) convertView.findViewById(R.id.it);
			//number.setText(childList.get(groupPosition).size()+"个");
			textView.setText(groupList.get(groupPosition));
			return convertView;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view00, ViewGroup viewGroup)
		{
			view00 = View.inflate(context, R.layout.item_isp_data, null);
			TextView textView = (TextView) view00.findViewById(R.id.child_name);
			//外层的分组名字
			TextView isp=(TextView)view00.findViewById(R.id.child_model);
			textView.setText(childList.get(groupPosition).get(childPosition));
			isp.setText("Isp=" + ispdata.get(groupPosition).get(childPosition));
			return view00;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition)
		{//isp.setText(ispdata.get(groupPosition).get(childPosition));
		//a.dismiss();
			return true;
		}
		
	}
}
