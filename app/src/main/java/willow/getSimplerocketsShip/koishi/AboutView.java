package willow.getSimplerocketsShip.koishi;
import android.support.v7.app.*;
import android.os.*;
import android.support.v7.widget.*;
import android.view.View.*;
import android.view.*;

public class AboutView extends AppCompatActivity
{
Toolbar toolbar;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		toolbar=(Toolbar)this.findViewById(R.id.toolbar);
		toolbar.setTitle(R.string.about);
		setSupportActionBar(toolbar);
		getSupportActionBar().setHomeButtonEnabled(true); 
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		toolbar.setNavigationOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					finish();
				}
			});
	}
	
}
