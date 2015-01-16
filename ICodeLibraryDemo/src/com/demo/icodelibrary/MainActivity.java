package com.demo.icodelibrary;

import android.app.Activity;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	private Button button_market ,button_toast;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
	}

	
	private void initViews(){
		
		button_market = (Button) findViewById(R.id.main_button_market);
		button_market.setOnClickListener(this);
		
		button_toast = (Button)findViewById(R.id.main_button_toast);
		button_toast.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_button_market:
			break;
		case R.id.main_button_toast:
		  break;

		default:
			break;
		}
		
	}

}
