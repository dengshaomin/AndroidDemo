package com.code.minelazyviewpager;

import java.util.Arrays;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView listView = new ListView(this);
        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.simple_expandable_list_item_1, Arrays
                .asList(new String[]{"LazyViewPagerAdapter", "LazyFragmentPagerAdapter"})));
        listView.setOnItemClickListener(this);
        setContentView(listView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        if (position == 0) {
            startActivity(new Intent(this, TestLazyViewPagerAdapterActivity.class));
        } else {
            startActivity(new Intent(this, TestLazyFragmentPagerAdapterActivity.class));
        }
    }
}
