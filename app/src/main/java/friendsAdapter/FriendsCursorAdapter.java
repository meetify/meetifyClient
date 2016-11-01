package friendsAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.client.maks.client.Friend;
import com.client.maks.client.R;

import java.util.List;

/**
 * Created by Maks on 25.09.2016.
 */
public class FriendsCursorAdapter extends BaseAdapter{
    Bitmap bitmap;
    Context context;
    private List<Friend> friends;
    private LayoutInflater layoutInflater;

    public FriendsCursorAdapter(List<Friend> friends, Activity activity) {
        this.friends = friends;
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = activity;
    }

    @Override
    public int getCount() {
        return friends.size();
    }

    @Override
    public Object getItem(int i) {
        return friends.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void deleteFriend(int position) {
        this.friends.remove(position);
        notifyDataSetChanged();
    }

    public void addFriend(Friend friend) {
        friends.add(friend);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View contentView, ViewGroup viewGroup) {
        contentView = layoutInflater.inflate(R.layout.activity_friends_item_fragment, null, true);
        TextView name = (TextView)contentView.findViewById(R.id.friendName);
        ImageView image = (ImageView)contentView.findViewById(R.id.imageFriend);
        Friend friend = (Friend) getItem(position);
        name.setText(friend.getName());
        bitmap = BitmapFactory.decodeResource(context.getResources(), friend.getImage());
        bitmap = getCircleMaskedBitmap(bitmap, 75);

        image.setImageBitmap(bitmap);
        return contentView;
    }










    public static Bitmap getCircleMaskedBitmap(Bitmap source, int radius)
    {
        if (source == null)
        {
            return null;
        }

        int diam = radius << 1;
        Bitmap scaledBitmap = scaleTo(source, diam);

        Bitmap targetBitmap = Bitmap.createBitmap(diam, diam, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);

        final int color = 0xff424242;
        final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        final Rect rect = new Rect(0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight());

        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);

        canvas.drawCircle(radius, radius, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(scaledBitmap, rect, rect, paint);
        return targetBitmap;
    }







    public static Bitmap scaleTo(Bitmap source, int size)
    {
        int destWidth = source.getWidth();

        int destHeight = source.getHeight();

        destHeight = destHeight * size / destWidth;
        destWidth = size;

        if (destHeight < size)
        {
            destWidth = destWidth * size / destHeight;
            destHeight = size;
        }

        Bitmap destBitmap = Bitmap.createBitmap(destWidth, destHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(destBitmap);
        canvas.drawBitmap(source, new Rect(0, 0, source.getWidth(), source.getHeight()), new Rect(0, 0, destWidth, destHeight), new Paint(Paint.ANTI_ALIAS_FLAG));
        return destBitmap;
    }



}

