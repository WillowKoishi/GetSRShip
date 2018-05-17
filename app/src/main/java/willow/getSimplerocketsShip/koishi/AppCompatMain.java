package willow.getSimplerocketsShip.koishi;
import android.support.v7.app.*;
import android.support.v4.widget.*;
import android.support.design.widget.*;
import willow.getSimplerocketsShip.koishi.fragment.*;
import android.support.v7.widget.*;
import android.support.v4.app.*;
import android.os.*;
import android.view.*;
import android.content.*;
import willow.getSimplerocketsShip.koishi.appcompat.*;


public class AppCompatMain extends AppCompatActivity
{
	private DrawerLayout drawer;
	private NavigationView NaView;
	private AppCompatGetShip acgs;
	private FloatingActionButton fab;
	private Toolbar toolbar;
	private CoordinatorLayout coordinatorLayout;
	private FragmentTransaction fragmentTransaction;

	private long startTime;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.appcompat_main);
		drawer=(DrawerLayout)this.findViewById(R.id.drawer_layout);
		NaView=(NavigationView)this.findViewById(R.id.id_nv_menu);
		fab=(FloatingActionButton)this.findViewById(R.id.fab_delete);
		coordinatorLayout=(CoordinatorLayout)this.findViewById(R.id.appcompatmainCoordinatorLayout1);
		toolbar=(Toolbar)this.findViewById(R.id.toolbar);
		toolbar.setTitle(R.string.appname);
		toolbar.setPopupTheme(R.style.ThemeOverlay_AppCompat_Light);
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
		acgs=new AppCompatGetShip();
		fragmentTransaction.add(R.id.main_fragment,acgs);
		fragmentTransaction.commit();
	}
	private void setupDrawerContent(NavigationView navigationView)
    {
        navigationView.setNavigationItemSelectedListener(

			new NavigationView.OnNavigationItemSelectedListener()
			{

				@Override
				public boolean onNavigationItemSelected(MenuItem menuItem)
				{
					menuItem.setChecked(true);
					switch(menuItem.getItemId()){
						case R.id.old_ui:
							startActivity(new Intent(AppCompatMain.this,MainActivity.class));
							break;
						case R.id.fire_control:
							startActivity(new Intent(AppCompatMain.this,FireControl.class));
							break;
					}
					drawer.closeDrawers();
					return true;
				}
			});
    }


	@Override  
	public void onBackPressed() {  

		long currentTime = System.currentTimeMillis();  
		if ((currentTime - startTime) >= 2000) {    
			startTime = currentTime;  
			Snackbar sb=new Snackbar();
			sb.make(fab, R.string.back_again, Snackbar.LENGTH_SHORT).show();
		} else {  
			finish();  
		}  
	}  
	public void onClickFab(View v){
		Snackbar.make(v, "确定删除所有历史记录吗", Snackbar.LENGTH_SHORT).show();
	}
}
