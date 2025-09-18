package sabishiikoto.tictactoe;

import java.util.*;

public class Functions {
    // Check if win in general
    public static boolean win(char[][] map, char letter, int length){
        if (horizontalWin(map, letter, length)){
            return true;
        }
        else if (verticalWin(map, letter, length)){
            return true;
        }
        else if (downwardSlopeWin(map, letter, length)){
            return true;
        }
        else if (upwardSlopeWin(map, letter, length)){
            return true;
        }
        return false;
        
    }
    // Check horizontal line of the map
    public static boolean horizontalWin(char[][] map, char letter, int length){
        int len = map.length;
        int limit = len - length; // Set the limit so that if the column passes the limit and count is still 0, it moves to the next row.
        for (int row = 0; row < len; row++){
            int count = 0;
            for (int column = 0; column < len; column++){
                if (map[row][column] == letter){
                    count++;
                }
                else{
                    count = 0;
                }
                if (column == limit && count == 0){
                    break;
                }
                else if (count == length){
                    return true;
                }
            }

        }
        return false;
    }
    // Check the vertical line of the map
    public static boolean verticalWin(char[][] map, char letter, int length){
        int len = map.length;
        int limit = len - length; // Set the limit so that if the row passes the limit and count is still 0, it moves to the next column.
        for (int column = 0; column < map.length; column ++){
            int count = 0;
            for (int row = 0; row < map.length; row ++){
                if (map[row][column] == letter){
                    count++;
                }
                else{
                    count = 0;
                }
                if (row == limit && count == 0){
                    break;
                }
                else if (count == length){
                    return true;
                }
            }
        }
        return false;
    }

