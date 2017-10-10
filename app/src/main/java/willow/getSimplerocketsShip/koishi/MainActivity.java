package willow.getSimplerocketsShip.koishi;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;
import android.content.SharedPreferences;
import java.util.List;
import android.content.pm.ResolveInfo;
import java.util.ArrayList;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import android.widget.*;
import android.view.*;
import android.support.v7.app.AlertDialog;

public class MainActivity extends AppCompatActivity
{private EditText et,et2;
private Toolbar toolbar;
private AlertDialog ad;
private SharedPreferences sp;
private RelativeLayout ship,box;
	private List<ResolveInfo> apps = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {    super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		sp= getSharedPreferences("1.1", MODE_PRIVATE);
		if(sp.getBoolean("version",true)){
			sp.edit().putBoolean("version",false).commit();
			AlertDialog.Builder builder=new AlertDialog.Builder(this);
			builder.setTitle("更新内容");
			builder.setMessage("版本1.1v\n·修改了应用风格\n·增加了沙盘下载功能\n感谢Kally的技术滋瓷");
			builder.setNegativeButton("取消也没有用", null);
			builder.setPositiveButton("确定", null);
			builder.show();
	}
		et=(EditText)this.findViewById(R.id.mainEditText1);
		et2=(EditText)this.findViewById(R.id.mainEditText2);
		toolbar=(Toolbar)this.findViewById(R.id.toolbar);
		ship=(RelativeLayout)this.findViewById(R.id.ship);
		box=(RelativeLayout)this.findViewById(R.id.sandbox);
		toolbar.setTitleTextColor(0xffffffff);
		toolbar.setTitle("SR下载器");  //设置Title
		//toolbar.setPopupTheme(R.style.theme);
		//toolbar.setSubtitle("subTitle");    //设置subTitle
		//toolbar.setNavigationIcon(R.mipmap.ic_search);  // 设置导航栏图标
		//toolbar.setLogo(R.mipmap.ic_favorite);      // 设置Logo
		setSupportActionBar(toolbar);  
    }
	public void get(View view){
		if(loadApps()){
		//jundroo.com/ViewSandbox.html?id=647418
			//startActivity(new Intent().setAction(Intent.ACTION_VIEW).setData(Uri.parse("simplerockets://03"+et.getText())));
			
			startActivity(new Intent().setAction(Intent.ACTION_VIEW).setData(Uri.parse("simplerockets://00"+et.getText())));
	}
	else{
		DBToast.showToast(getBaseContext(), "你还没有安装SimpleRockets!", 3000);
	}
	}
	public void ship(View view){
ship.setVisibility(View.VISIBLE);
box.setVisibility(View.GONE);
	}
	public void sandbox(View view){
		ship.setVisibility(View.GONE);
		box.setVisibility(View.VISIBLE);
	}
	public void get2(View view){
		if(loadApps()){
			//jundroo.com/ViewSandbox.html?id=647418
			//startActivity(new Intent().setAction(Intent.ACTION_VIEW).setData(Uri.parse("simplerockets://03"+et.getText())));
			startActivity(new Intent().setAction(Intent.ACTION_VIEW).setData(Uri.parse("simplerockets://03"+et2.getText())));
		}
		else{
			DBToast.showToast(getBaseContext(), "你还没有安装SimpleRockets!", 3000);
		}
		}
	private boolean loadApps() {
		boolean sr=false;
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        apps = getPackageManager().queryIntentActivities(intent, 0);
        for (int i = 0; i < apps.size(); i++) {
            ResolveInfo info = apps.get(i);
            String packageName = info.activityInfo.packageName;
            CharSequence cls = info.activityInfo.name;
            CharSequence name = info.activityInfo.loadLabel(getPackageManager());
			if(packageName.indexOf("com.jundroo.simplerockets")!=-1){
				sr=true;
			}
            //Log.e("ddddddd",name+"----"+packageName+"----"+cls);
        }
		return sr;
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
				builder.setTitle("更新内容");
				builder.setMessage("版本v1.1\n·修改了应用风格\n·增加了沙盘下载功能\n感谢Kally的技术滋瓷");
				builder.setNegativeButton("取消也没有用", null);
				builder.setPositiveButton("确定", null);
				builder.show();
				//Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
