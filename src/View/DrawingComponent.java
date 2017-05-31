package View;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

class DrawingComponent extends JComponent {

    private Vector<Vector<Integer>> dataVector = new Vector<>();
    private int maxOx = 0, maxOy = 0;
    private Graphics graphics;

    public void addData(Vector<Vector<Integer>> polyLine){
        dataVector = polyLine;
    }

    public void clearData(){
        dataVector.clear();
    }

        @Override
    protected void paintComponent(Graphics graphics) {

        this.graphics = graphics;

        findMaxOxMaxOyAndSetSize();
        drawAxis();
        drawAxisDeletions();
        drawAxisNumbers();
        drawGraph();
    }

    private void findMaxOxMaxOyAndSetSize(){
        if(!dataVector.isEmpty()){
            for(int maxOxCounter = 0; maxOxCounter<dataVector.size(); maxOxCounter++){
                if(dataVector.get(maxOxCounter).get(0)>maxOx) {
                    maxOx = dataVector.get(maxOxCounter).get(0);
                }
            }

            for(int maxOyCounter = 0; maxOyCounter<dataVector.size(); maxOyCounter++){
                if(dataVector.get(maxOyCounter).get(1)>maxOy){
                    maxOy = dataVector.get(maxOyCounter).get(1);
                }
            }
        }

        super.setSize(maxOx*5 + 150, maxOy/500 + 100);
        super.setPreferredSize(new Dimension(maxOx*5 + 150, maxOy/500 + 100));
    }

    private void drawAxis(){

        graphics.drawLine(60, 20, maxOx*5 + 100, 20);
        graphics.drawLine(60, 20, 60, maxOy/500 + 60);
        graphics.drawString("0", 50, 12);
        graphics.drawLine(maxOx*5 + 100, 20, maxOx*5 + 90, 10);
        graphics.drawLine(maxOx*5 + 100, 20, maxOx*5 + 90, 30);
        graphics.drawLine(50, maxOy/500 + 50, 60, maxOy/500 + 60);
        graphics.drawLine(70, maxOy/500 + 50, 60, maxOy/500 + 60);
        graphics.drawString("N", maxOx*5 +105, 20);
        graphics.drawString("T/1000", 50, maxOy/500 + 75);
    }

    private void drawAxisDeletions(){

        for(int Ox = 80; Ox<maxOx*5 + 80; Ox+=30){
            graphics.drawLine(Ox, 15, Ox, 25);
        }

        for(int Oy = 40; Oy<maxOy/500 + 40; Oy+=11){
            graphics.drawLine(55, Oy, 65, Oy);
        }
    }

    private void drawAxisNumbers(){

        int amount = 5, time = 15;

        for(int OxNumbers = 80; OxNumbers<maxOx*5 + 80; OxNumbers+=60){
            graphics.drawString( ""+amount, OxNumbers, 12);
            amount += 10;
        }

        for(int OyNumbers = 40; OyNumbers<maxOy/500 + 40; OyNumbers+=22){
            graphics.drawString(""+time, 25, OyNumbers);
            time += 10;
        }
    }

    private void drawGraph(){
        if(dataVector.size()>1) {
            for (int drawCounter = 1; drawCounter < dataVector.size(); drawCounter++) {
                graphics.setColor(Color.GREEN);
                graphics.drawLine(dataVector.get(drawCounter - 1).get(0)*5 + 80,
                        dataVector.get(drawCounter - 1).get(1)/500 + 30,
                        dataVector.get(drawCounter).get(0)*5 + 80,
                        dataVector.get(drawCounter).get(1)/500  + 30);
            }
        }
    }

    public void scale(int scale){
        findMaxOxMaxOyAndSetSize();
        drawAxis();
        drawAxisDeletions();
        drawAxisNumbers();
        drawGraph();
    }
}
