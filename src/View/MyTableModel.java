package View;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class MyTableModel extends AbstractTableModel{

     private int rowCount = 0;
     private final int ELEMENTS_AMOUNT = 0;
     private final int TIME = 1;
     private Vector<Vector<Integer>> dataVector;

     public MyTableModel(Vector<Vector<Integer>> dataVector){
         this.dataVector = dataVector;
         rowCount = dataVector.size();
     }

     public void setData(Vector<Vector<Integer>> dataVector){
         this.dataVector = dataVector;
         rowCount = dataVector.size();
     }

     public int getRowCount(){
         return rowCount;
     }

     public int getColumnCount(){
         return 2;
     }

     public String getColumnName(int column){
         switch (column){
             case ELEMENTS_AMOUNT:
                 return "N";
             case TIME:
                 return "T";
             default:
                 return "";
         }
     }

     public Object getValueAt(int row, int column){

         Vector<Integer> currentRow = dataVector.get(row);

         switch(column){
             case ELEMENTS_AMOUNT:
                 return currentRow.get(ELEMENTS_AMOUNT);
             case TIME:
                 return currentRow.get(TIME);
             default:
                 return "";
         }
     }
}
