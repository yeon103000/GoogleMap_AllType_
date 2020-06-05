package com.example.busanapp.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.busanapp.R;

public class HomeFragment extends Fragment {
    private ImageAdapter adapter = new ImageAdapter();
    private RecyclerView recyclerView;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.PhotoRecycler);

        LinearLayoutManager horizonalLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);

        recyclerView.setLayoutManager(horizonalLayoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setItems(new ImageData().getItems());

        return view;
    }
}