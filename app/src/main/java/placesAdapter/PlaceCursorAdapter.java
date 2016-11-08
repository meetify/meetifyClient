package placesAdapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.client.maks.client.Place;
import com.client.maks.client.R;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * Created by Egor on 07.11.2016.
 */

public class PlaceCursorAdapter extends BaseAdapter {
    private Bitmap bitmap;
    private List<Place> places;
    private LayoutInflater layoutInflater;
    public PlaceCursorAdapter(List<Place> places, Activity activity) {
        this.places = places;
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return places.size();
    }

    @Override
    public Object getItem(int i) {
        return places.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void deleteFriend(int position) {
        this.places.remove(position);
        notifyDataSetChanged();
    }

    public void addFriend(Place place) {
        places.add(place);
        notifyDataSetChanged();
    }
    public View getView(int position, View contentView, ViewGroup viewGroup) {
        contentView = layoutInflater.inflate(R.layout.activity_friends_item_fragment, null, true);
        TextView name = (TextView)contentView.findViewById(R.id.friendName);
        ImageView image = (ImageView)contentView.findViewById(R.id.imageFriend);
        Place place = (Place) getItem(position);
        name.setText(place.getName());
        ByteArrayInputStream inputStream = new ByteArrayInputStream(place.getImage());
        bitmap = BitmapFactory.decodeStream(inputStream);


        image.setImageBitmap(bitmap);
        return contentView;
    }
}
