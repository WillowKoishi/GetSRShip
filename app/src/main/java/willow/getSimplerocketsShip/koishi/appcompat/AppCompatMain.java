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
import android.support.v7.widget.RecyclerView;
import java.util.List;
import willow.getSimplerocketsShip.koishi.view.RecyclerAdapter;
import java.util.ArrayList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.DefaultItemAnimator;
import willow.getSimplerocketsShip.koishi.fragment.FireControl;
import android.widget.Toast;
import android.support.design.widget.AppBarLayout;
import android.view.Menu;
import android.support.v7.widget.SearchView;
import android.support.v4.view.MenuItemCompat;

public class AppCompatMain extends AppCompatActivity
{
	private DrawerLayout drawer;
	private NavigationView NaView;
	
	
	
	private DeltaVCalculater dvc;
	private FireControl fcl;
	
	
	
	private FloatingActionButton fab;
	private Toolbar toolbar;
	private CoordinatorLayout coordinatorLayout;
	private FragmentTransaction fragmentTransaction;
private RecyclerView recyclerview;
	private long startTime;
	private int situation;
	private AppBarLayout appbar;
	
	private List<String> id;
	private List<Boolean> type;
	private RecyclerView.LayoutManager manager;
	private RecyclerAdapter adapter;

	private final static int GETSHIP=0,GETSANDBOX=1;

	private SearchView mSearchView;

	public void hideMe()
	{
		fragmentTransaction=getSupportFragmentManager().beginTransaction();
		fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
		fragmentTransaction.hide(fcl);
		fragmentTransaction.hide(dvc);
		fragmentTransaction.commit();
		coordinatorLayout.setVisibility(View.VISIBLE);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findID();
		situation = GETSHIP;
		initData();initRecy();//仅测试!!!!!!!
		toolbar.setTitle(R.string.input_data);
		toolbar.setBackgroundColor(Color.WHITE);
		//toolbar.setTitleTextColor(Color.GRAY);
		setSupportActionBar(toolbar);
		getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		//setSelectedItemId(NaView.getMenu().getItem(position).getItemId());
		android.support.v7.app.ActionBarDrawerToggle drawerToggle = new android.support.v7.app.ActionBarDrawerToggle(AppCompatMain.this, drawer, toolbar, R.string.whatnew , R.string.about) {
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
		dvc = new DeltaVCalculater();
		fcl=new FireControl();
		fragmentTransaction.add(R.id.main_fragment, dvc);
		fragmentTransaction.add(R.id.main_fragment,fcl);
		fragmentTransaction.hide(dvc);
		fragmentTransaction.hide(fcl);
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

					Toast .makeText(AppCompatMain. this,""+getSupportFragmentManager().getBackStackEntryCount(),1000);
					//menuItem.setChecked(true);
					switch (menuItem.getItemId())
					{
						case R.id.get_ship:
							situation = GETSHIP;
							fragmentTransaction.hide(dvc);
							coordinatorLayout.setVisibility(View.VISIBLE);
							//getSupportFragmentManager().popBackStack();
							fragmentTransaction.hide(fcl);
							break;
						case R.id.get_sandbox:
							situation = GETSANDBOX;
							fragmentTransaction.hide(fcl);
							fragmentTransaction.hide(dvc);
							//getSupportFragmentManager().popBackStack();
							coordinatorLayout.setVisibility(View.VISIBLE);
							break;
						case R.id.old_ui:
							//coordinatorLayout.setVisibility(View.VISIBLE);
							startActivity(new Intent(AppCompatMain.this, MainActivity.class));
							break;
						case R.id.fire_control:
							coordinatorLayout.setVisibility(View.GONE);
							if(fcl.isHidden()){
								fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
								//fragmentTransaction.add(R.id.main_fragment,dvc);
								fragmentTransaction.show(fcl);
								fragmentTransaction.addToBackStack("123");
								fragmentTransaction.hide(dvc);
							}
						
							//startActivity(new Intent(AppCompatMain.this, FireControl.class));
							break;
						case R.id.cal_dv:
							coordinatorLayout.setVisibility(View.GONE);
						  if( dvc.isHidden()){
							fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
							//fragmentTransaction.add(R.id.main_fragment,dvc);
							fragmentTransaction.show(dvc);
							fragmentTransaction.addToBackStack("456");
							  fragmentTransaction.hide(fcl);
							}
					}
					fragmentTransaction.commit();
					drawer.closeDrawers();
					return true;
				}
			});
	}
	private void  findID()
	{
		toolbar = (Toolbar)this.findViewById(R.id.main_toolbar);
		drawer = (DrawerLayout)this.findViewById(R.id.drawer_layout);
		NaView = (NavigationView)this.findViewById(R.id.id_nv_menu);
		fab = (FloatingActionButton)this.findViewById(R.id.fab_delete);
		coordinatorLayout = (CoordinatorLayout)this.findViewById(R.id.appcompatmainCoordinatorLayout1);
		recyclerview=(RecyclerView)this.findViewById(R.id.main_recyclerView1);
		appbar=(AppBarLayout)this.findViewById(R.id.activitymainAppBarLayout1);
	}

	@Override  
	public void onBackPressed()
	{  
		if (drawer.isDrawerVisible(Gravity.LEFT))
		{
			drawer.closeDrawer(Gravity.LEFT);
		}
		else
		{
			//if(fragmentTransaction.){}
			//else{
				//Toast .makeText(this,""+getSupportFragmentManager().getBackStackEntryCount(),1000).show();
			if (getSupportFragmentManager().getBackStackEntryCount() == 0||(fcl.isHidden()&&dvc.isHidden()))
			{
				
				long currentTime = System.currentTimeMillis();  
				if ((currentTime - startTime) >= 2000)
				{    
					startTime = currentTime;  
					Snackbar sb=new Snackbar();
					sb.make(fab, R.string.back_again, Snackbar.LENGTH_SHORT).show();
				}
				else
				{  
					finish();  
				}  
			}
			else
			{
				hideMe();

			}
		}
	}
	//}
	public void onClickFab(View v)
	{
		Snackbar.make(v, "确定删除所有历史记录吗", Snackbar.LENGTH_SHORT).show();
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_searchview, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        //通过MenuItem得到SearchView
        mSearchView=(SearchView)MenuItemCompat.getActionView(searchItem);

        //设置最大宽度
//        mSearchView.setMaxWidth();
        //设置是否显示搜索框展开时的提交按钮
      //  mSearchView.setSubmitButtonEnabled(true);
        //设置输入框提示语
        mSearchView.setQueryHint("hint");
        //SearchView设置监听
       // setMenuListener();


        return super.onCreateOptionsMenu(menu);
    }
	
	/*-------------仅测试!!!!---------------*/
	public void initData(){//just TEST!!!!
		id=new ArrayList<String>();
		type=new ArrayList<Boolean>();
		for(int i=1;i<50;i++){
			id.add(Integer.toString(i+100000));
			double b=(Math.random());
			if(b>0.5){
				type.add(true);
			}else{
				type. add(false);
			}
		}
}
	public void initRecy(){
		manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
		adapter=new RecyclerAdapter(this,id,type);
		recyclerview.setItemAnimator(new DefaultItemAnimator());
		recyclerview.setLayoutManager(manager);
		//adapter.setHasStableIds(true);
		recyclerview.setAdapter(adapter);
	}
	/*-------------仅测试!!!!!!!---------------*/
}
