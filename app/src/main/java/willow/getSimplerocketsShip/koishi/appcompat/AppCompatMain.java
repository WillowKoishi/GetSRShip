package willow.getSimplerocketsShip.koishi.appcompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import willow.getSimplerocketsShip.koishi.R;
import android.support.v7.widget.Toolbar;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import willow.getSimplerocketsShip.koishi.fragment.DeltaVCalculater;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentTransaction;
import willow.getSimplerocketsShip.koishi.MainActivity;
import android.view.Gravity;

public class AppCompatMain extends AppCompatActivity
{
	private DrawerLayout drawer;
	private NavigationView NaView;
	private DeltaVCalculater dvc;
	private FloatingActionButton fab;
	private Toolbar toolbar;
	private CoordinatorLayout coordinatorLayout;
	private FragmentTransaction fragmentTransaction;

	private long startTime;
	private int situation;
	
	private final static int GETSHIP=0,GETSANDBOX=1;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findID();
		situation=GETSHIP;
		toolbar.setTitle(R.string.input_data);
		toolbar.setBackgroundColor(Color.WHITE);
		//toolbar.setTitleTextColor(Color.GRAY);
		setSupportActionBar(toolbar);
		getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		//setSelectedItemId(NaView.getMenu().getItem(position).getItemId());
		android.support.v7.app.ActionBarDrawerToggle drawerToggle = new android.support.v7.app.ActionBarDrawerToggle(AppCompatMain.this,drawer,toolbar, R.string.whatnew , R.string.about) {
			@Override
			public void onDrawerOpened(View drawerView)
			{
				super.onDrawerOpened(drawer);
			}

			@Override
			public void onDrawerClosed(View drawerView)
			{
				super.onDrawerClosed(drawerView);
			}
		};
		drawerToggle.syncState();
        //绑定监听器
        drawer.setDrawerListener(drawerToggle);
		setupDrawerContent(NaView);
		FragmentManager fm=getSupportFragmentManager();
		fragmentTransaction = fm.beginTransaction();
		dvc=new DeltaVCalculater();
		fragmentTransaction.add(R.id.main_fragment,dvc);
		fragmentTransaction.hide(dvc);
		fragmentTransaction.commit();
	}
	private void setupDrawerContent(NavigationView navigationView)
    {
        navigationView.setNavigationItemSelectedListener(

			new NavigationView.OnNavigationItemSelectedListener()
			{

				@Override
				public boolean onNavigationItemSelected(MenuItem menuItem)
				{FragmentManager fm=getSupportFragmentManager();
					fragmentTransaction = fm.beginTransaction();
					fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
					fragmentTransaction.hide(dvc);
					coordinatorLayout.setVisibility(View.GONE);
					//menuItem.setChecked(true);
					switch(menuItem.getItemId()){
						case R.id.get_ship:
							situation=GETSHIP;
							coordinatorLayout.setVisibility(View.VISIBLE);
							break;
						case R.id.get_sandbox:
							situation=GETSANDBOX;
							coordinatorLayout.setVisibility(View.VISIBLE);
							break;
						case R.id.old_ui:
							coordinatorLayout.setVisibility(View.VISIBLE);
							startActivity(new Intent(AppCompatMain.this,MainActivity.class));
							break;
						case R.id.fire_control:
							startActivity(new Intent(AppCompatMain.this,FireControl.class));
							break;
						case R.id.cal_dv:
							fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
							//fragmentTransaction.add(R.id.main_fragment,dvc);
							fragmentTransaction.show(dvc);
					}
					fragmentTransaction.commit();
					drawer.closeDrawers();
					return true;
				}
			});
	}
	private void  findID(){
		toolbar=(Toolbar)this.findViewById(R.id.main_toolbar);
		drawer=(DrawerLayout)this.findViewById(R.id.drawer_layout);
		NaView=(NavigationView)this.findViewById(R.id.id_nv_menu);
		fab=(FloatingActionButton)this.findViewById(R.id.fab_delete);
		coordinatorLayout=(CoordinatorLayout)this.findViewById(R.id.appcompatmainCoordinatorLayout1);
	}

	@Override  
	public void onBackPressed() {  
if(drawer.isDrawerVisible(Gravity.LEFT)){
drawer.closeDrawer(Gravity.LEFT);
}
	else{
		//if(fragmentTransaction.){}
		//else{
		long currentTime = System.currentTimeMillis();  
		if ((currentTime - startTime) >= 2000) {    
			startTime = currentTime;  
			Snackbar sb=new Snackbar();
			sb.make(fab, R.string.back_again, Snackbar.LENGTH_SHORT).show();
		} else {  
			finish();  
		}  
	} 
	}
	//}
	public void onClickFab(View v){
		Snackbar.make(v, "确定删除所有历史记录吗", Snackbar.LENGTH_SHORT).show();
	}

	
}
