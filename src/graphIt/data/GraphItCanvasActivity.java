package graphIt.data;


import java.util.LinkedList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.*;

public class GraphItCanvasActivity extends Activity {
    /** Called when the activity is first created. */
	
	private Graph g1;  // RelativeLayout für die Vertices
	private RelativeLayout rLay_GraphCanvas;
	private LinkedList<Graph> graphList; //LinkedList zum Speichern der Graphen
	private Button btn_removeLastVertex;
	private Button btn_removeAllVertices;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphit_canvas_activity);
       
        rLay_GraphCanvas = (RelativeLayout) findViewById(R.id.rLay_GraphCanvas);
        btn_removeLastVertex = (Button) findViewById(R.id.btn_removeLastVertex);
        btn_removeAllVertices = (Button) findViewById(R.id.btn_removeAllVertices);
        
        g1 = new Graph(this, "Test");
        rLay_GraphCanvas.addView(g1);
        graphList =  new LinkedList<Graph>();
        graphList.add(g1);
        
        
        
        if(g1.isEmpty()){
        	btn_removeLastVertex.setEnabled(false);
        	btn_removeAllVertices.setEnabled(false);
        }
    }
    
    public void btn_addVertexClick(View view){
    	
    	g1.addVertex();
    	if(!btn_removeLastVertex.isEnabled())
    		btn_removeLastVertex.setEnabled(true);
    	if(!btn_removeAllVertices.isEnabled())
    		btn_removeAllVertices.setEnabled(true);
    }
    
    public void	btn_removeAllVerticesClick(View view){
    	g1.removeAllVertices();
    	btn_removeLastVertex.setEnabled(false);
    	btn_removeAllVertices.setEnabled(false);
    }
    
    public void btn_removeLastVertexClick(View view){
    	g1.removeLastVertex();
    	if(g1.isEmpty()){
        	btn_removeLastVertex.setEnabled(false);
        	btn_removeAllVertices.setEnabled(false);
    	}
    }
}