package View;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;

public class DrawingComponent extends JComponent implements MouseListener, MouseWheelListener, MouseMotionListener{

    private double zoom = 1.0;
    private static final double SCALE_STEP = 0.1d;
    private Point origin;

    private final int STEP_SIZE = 40;
    private int MAX_OX = 0;
    private int MAX_OY = 0;
    private final int X_INCREASE = 20;
    private final int Y_DECREASE = 50;

    private int stepSize = 40;
    private int windowWidth = 0, windowHeight = 0;
    private int maxOx = 0, maxOy = 0;
    private final int indentSize = 40;

    private int xIncrease = 20;
    private int yDecrease = 50;

    private Vector<Vector<Integer>> dataVector = new Vector<>();
    private Graphics2D graphics;

    private final MainFrameClass mainFrameClass;

    DrawingComponent(MainFrameClass mainFrameClass){
        this.mainFrameClass = mainFrameClass;
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        setAutoscrolls(true);
    }

    public void fillDataVector(Vector<Vector<Integer>> dataVector){
        this.dataVector = dataVector;
    }

    public void clearData(){
        dataVector.clear();
        maxOx = 0;
        maxOy = 0;
        zoom = 1.0;
    }

    public void paint(Graphics g){
        this.graphics = (Graphics2D) g;
        findMaxOxOy();
        drawAxis();
        drawGraph();
    }

    private void findMaxOxOy(){
        if(!dataVector.isEmpty()){
            for (Vector<Integer> aDataVector : dataVector) {
                if (aDataVector.get(0) * xIncrease > maxOx)
                    maxOx = aDataVector.get(0) * xIncrease + indentSize;
                    MAX_OX = aDataVector.get(0) * X_INCREASE + indentSize;
            }

            for (Vector<Integer> aDataVector : dataVector) {
                if (aDataVector.get(1) / yDecrease > maxOy)
                    maxOy = aDataVector.get(1) / yDecrease + indentSize;
                    MAX_OY = aDataVector.get(1) / Y_DECREASE + indentSize;
            }

            windowWidth = maxOx + 4 * indentSize;
            windowHeight = maxOy + 4 * indentSize;

            setSize(new Dimension(windowWidth, windowHeight));
            setPreferredSize(new Dimension(windowWidth, windowHeight));
        }
    }

    private void drawAxis(){

        int sizeOx = maxOx + stepSize, sizeOy = maxOy + stepSize;

        graphics.drawLine(indentSize, indentSize, sizeOx, indentSize);
        graphics.drawLine(indentSize, indentSize, indentSize, sizeOy);

        graphics.drawString("0", indentSize - 5, indentSize - 5);

        int amount = 2, time = 2;

        for(int oxCounter = indentSize + stepSize; oxCounter < maxOx + indentSize; oxCounter += stepSize){
            graphics.drawLine(oxCounter, indentSize - 2, oxCounter, indentSize + 2);
            graphics.drawString(String.valueOf(amount), oxCounter, indentSize - 8);
            amount += 2;
        }

        for(int oyCounter = indentSize + stepSize; oyCounter < maxOy + indentSize; oyCounter += stepSize){
            graphics.drawLine(indentSize - 2, oyCounter, indentSize + 2, oyCounter);
            graphics.drawString(String.valueOf(time), indentSize - 20, oyCounter);
            time += 2;
        }

        graphics.drawLine(sizeOx - 5, indentSize - 4, sizeOx, indentSize);
        graphics.drawLine(sizeOx - 5, indentSize + 4, sizeOx, indentSize);
        graphics.drawLine(indentSize - 4, sizeOy - 5, indentSize, sizeOy);
        graphics.drawLine(indentSize + 4, sizeOy - 5, indentSize, sizeOy);

        graphics.drawString("N", sizeOx + 5, indentSize);
        graphics.drawString("T", indentSize - 15, sizeOy + 5);
    }

    private void drawGraph(){

        if(dataVector.size() > 1){
            graphics.setColor(Color.GREEN);

            for(int pointsCounter = 1; pointsCounter<dataVector.size(); pointsCounter++){
                graphics.drawLine(dataVector.get(pointsCounter - 1).get(0) * xIncrease + indentSize,
                        dataVector.get(pointsCounter - 1).get(1) / yDecrease + indentSize,
                        dataVector.get(pointsCounter).get(0) * xIncrease + indentSize,
                        dataVector.get(pointsCounter).get(1) / yDecrease + indentSize);
            }
        }
    }

    private void doZoom(){
        stepSize = (int) (STEP_SIZE * zoom);
        maxOx = (int) (MAX_OX * zoom);
        maxOy = (int) (MAX_OY * zoom);
        xIncrease = (int) (X_INCREASE * zoom);
        yDecrease = (int) (Y_DECREASE / zoom);

        windowWidth = maxOx + 4 * indentSize;
        windowHeight = maxOy + 4 * indentSize;
    }

    public void mouseWheelMoved(MouseWheelEvent e) {

        if((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) {
            double zoomFactor = -SCALE_STEP * e.getPreciseWheelRotation();
            zoom = Math.abs(zoom + zoomFactor);

            if (zoom > 0.8 && zoom < 1.5) {
                doZoom();
                Dimension d = new Dimension(windowWidth, windowHeight);
                setPreferredSize(d);
                setSize(d);
                mainFrameClass.setZoomLabel(zoom);
                validate();
                revalidate();
            }
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (origin != null) {
            int deltaX = origin.x - e.getX();
            int deltaY = origin.y - e.getY();
            Rectangle view = getVisibleRect();
            view.x += deltaX;
            view.y += deltaY;
            scrollRectToVisible(view);
        }
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        origin = new Point(e.getPoint());
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

}
