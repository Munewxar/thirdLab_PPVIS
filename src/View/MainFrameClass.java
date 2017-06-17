package View;
import Controller.MergeSort;
import Controller.PaintButtonEventAssistant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class MainFrameClass {

    private final JFrame mainFrame = new JFrame("PPVIS #3");
    private final Vector<Vector<Integer>> vectorSortedVectors = new Vector<>();
    private final JButton paintGraphicsButton = new JButton("Paint");
    private final JLabel zoomLabel = new JLabel("zoom: x1");
    private final DrawingComponent drawingComponent = new DrawingComponent(this);
    private final JScrollPane drawScrollPane = new JScrollPane(drawingComponent);
    private final JPanel drawInstrumentsPanel = new JPanel();
    private final MyTableModel myTableModel = new MyTableModel(vectorSortedVectors);
    private final JTable table = new JTable(myTableModel);
    private final JScrollPane tableScrollPane = new JScrollPane(table);
    private final JTextField amountOfVectorsTextField = new JTextField("10");
    private final MergeSort mergeSort = new MergeSort();

    void setZoomLabel(double zoom){
        zoomLabel.setText("zoom x" + zoom);
    }

    public JTextField getAmountOfVectorsTextField() {
        return amountOfVectorsTextField;
    }

    public JFrame getMainFrame(){
        return mainFrame;
    }

    public Vector<Vector<Integer>> getVectorSortedVectors() {
        return vectorSortedVectors;
    }

    private MainFrameClass getMainFrameClass(){
        return this;
    }

    private void buildMainFrame() {

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
    }

    public class paintGraphicsButtonActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            PaintButtonEventAssistant paintButtonEventAssistant = new PaintButtonEventAssistant(getMainFrameClass(),
                    drawingComponent, myTableModel);
            paintButtonEventAssistant.doPaintButtonFunction();
        }
    }

    private JButton getPaintGraphicsButton(){
        return paintGraphicsButton;
    }

    public static void main(String[] args) {
        MainFrameClass mainFrameClass = new MainFrameClass();
        mainFrameClass.buildMainFrame();
        mainFrameClass.getPaintGraphicsButton().doClick();
    }
}
