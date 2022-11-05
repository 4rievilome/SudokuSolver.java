public class SudokuSolver {

    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {

        int [][] board = {
                {7, 0, 2, 0, 5, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {1, 0, 0, 0, 0, 9, 5, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 4, 3, 0, 0, 0, 7, 5, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };

        printBoard(board);

        if(solveBoard(board)) {
            System.out.println("A resolução foi um sucesso!");
        }
        else {
            System.out.println("Impossível de resolver :(");
        }

        printBoard(board);
    }

    private static void printBoard(int[][] board) {
        for (int row =0; row < GRID_SIZE; row++) {
            if(row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int column = 0; column < GRID_SIZE; column++) {
                if(column % 3 == 0 && row != 0) {
                    System.out.println("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

    /**
     *
     * @param board próprio quadro bidimensional chamado board
     * @param number número a ser checado se existe na fileira
     * @param row a própria fileira que neste caso vai de 0 a 8 já que estamos lidando com array
     *
     * @returntrue se o quadro naquela fileira em que o código está sendo passado na coluna i que estamos iterando
     * e o número checado for encontrado, retorna true
     * @returnfalse se o código passar pelo loop do for e nunca encontrar o número, retorna-se false
     */
    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if(board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param board é a prórpia borda bidimensional chamada board
     * @param number número a ser checado se existe na fileira
     * @param column a própria coluna
     */
    private static boolean isNumberInColumn(int[][] board, int number, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if(board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    /**
     * @for repetição através das três fileiras no quadrado e a mesma coisa para as colunas
     * @for2 nos leva exatamente para o quadrado 3x3 que bucamos
     */
    private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
        //ENCONTRANDO A LOCALIZAÇÃO
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for(int i = localBoxRow; i < localBoxRow + 3; i++) {
            for(int j = localBoxColumn; j <localBoxColumn + 3; j++) {
                if(board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Os três métodos anteriores são chamados, e se todos eles retornarem falso, saberemos que essa é uma
     * colocação válida
     */
    private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return !isNumberInRow(board, number, row) && !isNumberInColumn(board, number, column) && !isNumberInBox(board, number, row, column);
    }

    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                if (board[row][column] == 0) {
                    for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
                        if (isValidPlacement(board, numberToTry, row, column)) {
                            board[row][column] = numberToTry;

                            if (solveBoard(board)) {
                                return true;
                            } else {
                                //Limpa o número caso não consega um lugar para colocar
                                board[row][column] = 0;
                            }
                        }
                    }
                    //No caso do solveBoard passar por todos os quadrados e não encontrar uma colocação válida para colocar os números, simplesmente não pode resolvê-lo
                    return false;
                }
            }
        }
        return true;
    }
}
