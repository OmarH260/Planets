package com.example.planets.Pages;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.planets.AddDataFire.FirebaseServices;
import com.example.planets.AddDataFire.Planet;
import com.example.planets.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddPlanetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddPlanetFragment extends Fragment {

    private FirebaseServices fbs;
    private RecyclerView rvPlanet;
    private EditText etSize, etDistance, etName, etOrbitPeriod;
    private ImageButton btnBack;
    private Button btnAdd;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddPlanetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddRestaurantFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddPlanetFragment newInstance(String param1, String param2) {
        AddPlanetFragment fragment = new AddPlanetFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_planet, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        connectComponents();

    }

    private void connectComponents() {
        fbs = FirebaseServices.getInstance();
        etName = getView().findViewById(R.id.etNameAddPlanet);
        etDistance = getView().findViewById(R.id.etDistanceAddPlanet);
        etSize = getView().findViewById(R.id.etSizeAddPlanet);
        etOrbitPeriod = getView().findViewById(R.id.etOrbitAddPlanet);
        rvPlanet = getView().findViewById(R.id.rvPlanetsPlanetsFragment);
        btnAdd = getView().findViewById(R.id.btnAddAddPlanet);
        btnBack = getView().findViewById(R.id.btnBackAddPlanet);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoHomeFragment();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get data from screen
                String name = etName.getText().toString();
                String distance = etDistance.getText().toString();
                String size = etSize.getText().toString();
                String orbit = etOrbitPeriod.getText().toString();

                // data validation
                if (name.trim().isEmpty() || distance.trim().isEmpty() ||
                        size.trim().isEmpty() || orbit.trim().isEmpty())
                {
                    Toast.makeText(getActivity(), "Some fields are empty!", Toast.LENGTH_LONG).show();
                    return;
                }

                // add data to firestore
                Planet planet = new Planet(name, size, distance, orbit);

                fbs.getFire().collection("planets").add(planet).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getActivity(), "Successfully added your planet!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Failure AddPlanet: ", e.getMessage());
                    }
                });


            }
        });
    }
    private void gotoHomeFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMain,new HomeFragment());
        ft.commit();
    }
}