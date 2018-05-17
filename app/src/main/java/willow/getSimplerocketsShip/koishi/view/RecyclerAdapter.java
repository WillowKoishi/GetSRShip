package willow.getSimplerocketsShip.koishi.view;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import willow.getSimplerocketsShip.koishi.util.*;
import android.view.*;
import android.content.*;
import java.util.*;
import willow.getSimplerocketsShip.koishi.*;
import android.widget.*;
import android.graphics.drawable.*;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.eiewHolder>
{
	private Context mContext;
	private List<String> mID;
	//private List<Long> mHistory;
	private List<Boolean> mType;
public RecyclerAdapter(Context context,List<String> id,List<Boolean> type){
	mContext=context;
	//mHistory=history;
	mID=id;
	mType=type;
}
	public void updata(List<String> id,List<Boolean> type){
		//mHistory=history;
		mID=id;
		mType=type;
		notifyDataSetChanged();
	}
	@Override
	public eiewHolder onCreateViewHolder(ViewGroup p1, int p2)
	{
		View item=LayoutInflater.from(p1.getContext()).inflate(R.layout.item_history,p1,false);
		eiewHolder viewHolder=new eiewHolder(item);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(eiewHolder p1, int p2)
	{if(mType.get(p2)){
		p1.type.setImageResource(R.drawable.image_ship);
			p1.name.setText(R.string.ship);
	}else{
		p1.type.setImageResource(R.drawable.ic_lang);
		p1.name.setText(R.string.sandbox);
	}
	p1.id.setText("ID="+mID.get(p2));
	}

	@Override
	public int getItemCount()
	{
		// TODO: Implement this method
		return mID==null?0:mID.size();
	}
	
    public static class eiewHolder extends RecyclerView.ViewHolder {

        ImageView type;
		TextView name,id;
		ImageButton delete;

        public eiewHolder(View itemView) {
            super(itemView);
            type=(ImageView)itemView.findViewById(R.id.itemhistoryImageView1);
			name=(TextView)itemView.findViewById(R.id.itemhistory_type);
			id=(TextView)itemView.findViewById(R.id.itemhistory_id);
			delete=(ImageButton)itemView.findViewById(R.id.itemhistory_delete);
        }
    }
	
	
	
}
