package willow.getSimplerocketsShip.koishi.fragment;
import android.support.v4.app.*;
import android.view.*;
import android.os.*;
import willow.getSimplerocketsShip.koishi.*;
import android.support.v7.widget.*;

public class AppCompatGetShip extends Fragment
{
private SearchView searchView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{View view=inflater.inflate(R.layout.fragment_app_get, container, false);
	searchView=(SearchView)view.findViewById(R.id.fragmentappgetSearchView1);
		searchView.setSubmitButtonEnabled(true);//设置最右侧的提交按钮
		//getActivity().setActionBar(toolbar);
		return view;
	}
	
}
