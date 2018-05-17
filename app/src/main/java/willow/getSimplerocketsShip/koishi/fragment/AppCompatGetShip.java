package willow.getSimplerocketsShip.koishi.fragment;
import android.support.v4.app.*;
import android.view.*;
import android.os.*;
import willow.getSimplerocketsShip.koishi.*;
import android.support.v7.widget.*;
import java.util.*;
import willow.getSimplerocketsShip.koishi.view.*;
import android.view.View.*;
import android.widget.Button;
import android.support.design.widget.*;

public class AppCompatGetShip extends Fragment
{
private SearchView searchView;
private RecyclerView recyclerview;
private List<String> id;
private List<Boolean> type;
private RecyclerView.LayoutManager manager;
private RecyclerAdapter adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{View view=inflater.inflate(R.layout.fragment_app_get, container, false);
	searchView=(SearchView)view.findViewById(R.id.fragmentappgetSearchView1);
	recyclerview=(RecyclerView)view.findViewById(R.id.fragmentappgetRecyclerView1);
	Button b=(Button)view.findViewById(R.id.fragmentappgetButton1);
		b.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{type.remove(0);
					id.remove(0);
					adapter.updata(id,type);
					// TODO: Implement this method
				}
			});
		searchView.setSubmitButtonEnabled(true);//设置最右侧的提交按钮
		searchView.setQueryHint(getText(R.string.hint_getship));
		searchView.setIconifiedByDefault(false);
		initData();
		initRecy();
		//searchView.setIconified(
		//getActivity().setActionBar(toolbar);
		return view;
	}
	public void initRecy(){
		manager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
		adapter=new RecyclerAdapter(getActivity(),id,type);
		recyclerview.setItemAnimator(new DefaultItemAnimator());
		recyclerview.setLayoutManager(manager);
		adapter.setHasStableIds(true);
		recyclerview.setAdapter(adapter);
	}
	public void initData(){
		id=new ArrayList<String>();
		type=new ArrayList<Boolean>();
		for(int i=1;i<50;i++){
			id.add(Integer.toString( i+100000));
			double b=(Math.random());
			if(b>0.5){
			type.add(true);
			}else{
				type.add(false);
			}
		}
		
	}
}
