package View;
import Model.MyTableModel;
import Controller.MergeSort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class MainFrame {

    private final JFrame mainFrame = new JFrame("PPVIS #3");
    private final Vector<Vector<Integer>> vectorSortedVectors = new Vector<>();
    private JButton paintGraphicsButton = new JButton("Paint");
    private JLabel zoomLabel = new JLabel("zoom: x1");
    private DrawingComponent drawingComponent = new DrawingComponent(this);
    private JScrollPane drawScrollPane = new JScrollPane(drawingComponent);
    private JPanel drawInstrumentsPanel = new JPanel();
    private MyTableModel myTableModel = new MyTableModel(vectorSortedVectors);
    private JTable table = new JTable(myTableModel);
    private JScrollPane tableScrollPane = new JScrollPane(table);
    private JTextField amountOfVectorsTextField = new JTextField("10");
    private MergeSort mergeSort = new MergeSort();

    public void setZoomLabel(double zoom){
        zoomLabel.setText("zoom x" + zoom);
    }

    private JFrame buildMainFrame() {

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setSize(1200, 540);

        drawScrollPane.getViewport().setBackground(Color.WHITE);
        drawScrollPane.setPreferredSize(new Dimension(500, 420));

        tableScrollPane.setPreferredSize(new Dimension(500, 420));

        paintGraphicsButton.addActionListener(new paintGraphicsButtonActionListener());

        amountOfVectorsTextField.setPreferredSize(new Dimension(100, 30));
        paintGraphicsButton.setPreferredSize(new Dimension(70, 28));
        zoomLabel.setPreferredSize(new Dimension(70, 30));
        drawInstrumentsPanel.setPreferredSize(new Dimension(300, 35));
        drawInstrumentsPanel.add(amountOfVectorsTextField);
        drawInstrumentsPanel.add(paintGraphicsButton);
        drawInstrumentsPanel.add(zoomLabel);

        mainFrame.add(tableScrollPane, BorderLayout.WEST);
        mainFrame.add(drawScrollPane, BorderLayout.EAST);
        mainFrame.add(drawInstrumentsPanel, BorderLayout.CENTER);

        mainFrame.setVisible(true);
        return mainFrame;
    }

    public class paintGraphicsButtonActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            try{
                Integer.parseInt(amountOfVectorsTextField.getText());
                vectorSortedVectors.clear();
                drawingComponent.clearData();
                mergeSort.renewMassiveSize();
                mergeSort.renewSortTime();

                Thread sortingThread = new Thread(mergeSort);
                Thread drawingThread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        for(int vectorsCounter = 0; vectorsCounter < Integer.parseInt(amountOfVectorsTextField.getText()); vectorsCounter++){

                            sortingThread.run();

                            try{
                                    Thread.sleep(50);
                            }catch (InterruptedException E){}

                            if (sortingThread.isAlive()){

                                try {
                                    sortingThread.join();
                                }catch (InterruptedException E){}

                            }else{
                                vectorSortedVectors.add(new Vector<>());
                                vectorSortedVectors.get(vectorsCounter).add(mergeSort.getResultMassive().length);
                                vectorSortedVectors.get(vectorsCounter).add((int) mergeSort.getSortTime());

                                drawingComponent.fillDataVector(vectorSortedVectors);
                                myTableModel.setData(vectorSortedVectors);
                                myTableModel.fireTableStructureChanged();
                                mainFrame.repaint();
                            }
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

    public JButton getPaintGraphicsButton(){
        return paintGraphicsButton;
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.buildMainFrame();
        mainFrame.getPaintGraphicsButton().doClick();
    }
}
