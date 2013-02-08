package edu.msu.cse.parke261.words;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
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
	private String word;
	
	/*
	 * The word the player has guessed
	 */
	private String guess;
	/*
	 * The number of bulls in current guess
	 */
	private int bulls = 0;
	/*
	 * The number of cows in current guess
	 */
	private int cows = 0;
	/*
	 * The paint to draw cow and bull count
	 */
	private Paint paint;
	
	
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
		wordBank = new ArrayList<String>();
		paint = new Paint();
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
		cows = bulls = 0;
		for(int i=0; i<5;i++)
		{
			if(word.charAt(i)==guess.charAt(i))
			{
				bulls++;
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
						cows++;
						found[j] = true;
						usedGuess[k] = true;
						break;
					}
				}
			}
		}
		if(bulls==5)
		{
        	selectWord();
		}
	}
	
	/**
     * Handle a draw event
     * @param canvas canvas to draw on.
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.WHITE);
        paint.setStyle(Style.FILL);
        canvas.drawPaint(paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        if(bulls==5)
        {
        	paint.setTextSize(40);
        	canvas.drawText("You win! New Game Started!", 10, 40, paint);
        }
        else
        {
        	canvas.drawText("Cows: "+cows, 10, 40, paint);
        	canvas.drawText("Bulls: "+bulls, 10, 85, paint);
        }
		invalidate();

    }

}
