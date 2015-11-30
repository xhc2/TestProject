package com.example.tongmin.testproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by TongMin on 2015/11/26.
 */
public class MyFragment extends Fragment {

    private String str  ;
    private TextView tv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        str = (String)bundle.get("who");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        tv = (TextView)view.findViewById(R.id.tv);
        tv.setText(str);
    }

    public static MyFragment getInstance(Bundle bundle){
        MyFragment fr = new MyFragment();
        fr.setArguments(bundle);
        return fr;
    }
}
