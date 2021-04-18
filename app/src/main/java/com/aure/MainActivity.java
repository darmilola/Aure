package com.aure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.aure.UiAdapters.ShowCaseAdapter;
import com.aure.UiModels.ShowCaseModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ShowCaseAdapter showCaseAdapter;
    ArrayList<ShowCaseModel> showCaseModelArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        recyclerView = findViewById(R.id.show_case_recyclerview);

        ShowCaseModel showCaseModel = new ShowCaseModel(1);
        ShowCaseModel showCaseModel2 = new ShowCaseModel(2);
        ShowCaseModel showCaseModel3 = new ShowCaseModel(3);
        ShowCaseModel showCaseModel4 = new ShowCaseModel(4);
        ShowCaseModel showCaseModel5 = new ShowCaseModel(5);
        ShowCaseModel showCaseModel6 = new ShowCaseModel(6);
        ShowCaseModel showCaseModel7 = new ShowCaseModel(7);
        ShowCaseModel showCaseModel8 = new ShowCaseModel(8);
        for(int i = 0; i < 1; i++){
            showCaseModelArrayList.add(showCaseModel);
            showCaseModelArrayList.add(showCaseModel3);
            showCaseModelArrayList.add(showCaseModel2);
            showCaseModelArrayList.add(showCaseModel5);
            showCaseModelArrayList.add(showCaseModel4);
            showCaseModelArrayList.add(showCaseModel6);
            showCaseModelArrayList.add(showCaseModel7);
            showCaseModelArrayList.add(showCaseModel8);
        }
        showCaseAdapter = new ShowCaseAdapter(MainActivity.this,showCaseModelArrayList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(showCaseAdapter);

    }


    @Override
    public void onResume() {

        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.special_activity_background));
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.special_activity_background));
            // getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
           // getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            // getWindow().setFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS,WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }
    }
}
