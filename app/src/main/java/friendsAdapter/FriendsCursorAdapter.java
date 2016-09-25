package friendsAdapter;

import android.app.Activity;
import android.content.Context;
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
        private List<Friend> friends;
        private LayoutInflater layoutInflater;

        public FriendsCursorAdapter(List<Friend> friends, Activity activity) {
            this.friends = friends;
            this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            image.setImageResource(friend.getImage());
            return contentView;
        }
    }

