package fragments;


import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.widget.ListView;

import com.client.maks.client.R;

import friendsAdapter.MyFriendsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsFragment extends Fragment {
    ListView listView;
    public static Integer[] friendImages = {R.drawable.friend1, R.drawable.friend1, R.drawable.friend1, R.drawable.friend1};
    public static String[] friendNames = {"Никита","Никита", "Никита", "Никита"};

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
        MyFriendsAdapter adapter = new MyFriendsAdapter(getActivity(), friendNames, friendImages);
        listView = (ListView) getView().findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}
