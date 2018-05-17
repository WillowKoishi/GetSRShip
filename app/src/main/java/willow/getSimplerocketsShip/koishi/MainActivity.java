package willow.getSimplerocketsShip.koishi;

import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.AlertDialog;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.ListView;
import android.support.v4.app.*;
import willow.getSimplerocketsShip.koishi.fragment.*;
import java.util.*;
import android.content.pm.*;
import android.content.*;
import android.widget.*;
import android.view.*;
import android.view.inputmethod.*;
public class MainActivity extends AppCompatActivity 
{private Toolbar toolbar;
	private DrawerLayout dl;
	private ListView ls;
	private boolean canOpenSR;
	private GetSRShip ef1;
	private GetSandbox ef2;

	private FragmentTransaction transaction;

	private DeltaVCalculater ef3;
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		canOpenSR = loadApps();
		dl = (DrawerLayout)this.findViewById(R.id.drawer_layout);
		toolbar = (Toolbar)this.findViewById(R.id.toolbar);
		ls = (ListView)this.findViewById(R.id.left_drawer);
		toolbar.setTitle(R.string.appname2);
		toolbar.setPopupTheme(R.style.ThemeOverlay_AppCompat_Light);
		setSupportActionBar(toolbar);
		getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, dl, toolbar, R.string.whatnew , R.string.about) {
			@Override
			public void onDrawerOpened(View drawerView)
			{
				super.onDrawerOpened(dl);
			}

			@Override
			public void onDrawerClosed(View drawerView)
			{
				super.onDrawerClosed(drawerView);
			}
		};
		drawerToggle.syncState();
        //绑定监听器
        dl.setDrawerListener(drawerToggle);
		dl.openDrawer(Gravity.LEFT);//push draw
		FragmentManager fm=getSupportFragmentManager();
		 transaction = fm.beginTransaction();
		 transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ef1=new GetSRShip(); /* * add是将一个fragment实例添加到Activity的最上层 * replace替换containerViewId中的fragment实例， * 注意，它首先把containerViewId中所有fragment删除，然后再add进去当前的fragment * */
		ef1.pushSR(canOpenSR);
		ef2=new GetSandbox();
		ef2.pushSR(canOpenSR);
		ef3=new DeltaVCalculater();
		if(savedInstanceState==null){
		transaction.add(R.id.main_fragment, ef1);
		transaction.add(R.id.main_fragment,ef2);
		transaction.add(R.id.main_fragment,ef3);
		}
		transaction.hide(ef2);
		transaction.hide(ef3);
		transaction.commit();	
		ls = (ListView)this.findViewById(R.id.left_drawer);
		String[] commoFunList = new String[]{
			this.getString(R.string.item_getship)
			,this.getString(R.string.item_getsandbox)
			,this.getString(R.string.item_DV)};
		ls.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
											   commoFunList));   
		ls.setOnItemClickListener(new AdapterView.OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
				{FragmentManager fm=getSupportFragmentManager();
					transaction = fm.beginTransaction();
					transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
					transaction.hide(ef1);
					transaction.hide(ef2);
					transaction.hide(ef3);
					switch((int)(p4)){
						case 0:
							//transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
							transaction.show(ef1);
							//transaction.commit();
							break;
						case 1:
							//transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
							transaction.show(ef2);
							//transaction.commit();
							break;
						case 2:
							//transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
							transaction.show(ef3);
							break;
					}
					transaction.commit();
					dl.closeDrawer(Gravity.LEFT);
				}
			});
//		InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//		inputMethodManager.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken()
//												   ,InputMethodManager.HIDE_NOT_ALWAYS);
//		
			}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{getMenuInflater().inflate(R.menu.menu1, menu);
		// TODO: Implement this method
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.toolbar_about:
//				AlertDialog.Builder builder=new AlertDialog.Builder(this);
//				builder.setTitle(R.string.about);
//				builder.setMessage(R.string.new_);
//				builder.setNegativeButton("取消也没有用", null);
//				builder.setPositiveButton("确定", null);
//				builder.show();
//				//Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
				startActivity(new Intent(this,AboutView.class));
				break;
			case R.id.toolbar_exit:
				finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	private List<ResolveInfo> apps = new ArrayList<>();
	private boolean loadApps()
	{
		boolean sr=false;
		Intent intent = new Intent(Intent.ACTION_MAIN, null);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		apps = getPackageManager().queryIntentActivities(intent, 0);
		for (int i = 0; i < apps.size(); i++)
		{
			ResolveInfo info = apps.get(i);
			String packageName = info.activityInfo.packageName;
			//CharSequence cls = info.activityInfo.name;
			//CharSequence name = info.activityInfo.loadLabel(getPackageManager());
			if (packageName.indexOf("com.jundroo.simplerockets") != -1)
			{
				sr = true;
			}
			//Log.e("ddddddd",name+"----"+packageName+"----"+cls);
		}
		return sr;
	}
}
