package fragments;


import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.client.maks.client.R;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerViewOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import java.io.IOException;

//import android.support.v4.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class GMapFragment extends Fragment {
    Bitmap bitmap;
    static final int GALLERY_REQUEST = 1;
    MapView mapView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mapView = (MapView) getActivity().findViewById(R.id.mapview);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final MapboxMap mapboxMap) {

                mapboxMap.setOnMarkerClickListener(new MapboxMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(@NonNull Marker marker) {

                        Toast.makeText(getActivity(), marker.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                mapboxMap.setOnMapLongClickListener(new MapboxMap.OnMapLongClickListener() {
                    @Override
                    public void onMapLongClick(@NonNull final LatLng point) {
                        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                        photoPickerIntent.setType("image/*");
                        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
                        final IconFactory iconFactory = IconFactory.getInstance(getActivity());

                        new MaterialDialog.Builder(getActivity())
                                .title(R.string.input)
                                .content(R.string.input_content)
                                .inputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                                .input(R.string.input_hint1, 0, new MaterialDialog.InputCallback() {
                                    @Override
                                    public void onInput(MaterialDialog dialog, final CharSequence input1) {
                                        new MaterialDialog.Builder(getActivity())
                                                .title(R.string.input21)
                                                .content(R.string.input_content21)
                                                .inputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_CAP_SENTENCES)
                                                .input(R.string.input_hint2, 0, new MaterialDialog.InputCallback() {

                                                    @Override
                                                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input2) {

                                                        if(bitmap!=null) {
                                                            Icon icon = iconFactory.fromBitmap(bitmap);
                                                            MarkerViewOptions marker = new MarkerViewOptions().icon(icon)
                                                                    .position(new LatLng(point))
                                                                    .title(input1.toString())
                                                                    .snippet(input2.toString());

                                                            mapboxMap.addMarker(marker);
                                                        }
                                                        else{
                                                            Toast.makeText(getActivity(), "No", Toast.LENGTH_SHORT).show();}
                                                        }

                                                }).show();


                                    }
                                }).show();
                    }

                });
                mapboxMap.setCameraPosition(new CameraPosition.Builder()
                        .bearing(0F)
                        .tilt(0F)
                        .zoom(15F)
                        .target(new LatLng(48.467F, 35.05F))
                        .build());
            }
        });
        mapView.onCreate(savedInstanceState);

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {

            case GALLERY_REQUEST:
                Log.d("in1", "in1");
                if (resultCode == getActivity().RESULT_OK) {
                    Log.d("in2", "in2");
                    Uri selectedImage = data.getData();
                    try {
                        int k = 0;
                        Bitmap bitmap_ = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                       // if (bitmap_ == null){
                            Toast.makeText(getActivity(), "Null", Toast.LENGTH_LONG).show();//}
                        if(bitmap_.getHeight()<=bitmap_.getWidth())
                        {
                            k=bitmap_.getHeight()/64;
                            Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap_, (int)bitmap_.getWidth() /k, (int)bitmap_.getHeight() /k,true);
                            bitmap = Bitmap.createBitmap(bitmap1,(int)(bitmap1.getWidth()/2-bitmap1.getHeight()/2),0,bitmap1.getHeight(),bitmap1.getHeight());
                        }
                        else{
                            k=bitmap_.getWidth()/64;
                            Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap_, (int)bitmap_.getWidth() /k, (int)bitmap_.getHeight() /k,true);
                            bitmap = Bitmap.createBitmap(bitmap1,0,(int)(bitmap1.getHeight()/2- bitmap1.getWidth()/2), bitmap1.getWidth(), bitmap1.getWidth());

                        }
                    } catch (IOException e) {
                        Log.d("in3", "in3");
                        Log.d("ktm",e.toString());
                    }

                }

        }
    }
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
