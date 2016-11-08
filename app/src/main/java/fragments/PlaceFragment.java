package fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.client.maks.client.R;

import placeDataBase.pservice.PlaceService;
import placesAdapter.PlaceCursorAdapter;


public class PlaceFragment extends Fragment {
    PlaceCursorAdapter myAdapter;


    public PlaceFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_place, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        ListView listView = (ListView) getView().findViewById(R.id.placelistView);
        myAdapter = new PlaceCursorAdapter(new PlaceService(getActivity()).getAll(), getActivity());
        listView.setAdapter(myAdapter);
    }
}
