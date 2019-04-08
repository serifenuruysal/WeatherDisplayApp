package com.soulkitchen.app.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.soulkitchen.app.modelView.MainViewModel;
import com.soulkitchen.app.R;
import com.soulkitchen.app.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {

  MainActivityBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    binding.setViewModel(new MainViewModel());
  }
}
