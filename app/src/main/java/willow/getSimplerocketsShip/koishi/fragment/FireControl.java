package willow.getSimplerocketsShip.koishi.fragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.View;
import willow.getSimplerocketsShip.koishi.R;
import android.support.v7.widget.Toolbar;
import willow.getSimplerocketsShip.koishi.appcompat.AppCompatMain;
import android.view.View.*;

public class FireControl extends Fragment
{
	View view;
AppCompatMain main;
	private Toolbar toolbar;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{   view=inflater.inflate(R.layout.fragment_firecontrol, container, false);
		toolbar=(Toolbar)view.findViewById(R.id.toolbar);
		toolbar.setTitle(R.string.item_FC);
		main=(AppCompatMain)getActivity();
		main.setSupportActionBar(toolbar);
		main.getSupportActionBar().setHomeButtonEnabled(true);
		main.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		toolbar.setNavigationOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					main.hideMe();
				}
			});
		return view;
	}
	
}
