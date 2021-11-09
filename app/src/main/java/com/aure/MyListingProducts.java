package com.aure;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aure.UiAdapters.MarketplaceViewAllAdapter;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyListingProducts extends Fragment {


    View view;
    RecyclerView recyclerView;
    ArrayList<String> deatailList = new ArrayList<>();
    MarketplaceViewAllAdapter viewAllAdapter;
    MaterialButton addProduct;
    String mPhone;
    public MyListingProducts() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_listing_products, container, false);

        initView();
        return view;
    }

    private void initView(){
        recyclerView = view.findViewById(R.id.listings_myproducts_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        for(int i = 0; i < 20; i++){
            deatailList.add("");
        }

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        mPhone = preferences.getString("phonenumber","");

        //viewAllAdapter = new MarketplaceViewAllAdapter(getContext(),deatailList);
        //recyclerView.setAdapter(viewAllAdapter);
        addProduct = view.findViewById(R.id.dashboard_add_product);
        //recyclerView.setLayoutManager(manager);

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPhone.equalsIgnoreCase("null")) {
                    Toast.makeText(getContext(), "Provide contact phone", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(getContext(), AddListing.class));
                }
            }
        });

    }


}
