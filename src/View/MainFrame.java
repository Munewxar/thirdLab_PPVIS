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
    private DrawingComponent drawingComponent = new DrawingComponent();
    private JScrollPane drawScrollPane = new JScrollPane(drawingComponent);
    private JPanel drawInstrumentsPanel = new JPanel();
    private JLabel zoomLabel = new JLabel("zoom: x1");
    private MyTableModel myTableModel = new MyTableModel(vectorSortedVectors);
    private JTable table = new JTable(myTableModel);
    private JScrollPane tableScrollPane = new JScrollPane(table);
    private JTextField amountOfVectorsTextField = new JTextField();

    private int vectorsCounter = 0;

    public Vector<Vector<Integer>> getVectorSortedVectors() {
        return vectorSortedVectors;
    }

    public JScrollPane getDrawScrollPane(){
        return drawScrollPane;
    }

    public void setZoomLabel(int zoom){
        zoomLabel.setText("zoom: " + zoom);
    }

    private void generateTableInfo(int vectorsNumber) {

        for (vectorsCounter = 0; vectorsCounter < vectorsNumber; vectorsCounter++) {

            MergeSort mergeSort = new MergeSort();
            vectorSortedVectors.add(new Vector<>());
            vectorSortedVectors.get(vectorsCounter).add(mergeSort.getResultMassive().length);
            vectorSortedVectors.get(vectorsCounter).add((int) mergeSort.getSortTime());

            drawingComponent.addData(vectorSortedVectors);
            myTableModel.setData(vectorSortedVectors);
            myTableModel.fireTableStructureChanged();
            mainFrame.repaint();

        }

        drawingComponent.scale(200);
    }

    private JFrame buildMainFrame() {

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setSize(1200, 540);

        JPanel panelForTableAndGraphics = new JPanel();
        panelForTableAndGraphics.setLayout(new GridLayout());
        panelForTableAndGraphics.add(tableScrollPane);
        panelForTableAndGraphics.add(drawScrollPane);

        drawScrollPane.getViewport().setBackground(Color.WHITE);

        mainFrame.add(panelForTableAndGraphics);

        paintGraphicsButton.addActionListener(new paintGraphicsButtonActionListener());

        amountOfVectorsTextField.setPreferredSize(new Dimension(100, 30));
        paintGraphicsButton.setPreferredSize(new Dimension(70, 28));
        zoomLabel.setPreferredSize(new Dimension(70, 30));
        drawInstrumentsPanel.setPreferredSize(new Dimension(300, 35));
        drawInstrumentsPanel.add(amountOfVectorsTextField);
        drawInstrumentsPanel.add(paintGraphicsButton);
        drawInstrumentsPanel.add(zoomLabel);

        mainFrame.add(drawInstrumentsPanel);

        mainFrame.setVisible(true);
        return mainFrame;
    }

    public class paintGraphicsButtonActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            vectorSortedVectors.clear();
            drawingComponent.clearData();
            generateTableInfo(Integer.valueOf(amountOfVectorsTextField.getText()));
        }
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.buildMainFrame();
        mainFrame.generateTableInfo(5);
    }
}
