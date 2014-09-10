package ph.edu.ust.jakearroyo.soccr101.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import ph.edu.ust.jakearroyo.soccr101.R;
import ph.edu.ust.jakearroyo.soccr101.db.Org;

public class ListViewAdapter extends BaseAdapter {

    private List<Org> mData;
    private  Context mContext;
    private static LayoutInflater sInflater = null;

    public  ListViewAdapter(Context context, List<Org> data){
        this.mContext = context;
        this.mData = data;
        sInflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount(){
        return mData.size();
    }

    @Override
    public Object getItem(int position){
        return mData.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        return getView(convertView, mData.get(position));
    }

    public View getView(View convertView, Org item) {
        ViewHolder holder;
        if (convertView == null)convertView = sInflater.inflate(R.layout.listview_item, null);
        if (convertView != null) {
            holder = new ViewHolder();
            holder.logo = (CircleImageView) convertView.findViewById(R.id.item_icon);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.id = (TextView) convertView.findViewById(R.id.content_id);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.logo.setImageDrawable(getLogo(item));
        holder.name.setText(item.getName());
        holder.id.setText(item.getId().toString());
        return convertView;
    }

    private Drawable getLogo(Org item){
        Drawable d = null;
        try{
            // get input stream
            InputStream ims = mContext.getApplicationContext().getAssets().open("logo/" + item.getId() + ".png");
            // load image as Drawable
            d = Drawable.createFromStream(ims, null);
        }catch(IOException e){
            Log.e("IOException", e.toString());
        }
        if(d == null){
            try{
                // get input stream
                InputStream ims = mContext.getApplicationContext().getAssets().open("missing.png");
                // load image as Drawable
                d = Drawable.createFromStream(ims, null);
            }catch(IOException e){
                Log.e("IOException", e.toString());
            }
        }
        return d;
    }

    static class ViewHolder {
        CircleImageView logo;
        TextView name;
        TextView id;
    }
}
