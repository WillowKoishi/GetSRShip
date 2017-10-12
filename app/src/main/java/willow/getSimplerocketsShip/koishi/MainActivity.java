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

public class MainActivity extends AppCompatActivity 
{private Toolbar toolbar;
private DrawerLayout dl;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		dl=(DrawerLayout)this.findViewById(R.id.drawer_layout);
		toolbar=(Toolbar)this.findViewById(R.id.toolbar);
		toolbar.setTitle(R.string.appname);
		toolbar.setPopupTheme(R.style.ThemeOverlay_AppCompat_Light);
		setSupportActionBar(toolbar);
		getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
    
	ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, dl, toolbar,R.string.whatnew , R.string.about) {
		@Override
		public void onDrawerOpened(View drawerView) {
			super.onDrawerOpened(dl);
		}

		@Override
		public void onDrawerClosed(View drawerView) {
			super.onDrawerClosed(drawerView);
		}
	};
		drawerToggle.syncState();
        //绑定监听器
        dl.setDrawerListener(drawerToggle);
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{getMenuInflater().inflate(R.menu.menu1, menu);
		// TODO: Implement this method
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.toolbar_about:
				AlertDialog.Builder builder=new AlertDialog.Builder(this);
				builder.setTitle(R.string.about);
				builder.setMessage(R.string.new_);
				builder.setNegativeButton("取消也没有用", null);
				builder.setPositiveButton("确定", null);
				builder.show();
				//Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
