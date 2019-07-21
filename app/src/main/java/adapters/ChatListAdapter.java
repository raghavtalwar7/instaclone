package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.raghav.instaclone.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import models.ChatModel;
import utils.UniversalImageLoader;

public class ChatListAdapter extends BaseAdapter {

    private ArrayList<ChatModel> arrayList;
    private Context context;
    private Context mContext;

    public ChatListAdapter(ArrayList<ChatModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
// UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_contacts)
                .showImageOnFail(R.drawable.ic_contacts)
                .showImageOnLoading(R.drawable.ic_contacts)
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
        // END - UNIVERSAL IMAGE LOADER SETUP
    }

    @Override
    public int getCount() {
        return arrayList != null ? arrayList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return arrayList != null ? arrayList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.chat_list_item, null);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.text1);
        tvName.setText(arrayList.get(position).getName());

/*
        TextView status = (TextView) convertView.findViewById(R.id.online_offline);
        status.setText(arrayList.get(position).getState());
*/

        CircleImageView displayphoto = (CircleImageView) convertView.findViewById(R.id.display_image);
        UniversalImageLoader.setImage("",displayphoto,null,"");

        return convertView;
    }
}