package com.example.myfirstapplication.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myfirstapplication.databinding.FragmentDashboardBinding;
import com.example.myfirstapplication.ui.game.CsvHandler;
import com.example.myfirstapplication.ui.game.Playthrough;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 🟢 Load and display playthrough data
        loadPlaythroughs();

        return root;
    }

    private void loadPlaythroughs() {
        // 🟢 Read from CSV file
        List<Playthrough> playthroughs = CsvHandler.readFromFile(requireContext());

        // Convert Playthrough objects to a list of strings for display
        List<String> displayList = new ArrayList<>();
        for (Playthrough p : playthroughs) {
            displayList.add("Player: " + p.getPlayer() + ", Steps: " + p.getPoints() + ", Time: " + p.getStartTime());
        }

        // 🟢 Set up ListView and ArrayAdapter
        ListView listView = binding.listViewDashboard; // Assuming you added a ListView in the XML
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, displayList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
