package edu.msu.cse.parke261.words;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class CowView extends View {

	/*
	 * The wordBank
	 */
	private ArrayList<String> wordBank;
	/*
	 * The word the player is guessing
	 */
	private String word = null;
	
	/*
	 * The word the player has guessed
	 */
	private String guess = null;
	/*
	 * The number of bulls in current guess
	 */
	private int bulls = 0;
	public int getBulls() {
		return bulls;
	}

	public void setBulls(int bulls) {
		this.bulls = bulls;
	}

	/*
	 * The number of cows in current guess
	 */
	private int cows = 0;
	
	public int getCows() {
		return cows;
	}

	public void setCows(int cows) {
		this.cows = cows;
	}	
	
	public CowView(Context context){
		super(context);
		init(context);		
	}

	public CowView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public CowView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	
	private void init(Context context){
		cows = 0;
		bulls = 0;
		guess = null;
		word = null;
		wordBank = new ArrayList<String>();
		try {
				InputStream file = context.getAssets().open("5-letter.txt");
				InputStreamReader inputreader = new InputStreamReader(file);
			    BufferedReader buffreader = new BufferedReader(inputreader);	                 
			    String line;


			    // read every line of the file into the line-variable, on line at the time
			    while ((line = buffreader.readLine()) != null) {
			    	String all[] = line.split(" ");
			    	// do something with the settings from the file
			    	for(int i=0;i<all.length;i++)
			    	{	
			    		wordBank.add(all[i]);
			    	}
			    }
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		selectWord();
		invalidate();
		
	}
	
	public void selectWord()
	{
		int index = (int)(Math.random() * (wordBank.size() + 1));
		setWord(wordBank.get(index));
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getGuess() {
		return guess;
	}

	public void setGuess(String guess) {
		this.guess = guess.toLowerCase(Locale.US);
		processGuess();
		invalidate();
	}
	
	public void processGuess()
	{
		boolean found[] = {false, false, false, false, false};
		boolean usedGuess[] = {false, false, false, false, false};
		setCows(0);
		setBulls(0);
		for(int i=0; i<5;i++)
		{
			if(word.charAt(i)==guess.charAt(i))
			{
				setBulls(getBulls()+1);
				found[i] = true;
				usedGuess[i] = true;
			}
		}
		for(int j=0;j<5;j++)
		{
			if(!found[j])
			{
				for(int k=0;k<5;k++)
				{
					if(!usedGuess[k]&&word.charAt(j)==guess.charAt(k))
					{
						setCows(getCows() + 1);
						found[j] = true;
						usedGuess[k] = true;
						break;
					}
				}
			}
		}
		if(getBulls()==5)
		{
        	selectWord();
		}
	}

}