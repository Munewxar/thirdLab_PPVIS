package Controller;


import java.util.Random;

class MassiveGenerator {

    private int[] firstMassive;
    private int[] secondMassive;
    private int[] resultMassive;

    void generateMassives(int massiveSize){
        Random random = new Random();

        firstMassive = new int[massiveSize];
        secondMassive = new int[massiveSize];
        resultMassive = new int[massiveSize * 2];

        for(int elementsCounter = 0; elementsCounter<massiveSize; elementsCounter++){
            firstMassive[elementsCounter] = random.nextInt(1000);
        }

        for(int elementsCounter = 0; elementsCounter<massiveSize; elementsCounter++){
            secondMassive[elementsCounter] = random.nextInt(5000);
        }
    }

    int[] getFirstMassive(){
        return firstMassive;
    }

    int[] getSecondMassive(){
        return secondMassive;
    }

    int[] getResultMassive(){
        return resultMassive;
    }
}
