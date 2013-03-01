package edu.msu.cse.parke261.words;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

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
    
    /**
     * The scores
     */
    private TextView cow_score = null;
    private TextView bull_score = null;
    
    /**
     * The word they're guessing for
     */
    private String word = null;
    
    /**
     * The Previous Guesses Button
     */
    private Button prev = null;
    
    /**
     * whether prev guesses button is clickable
     */
    boolean canClick = false;
    
    /**
     * The guesses made so far
     */
    private ArrayList<String> guesses = null;
    
    
    /**
     * whether letters have been discarded
     */
    private boolean used[] = null;
    
    private static int USED_LETTERS = 1;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cow);
        cowView = (CowView)findViewById(R.id.cowView);
        word = cowView.getWord();
        guessEdit = (EditText)findViewById(R.id.editGuess);
        cow_score = (TextView)findViewById(R.id.cow_score);
        bull_score = (TextView)findViewById(R.id.bull_score);
        prev = (Button)findViewById(R.id.buttonPrevious);
        prev.setClickable(false);
        cow_score.setText("0");
        bull_score.setText("0");
        setTitle("Cows and Bulls");
        guesses = new ArrayList<String>();
        used = new boolean[26];
        for(int i=0;i<used.length;i++)
        {
        	used[i] = false;
        }
        canClick = false;
	}	
	public void onSubmit(View view){
		guess = guessEdit.getText().toString();
		guessEdit.setText("");
		if(guess.length()!=5)
		{
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(cowView.getContext());
	 
				// set title
				alertDialogBuilder.setTitle("Error");
	 
				// set dialog message
				alertDialogBuilder
					.setMessage("Please make sure your guess is 5 letters long")
					.setCancelable(false)
					.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					  });
	 
					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();
	 
					// show it
					alertDialog.show();
		}
		else{	
			cowView.setGuess(guess);
			if(cowView.getBulls()==5)
			{
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(cowView.getContext());
				 
				// set title
				alertDialogBuilder.setTitle("Congratulations!");
	 
				// set dialog message
				alertDialogBuilder
					.setMessage("You have guessed the word correctly!")
					.setCancelable(false)
					.setPositiveButton("Play Again",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
					        cow_score.setText("0");
					        bull_score.setText("0");
					        guesses = new ArrayList<String>();
					        canClick = false;
							onRestart();
						}
					  })
					.setNegativeButton("Quit",new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							finish();
						}
					  });
				
	 
					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();
	 
					// show it
					alertDialog.show();
			}
			else
			{
				cow_score.setText(cowView.getCows()+"");
				bull_score.setText(cowView.getBulls()+"");
				guesses.add(guess+"\tCows: "+cow_score.getText().toString()+"\tBulls: "+bull_score.getText().toString());
				canClick = true;
		        prev.setClickable(canClick);
			}
		}
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
	  super.onSaveInstanceState(savedInstanceState);
	  // Save UI state changes to the savedInstanceState.
	  // This bundle will be passed to onCreate if the process is
	  // killed and restarted.
	  savedInstanceState.putString("cow_score",cow_score.getText().toString());
	  savedInstanceState.putString("bull_score",bull_score.getText().toString());
	  savedInstanceState.putString("guess",guess);
	  savedInstanceState.putString("word",word);
	  savedInstanceState.putString("currentEditText", guessEdit.getText().toString());
	  savedInstanceState.putStringArrayList("guesses", guesses);
	  savedInstanceState.putBoolean("canClick",canClick);
	  savedInstanceState.putBooleanArray("used",used);
	  // etc.
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
	  super.onRestoreInstanceState(savedInstanceState);
	  // Restore UI state from the savedInstanceState.
	  // This bundle has also been passed to onCreate.
	  cow_score.setText(savedInstanceState.getString("cow_score"));
	  bull_score.setText(savedInstanceState.getString("bull_score"));
	  guess = savedInstanceState.getString("guess");
	  guessEdit.setText(savedInstanceState.getString("currentEditText"));
	  word = savedInstanceState.getString("word");
	  cowView.setWord(word);
	  guesses = savedInstanceState.getStringArrayList("guesses");
	  canClick = savedInstanceState.getBoolean("canClick");
	  prev.setClickable(canClick);
	  used = savedInstanceState.getBooleanArray("used");
	}
	
	public void onPreviousGuesses(View view){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(cowView.getContext());
		 
		// set title
		alertDialogBuilder.setTitle("Previous Guesses (" + guesses.size() + ")");
		String message = "";
		
		for(int i=0;i<guesses.size();i++)
		{
			message+=(guesses.get(i)+"\n");
		}

		// set dialog message
		alertDialogBuilder
			.setMessage(message)
			.setCancelable(false)
			.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			  });

			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();
		
	}
	
	public void onHelp(View view){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(cowView.getContext());
		 
		// set title
		alertDialogBuilder.setTitle("Rules");

		// set dialog message
		alertDialogBuilder
			.setMessage("At the start of the game, a 5-letter word will be chosen by the game. It is your job to guess the word. " +
					"When you guess a word, the game will give you back the number of 'Cows' (letters that are in the chosen word, but are in the wrong spot in your guess) " +
					"and the number of 'Bulls' (letters that are in the chosen word and are in the correct spot in your guess). " +
					"Use these as clues to make guesses until you get the word right.")
			.setCancelable(false)
			.setPositiveButton("OK",new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			  });

			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();
		
	}
	
	public void onLetters(View view){
		Intent intent = new Intent(getBaseContext(), LettersActivity.class);
		intent.putExtra("used", used);
		startActivityForResult(intent, USED_LETTERS);
	}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getExtras().containsKey("used")){
            used = data.getBooleanArrayExtra("used"); 
        }

    }

}
