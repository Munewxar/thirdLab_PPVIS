package Controller;

import View.DrawingComponent;
import View.MainFrameClass;
import View.MyTableModel;

import javax.swing.*;
import java.util.Vector;

public class PaintButtonEventAssistant {

    private MainFrameClass mainFrameClass;
    private MergeSort mergeSort = new MergeSort();
    private DrawingComponent drawingComponent;
    private MyTableModel myTableModel;

    public PaintButtonEventAssistant(MainFrameClass mainFrameClass, DrawingComponent drawingComponent, MyTableModel myTableModel) {
        this.mainFrameClass = mainFrameClass;
        this.drawingComponent = drawingComponent;
        this.myTableModel = myTableModel;
    }

    public void doPaintButtonFunction(){
        try{
            Integer.parseInt(mainFrameClass.getAmountOfVectorsTextField().getText());
            mainFrameClass.getVectorSortedVectors().clear();
            drawingComponent.clearData();
            mergeSort.renewMassiveSize();
            mergeSort.getSortingTime().renewRealSortTime();

            Thread sortingThread = new Thread(mergeSort);
            Thread drawingThread = new Thread(() -> {

                for(int vectorsCounter = 0;
                    vectorsCounter < Integer.parseInt(mainFrameClass.getAmountOfVectorsTextField().getText()); vectorsCounter++){

                    sortingThread.run();

                    try{
                        Thread.sleep(50);
                    }catch (InterruptedException ignored){}

                    if (sortingThread.isAlive()){

                        try {
                            sortingThread.join();
                        }catch (InterruptedException ignored){}

                    }else{
                        mainFrameClass.getVectorSortedVectors().add(new Vector<>());
                        mainFrameClass.getVectorSortedVectors().get(vectorsCounter).add(mergeSort.getMassiveGenerator().
                                getResultMassive().length);
                        mainFrameClass.getVectorSortedVectors().get(vectorsCounter).add((int) mergeSort.getSortingTime().
                                getRealSortTime());

                        drawingComponent.fillDataVector(mainFrameClass.getVectorSortedVectors());
                        myTableModel.setData(mainFrameClass.getVectorSortedVectors());
                        myTableModel.fireTableStructureChanged();
                        mainFrameClass.getMainFrame().repaint();
                    }
                }
            });

            drawingThread.start();

        }catch (NumberFormatException E){
            JOptionPane.showMessageDialog(null, "You can enter only NUMBERS!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
