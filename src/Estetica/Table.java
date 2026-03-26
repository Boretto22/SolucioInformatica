package Estetica;

import processing.core.PApplet;

public class Table {

    String[] tableHeaders;   // Títols de les columnes
    String[][] tableData;    // Dades de la taula
    float[] columnWidths;    // Amplades de les columnes

    int numCols, numRows;  // Número de files i columnes

    int numTotalPages;

    Paleta paleta;

    // Constructor
    public Table(int nr, int nc){
        this.numRows = nr;
        this.numCols = nc;
    }

    // Setters

    public void setHeaders(String[] h){
        this.tableHeaders = h;
    }

    public void setData(String[][] d){
        this.tableData = d;
        if(d.length % (this.numRows-1)==0){
            this.numTotalPages = (d.length / (this.numRows-1)) -1;
        }
        else {
            this.numTotalPages = (d.length / (this.numRows-1)) ;
        }
    }

    public void setValueAt(String value, int nr, int nc){
        this.tableData[nr][nc] = value;
    }

    public void setColumnWidths(float[] w){
        this.columnWidths = w;
    }

    // Dibuixa taula
    public void display(PApplet p5, float x, float y, float w, float h){

        p5.pushStyle();

        p5.fill(200, 50); p5.stroke(0); p5.strokeWeight(3);
        p5.rect(x, y, w, h);

        float rowHeight = h / numRows;
        p5.fill(paleta.getColorAt(2)); p5.stroke(0); p5.strokeWeight(3);
        p5.rect(x, y, w, rowHeight);

        // Dibuixa files
        p5.stroke(0);
        for(int r = 1; r <numRows; r++){
            if(r==1){ p5.strokeWeight(3); }
            else {    p5.strokeWeight(1); }
            p5.line(x, y + r*rowHeight, x + w, y + r*rowHeight);
        }

        // Dibuixa Columnes
        float xCol = x;
        for(int c = 0; c<numCols; c++){
            xCol += w*columnWidths[c]/100.0;
            p5.line(xCol, y, xCol, y + h);
        }


        // Informació de la Pàgina
        p5.fill(0);

        p5.popStyle();
    }

}
