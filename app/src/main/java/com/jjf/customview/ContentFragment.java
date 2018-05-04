package com.jjf.customview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jinjiafeng.
 * date:on 18-5-3
 */

public class ContentFragment extends Fragment {

    public static Fragment newInstance(String arg){
        Bundle bundle = new Bundle();
        bundle.putString("arg",arg);
        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(bundle);
        return fragment;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container,false);
        TextView textView = view.findViewById(R.id.textView);
        textView.setText(getArguments().getString("arg"));
        return view;
    }


//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
//    }
}
