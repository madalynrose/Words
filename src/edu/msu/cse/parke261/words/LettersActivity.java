package edu.msu.cse.parke261.words;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ToggleButton;

public class LettersActivity extends Activity {
    /**
     * All of the Letter Buttons
     */
    private ToggleButton letters[] = null;
    /**
     * whether letters have been discarded
     */
    private boolean used[] = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_letters);
		used = new boolean[26];
        letters = new ToggleButton[26];
        letters[0]=(ToggleButton)findViewById(R.id.toggleButtonA);
        letters[1]=((ToggleButton)findViewById(R.id.toggleButtonB));
        letters[2]=((ToggleButton)findViewById(R.id.toggleButtonC));
        letters[3]=((ToggleButton)findViewById(R.id.toggleButtonD));
        letters[4]=((ToggleButton)findViewById(R.id.toggleButtonE));
        letters[5]=((ToggleButton)findViewById(R.id.toggleButtonF));
        letters[6]=((ToggleButton)findViewById(R.id.toggleButtonG));
        letters[7]=((ToggleButton)findViewById(R.id.toggleButtonH));
        letters[8]=((ToggleButton)findViewById(R.id.toggleButtonI));
        letters[9]=((ToggleButton)findViewById(R.id.toggleButtonJ));
        letters[10]=((ToggleButton)findViewById(R.id.toggleButtonK));
        letters[11]=((ToggleButton)findViewById(R.id.toggleButtonL));
        letters[12]=((ToggleButton)findViewById(R.id.toggleButtonM));
        letters[13]=((ToggleButton)findViewById(R.id.toggleButtonN));
        letters[14]=((ToggleButton)findViewById(R.id.toggleButtonO));
        letters[15]=((ToggleButton)findViewById(R.id.toggleButtonP));
        letters[16]=((ToggleButton)findViewById(R.id.toggleButtonQ));
        letters[17]=((ToggleButton)findViewById(R.id.toggleButtonR));
        letters[18]=((ToggleButton)findViewById(R.id.toggleButtonS));
        letters[19]=((ToggleButton)findViewById(R.id.toggleButtonT));
        letters[20]=((ToggleButton)findViewById(R.id.toggleButtonU));
        letters[21]=((ToggleButton)findViewById(R.id.toggleButtonV));
        letters[22]=((ToggleButton)findViewById(R.id.toggleButtonW));
        letters[23]=((ToggleButton)findViewById(R.id.toggleButtonX));
        letters[24]=((ToggleButton)findViewById(R.id.toggleButtonY));
        letters[25]=((ToggleButton)findViewById(R.id.toggleButtonZ));
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            used = extras.getBooleanArray("used");
        }
        for(int i=0;i<letters.length;i++)
        {
        	letters[i].setChecked(used[i]);
        }
	}
	
	public void onToggleClicked(View view) {
		ToggleButton button = ((ToggleButton) view);
		boolean on = button.isChecked();
		    
		for(int i=0;i<letters.length;i++)
		{
			if(letters[i].equals(button))
			{
				used[i] = on;
				break;
			}
		}
	}
	
	public void onDone(View view)
	{
		Intent intent = new Intent();
		intent.putExtra("used",used);
		setResult(RESULT_OK, intent);
		finish();
	}
	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
	  super.onRestoreInstanceState(savedInstanceState);
	  // Restore UI state from the savedInstanceState.
	  // This bundle has also been passed to onCreate.
	  used = savedInstanceState.getBooleanArray("used");
	  for(int i=0;i<used.length;i++)
	  {
		  letters[i].setChecked(used[i]);
	  }
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
	  super.onSaveInstanceState(savedInstanceState);
	  savedInstanceState.putBooleanArray("used",used);

	}

}
