package View;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;

class DrawingComponent extends JComponent {

    private Vector<Vector<Integer>> dataVector = new Vector<>();

    public void addData(Vector<Vector<Integer>> polyLine){
        dataVector = polyLine;
    }

    public void clearData(){
        dataVector.clear();
    }

        @Override
    protected void paintComponent(Graphics gh) {

        Graphics2D graphics2D = (Graphics2D)gh;

        int maxOx = 0, maxOy = 0;

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

        graphics2D.drawLine(60, 20, maxOx*5 + 100, 20);
        graphics2D.drawLine(60, 20, 60, maxOy/500 + 60);
        graphics2D.drawString("0", 50, 12);
        graphics2D.drawLine(maxOx*5 + 100, 20, maxOx*5 + 90, 10);
        graphics2D.drawLine(maxOx*5 + 100, 20, maxOx*5 + 90, 30);
        graphics2D.drawLine(50, maxOy/500 + 50, 60, maxOy/500 + 60);
        graphics2D.drawLine(70, maxOy/500 + 50, 60, maxOy/500 + 60);
        graphics2D.drawString("N", maxOx*5 +105, 20);
        graphics2D.drawString("T", 50, maxOy/500 + 75);

        for(int Ox = 80; Ox<maxOx*5 + 80; Ox+=30){
            graphics2D.drawLine(Ox, 15, Ox, 25);
        }

        for(int Oy = 40; Oy<maxOy/500 + 40; Oy+=11){
            graphics2D.drawLine(55, Oy, 65, Oy);
        }

        int amount = 5;

        for(int OxNumbers = 80; OxNumbers<maxOx*5 + 80; OxNumbers+=60){
            graphics2D.drawString( ""+amount, OxNumbers, 12);
            amount += 10;
        }

        int time = 15000;

        for(int OyNumbers = 40; OyNumbers<maxOy/500 + 40; OyNumbers+=22){
           graphics2D.drawString(""+time, 10, OyNumbers);
           time += 10000;
        }

        if(dataVector.size()>1) {
            for (int drawCounter = 1; drawCounter < dataVector.size(); drawCounter++) {
                graphics2D.drawLine(dataVector.get(drawCounter - 1).get(0) * 5 + 80, dataVector.get(drawCounter - 1).get(1) / 500 + 20,
                        dataVector.get(drawCounter).get(0) * 5 + 80, dataVector.get(drawCounter).get(1) / 500 + 20);
            }
        }

    }
}
