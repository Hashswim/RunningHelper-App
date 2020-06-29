package com.example.walk;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class basicfragment extends Fragment {

    walking walking = null;
    ImageView iv;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        walking = (walking) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        walking = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.basicfragment,container,false);

        iv = (ImageView) rootview.findViewById(R.id.imageView1);
        AnimationDrawable drawable = (AnimationDrawable) iv.getBackground();

        drawable.start();

        return rootview;
    }
}

