package com.aure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Range;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aure.UiModels.CompleteProfileModel;
import com.aure.UiModels.Utils.ListDialog;
import com.google.android.material.button.MaterialButton;
import com.mohammedalaa.seekbar.DoubleValueSeekBarView;
import com.mohammedalaa.seekbar.OnDoubleValueSeekBarChangeListener;
import com.mohammedalaa.seekbar.OnRangeSeekBarChangeListener;
import com.mohammedalaa.seekbar.RangeSeekBarView;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class PreferenceFilter extends AppCompatActivity {

    LinearLayout statusSelect,religionSelect,drinkingSelect,smokingSelect,goalSelect,languageSelect;
    TextView languageText,statusText,religionText,drinkingText,smokingText,goalText;
    ListDialog listDialog;
    ArrayList<String> languageItems = new ArrayList<>();
    ArrayList<String> statusItems = new ArrayList<>();
    ArrayList<String> religionItems = new ArrayList<>();
    ArrayList<String> drinkingItems = new ArrayList<>();
    ArrayList<String> smokingItems = new ArrayList<>();
    ArrayList<String> goalsItems = new ArrayList<>();
    DoubleValueSeekBarView ageSeekbar;
    MaterialButton startSearching;
    LinearLayout preferenceBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_filter);
        initView();
    }

    private void initView(){
        populateDialogListView();
        languageText = findViewById(R.id.language_select_textview);
        languageSelect = findViewById(R.id.profile_language_select);
        goalSelect = findViewById(R.id.profile_marriage_goals_select);
        goalText = findViewById(R.id.marriage_goals_select_textview);
        preferenceBack = findViewById(R.id.preference_back);
        startSearching = findViewById(R.id.preference_start_searching);
        ageSeekbar = findViewById(R.id.age_range_seekbar);
        statusSelect = findViewById(R.id.status__filter_select);
        religionSelect = findViewById(R.id.religion_filter_select);
        drinkingSelect = findViewById(R.id.drinking_filter_select);
        smokingSelect = findViewById(R.id.smoking_filter_select);
        statusText = findViewById(R.id.status_filter_select_textview);
        religionText = findViewById(R.id.religion_filter_select_textview);
        drinkingText = findViewById(R.id.drinking_filter_select_textview);
        smokingText = findViewById(R.id.smoking_filter_select_textview);

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            statusText.setText(preferences.getString("status","Single"));
            religionText.setText(preferences.getString("religion","Christian"));
            drinkingText.setText(preferences.getString("drinking","d'ont drink"));
            smokingText.setText(preferences.getString("smoking","d'ont smoke"));
            languageText.setText(preferences.getString("language","English only"));
            goalText.setText(preferences.getString("goal","Ready to marry in 1-2 years"));
           // ageSeekbar.setMinValue(Integer.parseInt(preferences.getString("min_age","18")));
           // ageSeekbar.setMaxValue(Integer.parseInt(preferences.getString("max_age","79")));

        startSearching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });

        preferenceBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

            ageSeekbar.setOnRangeSeekBarViewChangeListener(new OnDoubleValueSeekBarChangeListener() {
                @Override
                public void onValueChanged(@Nullable DoubleValueSeekBarView doubleValueSeekBarView, int i, int i1, boolean b) {

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(PreferenceFilter.this);
                    String min_age = String.valueOf(i);
                    String max_age = String.valueOf(i1);
                    preferences.edit().putString("min_age",min_age).apply();
                    preferences.edit().putString("max_age",max_age).apply();
                    preferences.edit().putBoolean("preference_saved",true).apply();

                }

                @Override
                public void onStartTrackingTouch(@Nullable DoubleValueSeekBarView doubleValueSeekBarView, int i, int i1) {

                }

                @Override
                public void onStopTrackingTouch(@Nullable DoubleValueSeekBarView doubleValueSeekBarView, int i, int i1) {


                }
            });

        religionSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               listDialog = new ListDialog(religionItems,PreferenceFilter.this);
               listDialog.showListDialog();
               listDialog.setItemClickedListener(new ListDialog.OnItemClickedListener() {
                   @Override
                   public void onItemClicked(String item) {
                       religionText.setText(item);
                       SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(PreferenceFilter.this);
                       preferences.edit().putString("religion",item).apply();
                       preferences.edit().putBoolean("preference_saved",true).apply();
                   }
               });
            }
        });

        statusSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listDialog = new ListDialog(statusItems,PreferenceFilter.this);
                listDialog.showListDialog();
                listDialog.setItemClickedListener(new ListDialog.OnItemClickedListener() {
                    @Override
                    public void onItemClicked(String item) {
                        statusText.setText(item);
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(PreferenceFilter.this);
                        preferences.edit().putString("status",item).apply();
                        preferences.edit().putBoolean("preference_saved",true).apply();
                    }
                });
            }
        });

        drinkingSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listDialog = new ListDialog(drinkingItems,PreferenceFilter.this);
                listDialog.showListDialog();
                listDialog.setItemClickedListener(new ListDialog.OnItemClickedListener() {
                    @Override
                    public void onItemClicked(String item) {
                        drinkingText.setText(item);
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(PreferenceFilter.this);
                        preferences.edit().putString("drinking",item).apply();
                        preferences.edit().putBoolean("preference_saved",true).apply();
                    }
                });
            }
        });

        languageSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listDialog = new ListDialog(languageItems,PreferenceFilter.this);
                listDialog.showListDialog();
                listDialog.setItemClickedListener(new ListDialog.OnItemClickedListener() {
                    @Override
                    public void onItemClicked(String item) {
                        languageText.setText(item);
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(PreferenceFilter.this);
                        preferences.edit().putString("language",item).apply();
                        preferences.edit().putBoolean("preference_saved",true).apply();

                    }
                });
            }
        });

        goalSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listDialog = new ListDialog(goalsItems,PreferenceFilter.this);
                listDialog.showListDialog();
                listDialog.setItemClickedListener(new ListDialog.OnItemClickedListener() {
                    @Override
                    public void onItemClicked(String item) {
                        goalText.setText(item);
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(PreferenceFilter.this);
                        preferences.edit().putString("goal",item).apply();
                        preferences.edit().putBoolean("preference_saved",true).apply();

                    }
                });
            }
        });

        smokingSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listDialog = new ListDialog(smokingItems,PreferenceFilter.this);
                listDialog.showListDialog();
                listDialog.setItemClickedListener(new ListDialog.OnItemClickedListener() {
                    @Override
                    public void onItemClicked(String item) {
                        smokingText.setText(item);
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(PreferenceFilter.this);
                        preferences.edit().putString("smoking",item).apply();
                        preferences.edit().putBoolean("preference_saved",true).apply();
                    }
                });
            }
        });
    }

    private void saveDefaultPreference(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putString("status","Single").apply();
        preferences.edit().putString("religion","Christian").apply();
        preferences.edit().putString("drinking","d'ont drink").apply();
        preferences.edit().putString("smoking","d'ont smoke").apply();
        preferences.edit().putInt("min_age",18).apply();
        preferences.edit().putInt("max_age",70).apply();
        preferences.edit().putBoolean("preference_saved",false).apply();
    }
    private boolean isPreferenceSaved(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isSaved = preferences.getBoolean("preference_saved",false);
        return isSaved;
    }

    private void populateDialogListView(){
        statusItems.add("Single");
        statusItems.add("Married");
        statusItems.add("Divorced");

        religionItems.add("Muslim");
        religionItems.add("Christian");
        religionItems.add("Traditional");

        drinkingItems.add("d'ont drink");
        drinkingItems.add("drinks occasionally");
        drinkingItems.add("drink always");

        smokingItems.add("d'ont smoke");
        smokingItems.add("Smokes occasionally");
        smokingItems.add("Smoke always");

        goalsItems.add("Ready to marry in 3-6 months");
        goalsItems.add("Ready to marry in 6-12 months");
        goalsItems.add("Ready to marry in 1-2 years");

        languageItems.add("Hausa and English");
        languageItems.add("Ibo and English");
        languageItems.add("Yoruba and English");
        languageItems.add("English Only");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.special_activity_background));
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.special_activity_background));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}