    // Check the slope of the map
    public static boolean upwardSlopeWin(char[][] map, char letter, int length) {
        int len = map.length;
        int loop = len - length + 1;
        int count = 0;
        int[][] list = new int[loop*2 - 1][2];

        // Create a list of starting point (row, column) to check later
        for (int i = 0; i < loop; i++){
            int row = i;
            int column = (len - 1) - i;
            if (row == 0 && column == len - 1){
                int[] tempList = {row,column};
                list[count] = tempList;
            }
            else{
                int[] tempList = {0,column};
                list[count] = tempList.clone();
                count++;
                tempList[0] = row;
                tempList[1] = len - 1;
                list[count] = tempList.clone();
            }
            count++;
        }
        // Loop through each starting point
        for (int[] smallList : list){
            count = 0;
            int limit;
            int row = smallList[0];
            int column = smallList[1];
            limit = len - column;
            // Loop through the slope
            for (row = smallList[0], column = smallList[1]; row < len && column >= 0; row++, column--) {
                if (map[row][column] == letter) {
                    count++;
                }
                if(column <= row){
                    if (column == limit && count == 0) {
                        break;
                    }
                }
                else{
                    if (row == limit && count == 0) {
                        break;
                    }
                }
                if (count == length) {
                    return true;
                }
            }

        }
        return false;

    }
    public static boolean downwardSlopeWin(char[][] map, char letter, int length){
        int len = map.length;
        int loop = len - length + 1;
        int count = 0;
        int[][] list = new int[loop*2 - 1][2];
        for (int i = 0; i < loop; i++){
            if (i == 0){
                int[] tempList = {i,i};
                list[count] = tempList;
            }
            else{
                int[] tempList = {0,i};
                list[count] = tempList.clone();
                count++;
                tempList[0] = i;
                tempList[1] = 0;
                list[count] = tempList.clone();
            }
            count++;
        }
        for (int[] smallList : list){
            count = 0;
            int limit;
            int row = smallList[0];
            int column = smallList[1];
            if (row >= column){
                limit = len - row - length;
            }
            else{
                limit = len - column - length;
            }
            for (row = smallList[0], column = smallList[1]; row < len && column < len; row++, column++) {
                if (map[row][column] == letter) {
                    count++;
                }
                if(column <= row){
                    if (column == limit && count == 0) {
                        break;
                    }
                }
                else{
                    if (row == limit && count == 0) {
                        break;
                    }
                }
                if (count == length) {
                    return true;
                }
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
        if (list.size() == 1){
            return list.getFirst();
        }
        else {
            int index = random.nextInt(0,list.size());
            return list.get(index);
        }
    }


    public static int[] blockingPlayer(char[][] map, char botLetter, int length){
        char letter = '\u0000';
        if (botLetter == 'o'){
            letter = 'x';
        }
        else{
            letter = 'o';
        }
        int len = map.length;
        int[] result = null;
        if (botVerticalCheck(map, letter, length) != null){
            ArrayList<int[]> winRange = botVerticalCheck(map, letter, length);
            int[] first = winRange.getFirst();
            int[] last = winRange.getLast();
            if (first[0] > 0){
                int row = first[0];
                int column = first[1];
                char Char = map[row - 1][column];
                if (Char == '\u0000'){
                    result = new int[2];
                    result[0] = row - 1;
                    result[1] = column;
                    return result;
                }
            }
            if (last[0] < len - 1){
                int row = last[0];
                int column = last[1];
                char Char = map[row + 1][column];
                if (Char == '\u0000'){
                    result = new int[2];
                    result[0] = row + 1;
                    result[1] = column;
                    return result;
                }
            }
        }
        if (botHorizontalCheck(map, letter, length) != null){
            ArrayList<int[]> winRange = botHorizontalCheck(map, letter, length);
            int[] first = winRange.getFirst();
            int[] last = winRange.getLast();
            if (first[1] > 0){
                int row = first[0];
                int column = first[1];
                char Char = map[row][column - 1];
                if (Char == '\u0000'){
                    result = new int[2];
                    result[0] = row;
                    result[1] = column - 1;
                    return result;
                }
            }
            if (last[1] < len - 1){
                int row = last[0];
                int column = last[1];
                char Char = map[row][column + 1];
                if (Char == '\u0000'){
                    result = new int[2];
                    result[0] = row;
                    result[1] = column + 1;
                    return result;
                }
            }
        }
        if (botDownwardSlopeCheck(map, letter, length) != null){
            ArrayList<int[]> winRange = botDownwardSlopeCheck(map, letter, length);
            int[] first = winRange.getFirst();
            int[] last = winRange.getLast();
            if (first[1] > 0 && first[0] > 0){
                int row = first[0];
                int column = first[1];
                char Char = map[row - 1][column - 1];
                if (Char == '\u0000'){
                    result = new int[2];
                    result[0] = row - 1;
                    result[1] = column - 1;
                    return result;
                }
            }
            if (last[1] < len - 1 && last[0] < len - 1){
                int row = last[0];
                int column = last[1];
                char Char = map[row + 1][column + 1];
                if (Char == '\u0000'){
                    result = new int[2];
                    result[0] = row + 1;
                    result[1] = column + 1;
                    return result;
                }
            }
        }
        if (botUpwardSlopeCheck(map, letter, length) != null){
            ArrayList<int[]> winRange = botUpwardSlopeCheck(map, letter, length);
            int[] first = winRange.getFirst();
            int[] last = winRange.getLast();
            if (first[1] < len - 1 && first[0] > 0){
                int row = first[0];
                int column = first[1];
                char Char = map[row - 1][column + 1];
                if (Char == '\u0000'){
                    result = new int[2];
                    result[0] = row - 1;
                    result[1] = column + 1;
                    return result;
                }
            }
            if (last[1] > 0 && last[0] < len - 1){
                int row = last[0];
                int column = last[1];
                char Char = map[row + 1][column - 1];
                if (Char == '\u0000'){
                    result = new int[2];
                    result[0] = row + 1;
                    result[1] = column - 1;
                    return result;
                }
            }
        }
        return null;
    }

    public static ArrayList<int[]> botHorizontalCheck(char[][] map, char letter, int length){
        int len = map.length;
        int limit = len - length + 1; // Set the limit so that if the column passes the limit and count is still 0, it moves to the next row.
        for (int row = 0; row < len; row++){
            int count = 0;
            ArrayList<int[]> list = new ArrayList<>();
            for (int column = 0; column < len; column++){
                if (map[row][column] == letter){
                    int[] tempList = new int[2];
                    tempList[0] = row;
                    tempList[1] = column;
                    list.add(tempList);
                    count++;
                }
                else{
                    count = 0;
                }
                if (column == limit && count == 0){
                    break;
                }
                else if (count == length - 1){
                    return list;
                }
            }

        }
        return null;
    }
    public static ArrayList<int[]> botVerticalCheck(char[][] map, char letter, int length){
        int len = map.length;
        int limit = len - length + 1; // Set the limit so that if the row passes the limit and count is still 0, it moves to the next column.
        for (int column = 0; column < map.length; column ++){
            int count = 0;
            ArrayList<int[]> list = new ArrayList<>();
            for (int row = 0; row < map.length; row ++){
                if (map[row][column] == letter){
                    int[] tempList = new int[2];
                    tempList[0] = row;
                    tempList[1] = column;
                    list.add(tempList);
                    count++;
                }
                else{
                    count = 0;
                }
                if (row == limit && count == 0){
                    break;
                }
                else if (count == length - 1){
                    return list;
                }
            }
        }
        return null;
    }
    public static ArrayList<int[]> botUpwardSlopeCheck(char[][] map, char letter, int length){
        int len = map.length;
        int loop = len - length + 1;
        int count = 0;
        int[][] list = new int[loop*2 - 1][2];

        // Create a list of starting point (row, column) to check later
        for (int i = 0; i < loop; i++){
            int row = i;
            int column = (len - 1) - i;
            if (row == 0 && column == len - 1){
                int[] tempList = {row,column};
                list[count] = tempList;
            }
            else{
                int[] tempList = {0,column};
                list[count] = tempList.clone();
                count++;
                tempList[0] = row;
                tempList[1] = len - 1;
                list[count] = tempList.clone();
            }
            count++;
        }
        // Loop through each starting point
        for (int[] smallList : list){
            count = 0;
            int limit;
            int row = smallList[0];
            int column = smallList[1];
            ArrayList<int[]> List = new ArrayList<>();
            limit = len - column + 1;
            // Loop through the slope
            for (row = smallList[0], column = smallList[1]; row < len && column >= 0; row++, column--) {
                if (map[row][column] == letter) {
                    int[] tempList = new int[2];
                    tempList[0] = row;
                    tempList[1] = column;
                    List.add(tempList);
                    count++;
                }
                if(column <= row){
                    if (column == limit && count == 0) {
                        break;
                    }
                }
                else{
                    if (row == limit && count == 0) {
                        break;
                    }
                }
                if (count == length - 1) {
                    return List;
                }
            }

        }
        return null;
    }
    public static ArrayList<int[]> botDownwardSlopeCheck(char[][] map, char letter, int length){
        int len = map.length;
        int loop = len - length + 1;
        int count = 0;
        int[][] list = new int[loop*2 - 1][2];
        for (int i = 0; i < loop; i++){
            if (i == 0){
                int[] tempList = {i,i};
                list[count] = tempList;
            }
            else{
                int[] tempList = {0,i};
                list[count] = tempList.clone();
                count++;
                tempList[0] = i;
                tempList[1] = 0;
                list[count] = tempList.clone();
            }
            count++;
        }
        for (int[] smallList : list){
            count = 0;
            int limit;
            int row = smallList[0];
            int column = smallList[1];
            ArrayList<int[]> List = new ArrayList<>();
            if (row >= column){
                limit = len - row - length + 1;
            }
            else{
                limit = len - column - length + 1;
            }
            for (row = smallList[0], column = smallList[1]; row < len && column < len; row++, column++) {
                if (map[row][column] == letter) {
                    int[] tempList = new int[2];
                    tempList[0] = row;
                    tempList[1] = column;
                    List.add(tempList);
                    count++;
                }
                if(column <= row){
                    if (column == limit && count == 0) {
                        break;
                    }
                }
                else{
                    if (row == limit && count == 0) {
                        break;
                    }
                }
                if (count == length - 1) {
                    return List;
                }
            }

        }
        return null;
    }
}
