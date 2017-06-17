package Controller;

import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

public class MergeSort implements Runnable{

    private int massiveSize = 1;
    private MassiveGenerator massiveGenerator = new MassiveGenerator();
    private SortingTime sortingTime = new SortingTime();

    SortingTime getSortingTime() {
        return sortingTime;
    }

    MassiveGenerator getMassiveGenerator(){
        return massiveGenerator;
    }

    public void run(){

        for(int sameElementsAmountMassiveCounter = 0; sameElementsAmountMassiveCounter<200; sameElementsAmountMassiveCounter++) {
            massiveGenerator.generateMassives(massiveSize);
            sortingTime.setStartTime();
            sortMassive(massiveGenerator.getFirstMassive(), 0, massiveGenerator.getFirstMassive().length - 1);
            sortMassive(massiveGenerator.getSecondMassive(), 0, massiveGenerator.getSecondMassive().length - 1);
            mergeMassives();
            sortingTime.setFinishTime();
            sortingTime.addTimeToVector(sortingTime.setSortingTime());
        }

        sortingTime.setRealSortTime();
        sortingTime.clearSortTimeVector();

        massiveSize++;
    }

    void renewMassiveSize(){
        massiveSize = 1;
    }

    private void sortMassive(int[] currentMassive, int lowestIndex, int highestIndex){

        if(highestIndex <= lowestIndex)
            return;

        int midIndex = lowestIndex + (highestIndex - lowestIndex)/2;
        sortMassive(currentMassive, lowestIndex, midIndex);
        sortMassive(currentMassive, midIndex + 1, highestIndex);

        int[] bufferMassive = Arrays.copyOf(currentMassive, currentMassive.length);

        System.arraycopy(currentMassive, lowestIndex, bufferMassive, lowestIndex, highestIndex + 1 - lowestIndex);

        int firstCounter = lowestIndex, secondCounter = midIndex + 1;

        for (int resultCounter = lowestIndex; resultCounter <= highestIndex; resultCounter++) {

            if (firstCounter > midIndex) {
                currentMassive[resultCounter] = bufferMassive[secondCounter];
                secondCounter++;
            } else if (secondCounter > highestIndex) {
                currentMassive[resultCounter] = bufferMassive[firstCounter];
                firstCounter++;
            } else if (bufferMassive[secondCounter] < bufferMassive[firstCounter]) {
                currentMassive[resultCounter] = bufferMassive[secondCounter];
                secondCounter++;
            } else {
                currentMassive[resultCounter] = bufferMassive[firstCounter];
                firstCounter++;
            }
        }
    }

    private void mergeMassives(){

        int firstCounter=0, secondCounter=0;
        for (int resultCounter=0; resultCounter<massiveGenerator.getResultMassive().length; resultCounter++) {

            if (firstCounter > massiveGenerator.getFirstMassive().length-1) {
                int a = massiveGenerator.getSecondMassive()[secondCounter];
                massiveGenerator.getResultMassive()[resultCounter] = a;
                secondCounter++;
            }
            else if (secondCounter > massiveGenerator.getSecondMassive().length-1) {
                int a = massiveGenerator.getFirstMassive()[firstCounter];
                massiveGenerator.getResultMassive()[resultCounter] = a;
                firstCounter++;
            }
            else if (massiveGenerator.getFirstMassive()[firstCounter] < massiveGenerator.getSecondMassive()[secondCounter]) {
                int a = massiveGenerator.getFirstMassive()[firstCounter];
                massiveGenerator.getResultMassive()[resultCounter] = a;
                firstCounter++;
            }
            else {
                int b = massiveGenerator.getSecondMassive()[secondCounter];
                massiveGenerator.getResultMassive()[resultCounter] = b;
                secondCounter++;
            }
        }
    }

}
