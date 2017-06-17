package Controller;

import java.util.Vector;

class SortingTime {

    private long startTime;
    private long finishTime;
    private long realSortTime;
    private final Vector<Long> sortTimeVector = new Vector<>();

    void setStartTime(){
        startTime = System.nanoTime();
    }

    void setFinishTime(){
        finishTime = System.nanoTime();
    }

    long setSortingTime(){
        return finishTime - startTime;
    }

    void addTimeToVector(long sortingTime){
        sortTimeVector.add(sortingTime);
    }

    void clearSortTimeVector(){
        sortTimeVector.clear();
    }

    void setRealSortTime(){
        for (Long aSortTimeVector : sortTimeVector) {
            realSortTime += aSortTimeVector;
        }

        realSortTime = realSortTime / sortTimeVector.size();
    }

    void renewRealSortTime(){
        realSortTime = 0;
    }

    long getRealSortTime(){
        return realSortTime;
    }
}
