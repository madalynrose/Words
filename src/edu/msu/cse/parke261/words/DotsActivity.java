package edu.msu.cse.parke261.words;

import android.os.Bundle;
import android.app.Activity;

public class DotsActivity extends Activity {

	DotsView dotsView = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dots);
		dotsView = (DotsView) findViewById(R.id.dotsView);
	}


}
