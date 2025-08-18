package sabishiikoto.tictactoe;

import java.util.*;

public class Functions {
    // Check if win in general
    public static boolean win(char[][] map, char letter, int length){
        if (horizontalWin(map, letter, length) || verticalWin(map, letter, length)){
            return true;
        }
        else if (downwardSlopeWin(map, letter, length) || upwardSlopeWin(map, letter, length)){
            return true;
        }
        return false;
        
    }
    // Check horizontal line of the map
    public static boolean horizontalWin(char[][] map, char letter, int length){

        for (int row = 0; row < map.length; row++){
            int count = 0;
            for (int column = 0; column < map.length; column++){
                if (map[row][column] == letter){
                    count++;
                }
                else{
                    count = 0;
                }
                if (count == length){
                    return true;
                }
            }

        }
        return false;
    }
    // Check the vertical line of the map
    public static boolean verticalWin(char[][] map, char letter, int length){
        for (int column = 0; column < map.length; column ++){
            int count = 0;
            for (int row = 0; row < map.length; row ++){
                if (map[row][column] == letter){
                    count++;
                }
                else{
                    count = 0;
                }
                if (count == length){
                    return true;
                }
            }
        }
        return false;
    }

    // Check the slope of the map
    public static boolean upwardSlopeWin(char[][] map, char letter, int length) {
        int count = 0;
        for (int row = map.length - 1, column = 0; row >= 0 && column < map.length; row --, column++){
            if (map[row][column] == letter){
                count++;
            }
            else{
                count = 0;
            }
            if (count == length){
                return true;
            }
        }
        return false;

    }
    public static boolean downwardSlopeWin(char[][] map, char letter, int length){
        int count = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i][i] == letter) {
                count++;
            }
            else{
                count = 0;
            }
            if (count == length){
                return true;
            }
        }
        return false;
    }

    public static int randomNumber(int length){
        Random random = new Random();
        return random.nextInt(length);
    }

    public static boolean inGame (char[][] map){
        for (int row = 0; row < map.length; row ++){
            for (int column = 0; column < map.length; column ++){
                if (map[row][column] == '\u0000'){
                    return true;
                }
            }
        }
        return false;
    }

    public static ArrayList<int[]> mapToListForRandom(char[][] map){
        ArrayList<int[]> list = new ArrayList<>();
        for (int row = 0; row < map.length; row ++){
            for (int column = 0; column < map.length; column++){
                if (map[row][column] == '\u0000'){
                    int[] tempList = new int[2];
                    tempList[0] = row;
                    tempList[1] = column;
                    list.add(tempList);
                }
            }
        }
        return list;
    }

    public static int[] randomLocation(char[][] map){
        Random random = new Random();
        ArrayList<int[]> list = mapToListForRandom(map);
        if (list.isEmpty()){
            return null;
        }
        else if (list.size() == 1){
            return list.getFirst();
        }
        else {
            int index = random.nextInt(0,list.size());
            return list.get(index);
        }
    }
}
