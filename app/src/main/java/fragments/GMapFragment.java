package fragments;


import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.client.maks.client.MainActivity;
import com.client.maks.client.Place;
import com.client.maks.client.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
/*import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerViewOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;*/

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import placeDataBase.pservice.PlaceService;

//import android.support.v4.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class GMapFragment extends Fragment implements OnMapReadyCallback {
    Bitmap bitmap;
    static final int GALLERY_REQUEST = 1;
    GoogleMap map;
    MapView mapView;

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        mapView = (MapView) v.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setMyLocationButtonEnabled(false);
        MapsInitializer.initialize(this.getActivity());

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(48.467F, 35.05F), 15);
        map.animateCamera(cameraUpdate);

        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(final LatLng latLng) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                Log.d("mmr","1");

                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);

                new MaterialDialog.Builder(getActivity())
                        .title(R.string.input)
                        .content(R.string.input_content)
                        .inputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                        .input(R.string.input_hint1, 0, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, final CharSequence input1) {


                                                if(bitmap!=null) {
                                                    map.addMarker(new MarkerOptions()
                                                            .position(latLng)
                                                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                                                            .title(input1.toString()));
                                                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                                    bitmap.compress(Bitmap.CompressFormat.PNG, 90, baos);
                                                    byte[] image = baos.toByteArray();
                                                    new PlaceService(getActivity()).save(new Place(input1.toString(), image));
                                                }
                                                else{
                                                    Log.d("mmr","Oops");
                                                    Toast.makeText(getActivity(), "No", Toast.LENGTH_SHORT).show();
                                                }
                            }

                        }).show();
            }
        });
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int permission = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    getActivity(),
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
        switch(requestCode) {

            case GALLERY_REQUEST:
                Log.d("in1", "in1");
                if (resultCode == -1) {
                    Log.d("in2", "in2");
                    Uri selectedImage = data.getData();
                    try {
                        int k = 0;
                        Bitmap bitmap_ = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                        /*if (bitmap_ == null){
                            Toast.makeText(getActivity(), "Null", Toast.LENGTH_LONG).show();}*/
                        Log.d("mmr","2");

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
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    /*@Override
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
    }*/


}
