package edu.msu.cse.parke261.words;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Five Letters");
    }

    
	public void onStartCows(View view) {
		Intent intent = new Intent(this, CowActivity.class);
		startActivity(intent);
	}
	
	public void onStartDots(View view){
		Intent intent = new Intent(this, DotsActivity.class);
		startActivity(intent);
	}
    
}
