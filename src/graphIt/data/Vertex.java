package graphIt.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;

public class Vertex extends ImageView {

	private final int WIDTH;
	private final int HEIGHT;

	// private String name;

	public Vertex(Context context) {
		super(context);
		this.setImageResource(R.drawable.circle_blue);
		WIDTH = this.getDrawable().getMinimumWidth();
		HEIGHT = this.getDrawable().getMinimumHeight();

	}

	/**
	 * Gibt die Breite des Bildes innerhalb des ImageViews zur�ck
	 * 
	 * @return Breite
	 */
	public int getImageWidth() {
		return WIDTH;
	}

	/**
	 * Gibt die H�he des Bildes innerhalb des ImageViews zur�ck
	 * 
	 * @return H�he
	 */
	public int getImageHeight() {
		return HEIGHT;
	}

}