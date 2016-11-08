package fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.widget.ListView;
import com.client.maks.client.Friend;
import com.client.maks.client.R;
import database.service.FriendService;
import friendsAdapter.FriendsCursorAdapter;

public class FriendsFragment extends Fragment {
    FriendsCursorAdapter myAdapter;
    public FriendsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friends, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (new FriendService(getActivity()).getAll().size() == 0) {
            new FriendService(getActivity()).save(new Friend("Аня", R.drawable.friend));

            new FriendService(getActivity()).save(new Friend("Никита", R.drawable.friend1));
            new FriendService(getActivity()).save(new Friend("Максим", R.drawable.friend2));
            new FriendService(getActivity()).save(new Friend("Дима", R.drawable.friend3));
        }
        ListView listView = (ListView) getView().findViewById(R.id.listView);
        myAdapter = new FriendsCursorAdapter(new FriendService(getActivity()).getAll(), getActivity());
        listView.setAdapter(myAdapter);
        //Friend friend = getActivity().getIntent().getParcelableExtra("user");
        //if (friend != null) {
         //   myAdapter.addFriend(friend);
        //}
    }
}
