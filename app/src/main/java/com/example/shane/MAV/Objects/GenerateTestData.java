package com.example.shane.MAV.Objects;

/**
 * Created by Shane on 16/03/2017.
 */

public class GenerateTestData {

    private int howManyRows;

    public GenerateTestData(int howManyRows){
        howManyRows = this.howManyRows;
    }

    public int[] CreateSimpleData(int percentagePositive){

        int returnedArray[] = new int[howManyRows];

        for(int i = 0; i < howManyRows; i++){
            int randomNumber = (int)Math.random() * 100;
            if(randomNumber > percentagePositive)
                returnedArray[i] = 1;
            else
                returnedArray[i] = 0;
        }
        return returnedArray;
    }

    public int[] CreateRandomRangedData(int lowerBound, int higherBound){

        int returnedArray[] = new int[howManyRows];

        for(int i = 0; i < howManyRows; i++){
            int randomNumber = (int)Math.random() * higherBound + lowerBound;
            returnedArray[i] = randomNumber;
        }
        return returnedArray;
    }
}
