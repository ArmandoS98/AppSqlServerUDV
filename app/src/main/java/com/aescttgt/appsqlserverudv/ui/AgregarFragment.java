package com.aescttgt.appsqlserverudv.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.aescttgt.appsqlserverudv.Pojos.Equipo;
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

        Equipo mEquipo = getArguments().getParcelable(getString(R.string.destino));
        NavOptions.Builder builder = new NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setEnterAnim(R.anim.nav_default_enter_anim)
                .setExitAnim(R.anim.nav_default_exit_anim)
                .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
                .setPopExitAnim(R.anim.nav_default_pop_exit_anim);

        NavOptions options = builder.build();

        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.destino), mEquipo);

        BottomNavigationView navigationView = view.findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(getActivity(), R.id.fragment);
        NavigationUI.setupWithNavController(navigationView, navController);


        navController.navigate(R.id.jugadorIngresoFragment, bundle, options);

        navigationView.setOnNavigationItemSelectedListener(item -> {
            navController.navigate(item.getItemId(), bundle, options);
            return true;
        });
    }


    @Override
    public void onClick(View v) {
//        insetar();
    }

}