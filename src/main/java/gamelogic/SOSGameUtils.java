package gamelogic;

public class SOSGameUtils {

    public static int convertTwoDIndexToOneD(int x, int y, int arraySize) {
        return x * arraySize + y;
    }
    public static int[] convertOneDIndexToTwoD(int index, int arraySize) {
        return new int[] {index / arraySize, index % arraySize};
    }

    /*
    00, 01, 02, 03, 04,
    05, 06, 07, 08, 09,
    10, 11, 12, 13, 14,
    15, 16, 17, 18, 19,
    20, 21, 22, 23, 24
     */

}
