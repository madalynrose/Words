package edu.msu.cse.parke261.words;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DotsView extends View {

	private int size = 8;
	private ArrayList<Dot> dots = null;
	Paint pen = new Paint();
	float spacing = 0;
	public DotsView(Context context) {
		super(context);
		dots = new ArrayList<Dot>();
		pen.setColor(Color.BLACK);
		pen.setStrokeWidth(1);
		size = 8;
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				dots.add(new Dot((i*size)+j,i,j));
			}
		}
	}

	public DotsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		dots = new ArrayList<Dot>();
		pen.setColor(Color.BLACK);
		pen.setStrokeWidth(1);
		size = 8;
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				dots.add(new Dot((i*size)+j,i,j));
			}
		}
	}

	public DotsView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		dots = new ArrayList<Dot>();
		pen.setColor(Color.BLACK);
		pen.setStrokeWidth(1);
		size = 8;
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				dots.add(new Dot((i*size)+j,i,j));
			}
		}
	}
	
	private class Dot{
		
		private int player;
		

		public int getPlayer() {
			return player;
		}

		public void setPlayer(int player) {
			this.player = player;
		}
		
		private int index;
		
		Point location = new Point();
		
		public Dot(int i, int x, int y)
		{
			setIndex(i);
			location.x = x;
			location.y = y;
			setPlayer(0);
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	    public boolean hit(float x, float y) {
	        // Make relative to the location and size to the piece size
	        int pX = (int)(x - location.x ); 
	        int pY = (int)(y - location.y);
	        
	        if(pX < 0 || pX >= spacing/2 ||
	           pY < 0 || pY >= spacing/2) {
	            return false;
	        }
	        
	        // We are within the rectangle of the piece.
	        // Are we touching actual picture?
	        return true;
	    }
		
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas); 
		  // Get the canvas size
        float wid = canvas.getWidth();
        float hit = canvas.getHeight();
        
        float spacingWid = wid/size;
        float spacingHit =  hit/size;
        
        spacing = spacingHit< spacingWid ? spacingHit : spacingWid;
        canvas.translate(spacing/2,  spacing/2);
		if(!dots.isEmpty())
		{
			for(int i=0;i<dots.size();i++)
			{
				switch(dots.get(i).getPlayer()){
					case(0):
					{
						pen.setColor(Color.BLACK);
						break;
					}
					case(1):
					{
						pen.setColor(Color.GREEN);
						break;
					}
					case(2):
					{
						pen.setColor(Color.MAGENTA);
						break;
					}
				}
				canvas.drawCircle(dots.get(i).location.x*spacing, dots.get(i).location.y*spacing,3, pen);
			}
			
        }

		canvas.restore();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		//return super.onTouchEvent(event);
		float x = event.getX();
		float y = event.getY();
        switch(event.getActionMasked()) {
		
        case MotionEvent.ACTION_DOWN:
        	for(int i=0;i<dots.size();i++)
        	{
        		if(dots.get(i).hit(x, y))
        		{
        			dots.get(i).setPlayer(1);
        			invalidate();
        		}
        	}
            break; 
				
        case MotionEvent.ACTION_UP:
        case MotionEvent.ACTION_CANCEL:
            break;
				
        case MotionEvent.ACTION_MOVE:
            break;
        }
		return true;
	}
	


	

}
