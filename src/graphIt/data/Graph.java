package graphIt.data;

import java.util.LinkedList;
import java.util.Random;
//import graphIt.data.*;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;

public class Graph extends RelativeLayout implements OnTouchListener {

	private LinkedList<Vertex> vertices; // Liste mit allen Knoten des Graphen
	private int vertexCount; // Anzahl der Knoten
	private String name; // Name des Graphen
	private int _xDelta; // DIfferenz zwischen aktueller Touchposition (x-Wert)
							// und dem Abstand des Knoten zur linken Screenseite
	private int _yDelta; // Differenz zwischen aktueller Touchposition (y-Wert)
							// und dem Abstand des Knoten zur oberen Screenseite
	private int width; // aktuelle Breite
	private int height; // aktuelle Höhe

	public Graph(Context context, String name) {
		super(context);
		this.name = name;
		this.setLayoutParams(new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.FILL_PARENT));
		vertices = new LinkedList<Vertex>();
		this.setWillNotDraw(false);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		width = this.getWidth();
		height = this.getHeight();
	}

	/**
	 * Fügt einen Neuen Knoten zum Graphen hinzu
	 */
	public void addVertex() {
		Vertex newVertex = new Vertex(this.getContext());
		RelativeLayout.LayoutParams vertexLayout = new RelativeLayout.LayoutParams(
				newVertex.getImageWidth(), newVertex.getImageHeight());
		vertexLayout.leftMargin = getRandomNum(newVertex.getImageWidth(), width
				- newVertex.getImageWidth());
		vertexLayout.topMargin = getRandomNum(newVertex.getImageHeight(),
				height - newVertex.getImageHeight());
		vertexLayout.bottomMargin = -100;
		vertexLayout.rightMargin = -100;
		newVertex.setLayoutParams(vertexLayout);
		newVertex.setOnTouchListener(this);
		newVertex.setLongClickable(true);

		vertices.add(newVertex);
		this.addView(newVertex);
		vertexCount++;
	}

	/**
	 * OnTouch-Event für die Knoten. Wird ausgeführt, wenn auf einen Knoten
	 * gedrückt wurde.
	 */
	public boolean onTouch(View view, MotionEvent event) {
		final int X = (int) event.getRawX();
		final int Y = (int) event.getRawY();
		switch (event.getAction() & MotionEvent.ACTION_MASK) {

		case MotionEvent.ACTION_DOWN:
			RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view
					.getLayoutParams();
			_xDelta = X - lParams.leftMargin;
			_yDelta = Y - lParams.topMargin;
			break;
		case MotionEvent.ACTION_UP:
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			break;
		case MotionEvent.ACTION_POINTER_UP:
			break;
		case MotionEvent.ACTION_MOVE:
			RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
					.getLayoutParams();
			layoutParams.leftMargin = X - _xDelta;
			layoutParams.topMargin = Y - _yDelta;
			layoutParams.rightMargin = -100;
			layoutParams.bottomMargin = -100;

			if (layoutParams.leftMargin > (this.width - view.getWidth()))
				layoutParams.leftMargin = this.width - view.getWidth();

			if (layoutParams.leftMargin < 0)
				layoutParams.leftMargin = 0;

			if (layoutParams.topMargin > (this.height - view.getHeight()))
				layoutParams.topMargin = this.height - view.getHeight();

			if (layoutParams.topMargin < 0)
				layoutParams.topMargin = 0;

			view.setLayoutParams(layoutParams);
			break;
		}
		this.invalidate();
		return true;
	}

	/**
	 * Generiert einen ganzzahligen Zufallswert zwischen den Grenzen lo und hi
	 * 
	 * @param lo
	 *            untere Grenze
	 * @param hi
	 *            obere Grenze
	 * @return zufällige Zahl
	 */
	private int getRandomNum(int lo, int hi) {
		Random random = new Random();
		int randomNum = random.nextInt(hi);
		if (randomNum < lo)
			randomNum += lo;

		return randomNum;
	}

	/**
	 * Gibt Name des Graphs zurück
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Ändert den Namen des Graphs
	 * 
	 * @param name
	 *            Neuer Name des Graphs
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Entfernt alle Knoten vom Graphen
	 */
	public void removeAllVertices() {
		vertices.clear();
		this.removeAllViews();
	}

	/**
	 * Methode zur Entfernung des zuletzt hinzugefügten Knoten.
	 * 
	 * @return true, wenn der letzte hinzugefügte Knoten gelöscht wurde. False,
	 *         falls der Graph leer ist
	 */
	public boolean removeLastVertex() {
		if (vertices.isEmpty())
			return false;
		Vertex removedVertex = vertices.removeLast();
		this.removeViewInLayout(removedVertex);
		this.invalidate();
		return true;
	}

	/**
	 * Überprüft, ob der Graph Knoten enthält
	 * 
	 * @return true, wenn der Graph leer ist. False, wenn Knoten vorhanden sind.
	 */
	public boolean isEmpty() {
		return vertices.isEmpty();
	}
}
