package test;
import java.util.ArrayList;
public class Board {
    public Tile[][] board;
    public int[][] specialBoard;
    public static Board checkBoard = null;
    int first;

    public Board() {
        board = new Tile[15][15];
        specialBoard  = new int[15][15];
        first =0;
        for(int i =0 ;i<15;i++){
            for(int j =0;j<15;j++){
                board[i][j] = null;
                specialBoard[i][j] = 0;
            }
        }
        specialBoard[0][0] = 4;
        specialBoard[0][3] = 1;
        specialBoard[0][7] = 4;
        specialBoard[0][11] = 1;
        specialBoard[0][14] = 4;
        specialBoard[1][1] = 3;
        specialBoard[1][5] = 2;
        specialBoard[1][9] = 2;
        specialBoard[1][13] = 3;
        specialBoard[2][2] = 3;
        specialBoard[2][6] = 1;
        specialBoard[2][8] = 1;
        specialBoard[2][12] = 3;
        specialBoard[3][0] = 1;
        specialBoard[3][3] = 3;
        specialBoard[3][7] = 1;
        specialBoard[3][11] = 3;
        specialBoard[3][14] = 1;
        specialBoard[4][4] = 3;
        specialBoard[4][10] = 3;
        specialBoard[5][1] = 2;
        specialBoard[5][5] = 2;
        specialBoard[5][9] = 2;
        specialBoard[5][13] = 2;
        specialBoard[6][2] = 1;
        specialBoard[6][6] = 1;
        specialBoard[6][8] = 1;
        specialBoard[6][12] = 1;
        specialBoard[7][0] = 4;
        specialBoard[7][3] = 1;
        specialBoard[7][11] = 1;
        specialBoard[7][14] = 4;
        specialBoard[8][2] = 1;
        specialBoard[8][6] = 1;
        specialBoard[8][8] = 1;
        specialBoard[8][12] = 1;
        specialBoard[9][1] = 2;
        specialBoard[9][5] = 2;
        specialBoard[9][9] = 2;
        specialBoard[9][13] = 2;
        specialBoard[10][4] = 3;
        specialBoard[10][10] = 3;
        specialBoard[11][0] = 1;
        specialBoard[11][3] = 3;
        specialBoard[11][7] = 1;
        specialBoard[11][11] = 3;
        specialBoard[11][14] = 1;
        specialBoard[12][2] = 3;
        specialBoard[12][6] = 1;
        specialBoard[12][8] = 1;
        specialBoard[12][12] = 3;
        specialBoard[13][1] = 3;
        specialBoard[13][5] = 2;
        specialBoard[13][9] = 2;
        specialBoard[13][13] = 3;
        specialBoard[14][0] = 4;
        specialBoard[14][3] = 1;
        specialBoard[14][7] = 4;
        specialBoard[14][11] = 1;
        specialBoard[14][14] = 4;
    }
    public Tile[][] getTiles(){
        return board.clone();
    }
    public static Board getBoard(){
        if(checkBoard == null){
            checkBoard = new Board();
        }
        return checkBoard;
    }
    public int tryPlaceWord(Word wod){
        if(!boardLegal(wod)){return 0;}
        if(!wod.vertical){
            int z = 0 ;
            for(int i = wod.col;i<wod.col+wod.tiles.length;i++){
                if(wod.tiles[z] != null){
                    board[wod.row][i] = wod.tiles[z];
                }
                z+=1;
            }
        }
        if(wod.vertical){
            int z = 0 ;
            for(int i = wod.row;i<wod.row+wod.tiles.length;i++){
                if(wod.tiles[z] != null){
                    board[i][wod.col] = wod.tiles[z];
                }
                z+=1;
            }
        }
        ArrayList<Word> myWords = getWords(wod);
        int sum = 0;
        for(int i = 0 ; i < myWords.size();i++){
            if(!dictionaryLegal(myWords.get(i))){
                int z = 0;
                if(wod.vertical){
                    for(int j = wod.row;j<wod.row + wod.tiles.length;j++){
                        if(wod.tiles[z] == null){
                            z+=1;
                            continue;}
                        else{
                            z+=1;
                            board[j][wod.col] = null;}
                    }
                }
                if(!wod.vertical){
                    for(int j = wod.col;j<wod.col + wod.tiles.length;j++){
                        if(wod.tiles[z] == null){
                            z+=1;
                            continue;}
                        else{
                            z+=1;
                            board[wod.row][j] = null;}
                    }
                }
                return 0;}
            sum+=getScore(myWords.get(i));
        }
        if(first == 0){first+=1;}
        return sum;
    }
    public boolean boardLegal(Word wod){
        if(first == 0){
            if(!checkFirstStar(wod)){return false;}
        }
        if(!checkIfInside(wod)){return false;}
        if(!overlapping(wod)){return false;}
        if(!checkIfEx(wod)){return false;}
        return true;
    }
    public boolean dictionaryLegal(Word wod){return true;}
    public ArrayList<Word> getWords(Word wod) {
        int ncol;
        int nrow;
        ArrayList<Word> wordlist = new ArrayList<>();
        if (wod.vertical) {
            int i;
            int j;
            Tile[] ftiles = new Tile[wod.tiles.length];
            i = wod.row;
            j =0;
            while(j<wod.tiles.length){
                if(wod.tiles[j] != null){ftiles[j] = wod.tiles[j];}
                else{ftiles[j] = board[i][wod.col];}
                j+=1;
                i+=1;
            }
                wordlist.add(new Word(ftiles,wod.row,wod.col,true));
        }
        if (!wod.vertical) {
            int i;
            int j;
            Tile[] ftiles = new Tile[wod.tiles.length];
            i = wod.col;
            j =0;
            while(j<wod.tiles.length){
                if(wod.tiles[j] != null){ftiles[j] = wod.tiles[j];}
                else{ftiles[j] = board[wod.row][i];}
                j+=1;
                i+=1;
            }
                wordlist.add(new Word(ftiles,wod.row,wod.col,false));
        }
        int x =0;
        if (wod.vertical) {
            for (int i = wod.row; i < wod.row + wod.tiles.length - 1; i++) {
                if(wod.tiles[x] == null){
                    x+=1;
                    continue;}
                x+=1;
                if (wod.col == 14) {
                    if (board[i][wod.col - 1] != null) {
                        int j = wod.col - 1;
                        int z = 0;
                        while (board[i][j - 1] != null && j - 1 >= 0) {
                            j -= 1;
                        }
                        int f = wod.col;
                        while (board[i][f+1] != null && f + 1 <= 14) {
                            f += 1;
                        }
                        Tile[] ntile = new Tile[f + 1 - j];
                        nrow = i;
                        ncol = j;
                        while (board[i][j + 1] != null && j + 1 <= 14) {
                            ntile[z] = board[i][j];
                            z += 1;
                            j += 1;
                        }
                        wordlist.add(new Word(ntile, nrow, ncol, false));
                        continue;
                    }
                }
                if (wod.col == 0) {
                    if (board[i][wod.col + 1] != null) {
                        int j = wod.col + 1;
                        int z = 0;
                        while (board[i][j + 1] != null && j + 1 <= 14) {
                            j += 1;
                        }
                        Tile[] ntile = new Tile[j + 1];
                        nrow = i;
                        ncol = 0;
                        j = wod.col;
                        while (board[i][j + 1] != null && j + 1 <= 14) {
                            ntile[z] = board[i][j];
                            z += 1;
                            j += 1;
                        }
                        wordlist.add(new Word(ntile, nrow, ncol, false));
                    }
                } else {
                    if (board[i][wod.col - 1] != null || board[i][wod.col + 1] != null) {
                        if(board[i][wod.col] != null){continue;}
                        int j = wod.col - 1;
                        int z = 0;
                        while (board[i][j - 1] != null && j - 1 >= 0) {
                            j -= 1;
                        }
                        int f = wod.col;
                        while (board[i][f+1] != null && f + 1 <= 14) {
                            f += 1;
                        }
                        Tile[] ntile = new Tile[f + 1 - j];
                        nrow = i;
                        ncol = j;
                        while (board[i][j + 1] != null && j + 1 <= 14) {
                            ntile[z] = board[i][j];
                            z += 1;
                            j += 1;
                        }
                        wordlist.add(new Word(ntile, nrow, ncol, false));
                    }
                }
            }
        }
        if (!wod.vertical) {
            for (int i = wod.col; i < wod.col + wod.tiles.length; i++) {
                if(wod.tiles[x] == null){
                    x+=1;
                    continue;}
                x+=1;
                if (wod.row == 14) {
                    if (board[wod.row - 1][i] != null) {
                        int j = wod.row - 1;
                        int z = 0;
                        while (board[j - 1][i] != null && j - 1 >= 0) {
                            j -= 1;
                        }
                        int f = wod.row;
                        while (board[f+1][i] != null && f + 1 <= 14) {
                            f += 1;
                        }
                        Tile[] ntile = new Tile[f + 1 - j];
                        nrow = j;
                        ncol = i;
                        while (board[j + 1][i] != null && j + 1 <= 14) {
                            ntile[z] = board[j][i];
                            z += 1;
                            j += 1;
                        }
                        wordlist.add(new Word(ntile, nrow, ncol, true));
                        continue;
                    }
                }
                if (wod.row == 0) {
                    if (board[wod.row + 1][i] != null) {
                        int j = wod.row;
                        int z = 0;
                        while (board[j + 1][i] != null && j + 1 <= 14) {
                            j += 1;
                        }
                        Tile[] ntile = new Tile[j + 1];
                        nrow = 0;
                        ncol = i;
                        j = wod.row;
                    while (board[j + 1][i] != null && j + 1 <= 14) {
                        ntile[z] = board[j][i];
                        z += 1;
                        j += 1;
                    }
                    wordlist.add(new Word(ntile, nrow, ncol, true));
                    }
                } else {
                    if (board[wod.row - 1][i] != null || board[wod.row + 1][i] != null) {
                        int j = wod.row;
                        int z = 0;
                        if (board[wod.row - 1][i] != null) {
                            while (board[j - 1][i] != null && j - 1 >= 0) {
                                j -= 1;
                            }
                        }
                        int f = wod.row;
                        while (board[f+1][i] != null && f + 1 <= 14) {
                            f += 1;
                        }
                        Tile[] ntile = new Tile[f + 1 - j];
                        nrow = j;
                        ncol = i;
                        while (board[j][i] != null && j + 1 <= 14) {
                            ntile[z] = board[j][i];
                            z += 1;
                            j += 1;
                        }
                        wordlist.add(new Word(ntile, nrow, ncol, true));
                    }
                }
            }
        }
        return wordlist;
    }
    public int getScore(Word wod){
        int sumoftheword =0;
        int sum=0;
        if(wod.vertical){
            int z = 0;
            for(int i = 0;i<wod.tiles.length;i++){sumoftheword+=wod.tiles[i].score;}
            sum+=sumoftheword;
            for(int i = wod.row;i< wod.row + wod.tiles.length;i++){
                if(specialBoard[i][wod.col]==1){
                    sum+=wod.tiles[z].score;
                }
                if(specialBoard[i][wod.col]==2){
                    sum+=wod.tiles[z].score+wod.tiles[z].score;
                }
                if(specialBoard[i][wod.col]==3){
                    sum+=sumoftheword;
                }
                if(specialBoard[i][wod.col]==4){
                    sum+=sumoftheword+sumoftheword;
                }
                z+=1;
            }
        }
        if(!wod.vertical){
            int z = 0;
            for(int i = 0;i<wod.tiles.length;i++){sumoftheword+=wod.tiles[i].score;}
            sum+=sumoftheword;
            for(int i = wod.col;i< wod.col + wod.tiles.length;i++){
                if(specialBoard[wod.row][i]==1){
                    sum+=wod.tiles[z].score;
                }
                if(specialBoard[wod.row][i]==2){
                    sum+=wod.tiles[z].score+wod.tiles[z].score;
                }
                if(specialBoard[wod.row][i]==3){
                    sum+=sumoftheword;
                }
                if(specialBoard[wod.row][i]==4){
                    sum+=sumoftheword+sumoftheword;
                }
                z+=1;
            }
        }
        if(first == 0){sum += sumoftheword;}
        return sum;
    }
    public boolean checkIfInside(Word wod){
        if (wod.vertical){
            if (wod.row + wod.tiles.length - 1 < 15 && wod.row>=0){
                return true;
            }
        }
        if (!wod.vertical){
            if (wod.col + wod.tiles.length - 1 < 15 && wod.col>=0){
                return true;
            }
        }
        return false;
    }
    public boolean checkFirstStar(Word wod){
        if (wod.vertical){
            if(wod.col != 7){return false;}
            if (wod.row + wod.tiles.length - 1 < 7){
                return false;
            }
        }
        if (!wod.vertical){
            if(wod.row != 7){return false;}
            if(wod.col + wod.tiles.length - 1 < 7){
                return false;
            }
        }
        return true;
    }
    public boolean overlapping(Word wod){
        if(first == 0){return true;}
        if (wod.vertical){
            for(int i =wod.row ;i<wod.row + wod.tiles.length - 1;i++){
                if(wod.col == 14){if(board[i][wod.col-1]!=null){return true;}}
                if(wod.col == 0){if(board[i][wod.col+1]!=null){return true;}}
                else{if(board[i][wod.col-1]!=null || board[i][wod.col+1]!=null){return true;}}
            }
        }
        if (!wod.vertical){
            for(int i =wod.col ;i<wod.col + wod.tiles.length - 1;i++){
                if(wod.row == 14){if(board[wod.row-1][i]!=null){return true;}}
                if(wod.row == 0){if(board[wod.row+1][i]!=null){return true;}}
                else{if(board[wod.row-1][i]!=null || board[wod.row+1][i]!=null){return true;}}
            }
        }
        return false;
    }
    public boolean checkIfEx(Word wod){
        if (wod.vertical){
            int j =0 ;
            for(int i =wod.row ;i<wod.row + wod.tiles.length - 1;i++){
                if(board[i][wod.col]!= null && wod.tiles[j]!=null){
                    return false;
                }
                j+=1;
            }
        }
        if (!wod.vertical){
            int j =0 ;
            for(int i =wod.col ;i<wod.col + wod.tiles.length - 1;i++){
                if(board[wod.row][i]!= null && wod.tiles[j]!=null){
                    return false;
                }
                j+=1;
            }
        }
        return true;
    }
}