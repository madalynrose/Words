package edu.msu.cse.parke261.words;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.app.Activity;

public class CowActivity extends Activity {

    /**
     * The cow view object
     */
    private CowView cowView = null;
    /**
     * The player's guess
     */
    private EditText guessEdit = null;
    private String guess = null;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cow);
        cowView = (CowView)findViewById(R.id.cowView);
        guessEdit = (EditText)findViewById(R.id.editGuess);
	}
	
	public void onSubmit(View view){
		guess = guessEdit.getText().toString();
		cowView.setGuess(guess);
		guessEdit.setText("");
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_cow, menu);
		return true;
	}*/

}
