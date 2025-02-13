package com.example.myfirstapplication.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myfirstapplication.R;
import com.example.myfirstapplication.databinding.FragmentHomeBinding;
import com.example.myfirstapplication.ui.game.Playthrough;
import com.example.myfirstapplication.ui.game.Tiles;

import java.util.Iterator;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private final int[][] buttonIds = {
            {R.id.topLeft, R.id.topCenterLeft, R.id.topCenterRight, R.id.topRight},
            {R.id.midTopLeft, R.id.midTopCenterLeft, R.id.midTopCenterRight, R.id.midTopRight},
            {R.id.midBottomLeft, R.id.midBottomCenterLeft, R.id.midBottomCenterRight, R.id.midBottomRight},
            {R.id.bottomLeft, R.id.bottomCenterLeft, R.id.bottomCenterRight, R.id.bottomRight}
    };
    Tiles tiles = new Tiles(12345L);
    Playthrough playthrough = new Playthrough();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tiles.setupInitTiles();
        updateTiles();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void correctTile(View v) {
        playthrough.incrementPoints();
        Log.d("buttons", "correct tile!" + playthrough.getPoints());
        tiles.stepForward();

        updateTiles();

    }

    public void wrongTile(View v) {
        Log.d("buttons", "wrong tile!");
        Log.d("buttons", "you lost");
    }

    public void updateTiles() {

        View root = binding.getRoot();

        Iterator<Tiles.Column> itr = tiles.getColQueue().iterator();
        int j = 0;
        while (itr.hasNext()) {

            Tiles.Column col = itr.next();
            int indexOfBlackTile = col.getIndexOfBlackTile();
            Log.d("INFO", "" + indexOfBlackTile);

            for (int i = 0; i<4; i++) {
                int buttonID = buttonIds[3-j][i];
                Button button = root.findViewById(buttonID);
                if (i == indexOfBlackTile) {
                    button.setOnClickListener(this::correctTile);
                    button.setBackgroundColor(Color.DKGRAY);
                    button.setTextColor(Color.WHITE);
                }
                else {
                    button.setOnClickListener(this::wrongTile);
                    button.setBackgroundColor(Color.WHITE);
                    button.setTextColor(Color.DKGRAY);
                }
            }
            j++;
        }
        playthrough.checkCheckPoint();
        displayStats();
    }

    public void displayStats() {

        View root = binding.getRoot();

        Button stepsButton = root.findViewById(R.id.topLeft);
        stepsButton.setText("Steps \n" + playthrough.getPoints());
        Button timeLeftButton = root.findViewById(R.id.topRight);
        timeLeftButton.setText("Time left \n" + playthrough.getFormattedTimeLeft());
    }
}