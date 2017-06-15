package Controller;

import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

public class MergeSort implements Runnable{

    private int[] firstMassive;
    private int[] secondMassive;
    private int[] resultMassive;
    private long sortTime = 0;
    private Vector<Long> sortTimeVector = new Vector<>();
    private int massiveSize = 1;

    public void run(){
        long startTime;
        long finishTime;

        for(int sameElementsAmountMassiveCounter = 0; sameElementsAmountMassiveCounter<4; sameElementsAmountMassiveCounter++) {
            generateMassives();
            startTime = System.nanoTime();
            sortMassive(getFirstMassive(), 0, getFirstMassive().length - 1);
            sortMassive(getSecondMassive(), 0, getSecondMassive().length - 1);
            mergeMassives();
            finishTime = System.nanoTime();
            sortTimeVector.add(finishTime - startTime);
        }

        for(int timeCounter = 0; timeCounter<sortTimeVector.size(); timeCounter++){
            sortTime += sortTimeVector.get(timeCounter);
        }

        sortTime = sortTime/sortTimeVector.size();

        sortTimeVector.clear();
        massiveSize++;
    }

    public long getSortTime(){
        return sortTime;
    }

    private int[] getFirstMassive(){
        return firstMassive;
    }

    private int[] getSecondMassive(){
        return secondMassive;
    }

    public int[] getResultMassive(){
        return resultMassive;
    }

    private void generateMassives(){

        Random random = new Random();

        firstMassive = new int[massiveSize];
        secondMassive = new int[massiveSize];
        int bufferSize = massiveSize;
        resultMassive = new int[bufferSize*2];

        for(int elementsCounter = 0; elementsCounter<massiveSize; elementsCounter++){
            firstMassive[elementsCounter] = random.nextInt(1000);
        }

        for(int elementsCounter = 0; elementsCounter<massiveSize; elementsCounter++){
            secondMassive[elementsCounter] = random.nextInt(5000);
        }


    }

    public void renewMassiveSize(){
        massiveSize = 1;
    }

    public void renewSortTime(){
        sortTime = 0;
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
        for (int resultCounter=0; resultCounter<resultMassive.length; resultCounter++) {

            if (firstCounter > firstMassive.length-1) {
                int a = secondMassive[secondCounter];
                resultMassive[resultCounter] = a;
                secondCounter++;
            }
            else if (secondCounter > secondMassive.length-1) {
                int a = firstMassive[firstCounter];
                resultMassive[resultCounter] = a;
                firstCounter++;
            }
            else if (firstMassive[firstCounter] < secondMassive[secondCounter]) {
                int a = firstMassive[firstCounter];
                resultMassive[resultCounter] = a;
                firstCounter++;
            }
            else {
                int b = secondMassive[secondCounter];
                resultMassive[resultCounter] = b;
                secondCounter++;
            }
        }
    }

}
