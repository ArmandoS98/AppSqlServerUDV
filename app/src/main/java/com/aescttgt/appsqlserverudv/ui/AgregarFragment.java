package com.aescttgt.appsqlserverudv.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.aescttgt.appsqlserverudv.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AgregarFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "AgregarFragment";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView navigationView = view.findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(getActivity(),  R.id.fragment);
        NavigationUI.setupWithNavController(navigationView, navController);
    }


    @Override
    public void onClick(View v) {
//        insetar();
    }

}