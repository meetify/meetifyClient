package friendsAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.client.maks.client.R;

/**
 * Created by Egor on 25.09.2016.
 */
public class MyFriendsAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] friendNames;
    private  final Integer[] friendImages;

    public MyFriendsAdapter(Context context, String[] friendNames, Integer[] friendImages) {
        super(context, R.layout.activity_friends_item_fragment, friendNames);
        this.context = context;
        this.friendNames = friendNames;
        this.friendImages = friendImages;
    }



    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_friends_item_fragment, null, true);
        TextView name = (TextView)rowView.findViewById(R.id.friendName);
        ImageView image = (ImageView)rowView.findViewById(R.id.imageFriend);
        name.setText(friendNames[position]);
        image.setImageResource(friendImages[position]);
        return rowView;
    }
}
