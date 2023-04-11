import java.util.Scanner;

public class Main
{
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static String currentPlayer;

    public static void main(String[] args)
    {
        clearBoard();
        currentPlayer = "X";
        boolean gameover = false;

        while (!gameover)
        {
            display();
            System.out.println("Player " + currentPlayer + ", enter your move (row [1-3] and column [1-3]): ");
            int row = getPlayerMove("row") - 1;
            int col = getPlayerMove("column") - 1;

            while (!isValidMove(row, col))
            {
                System.out.println("Invalid move. Please choose an empty cell.");
                display();
                row = getPlayerMove("row") - 1;
                col = getPlayerMove("column") - 1;
            }

            board[row][col] = currentPlayer;

            if (isWin(currentPlayer))
            {
                display();
                System.out.println("Player " + currentPlayer + " wins!");
                gameover = true;
            } else if (isTie())
            {
                display();
                System.out.println("It's a tie!");
                gameover = true;
            }
                else
            {
                currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
            }
        }
    }

    private static void clearBoard()
    {
        for (int i = 0; i < ROW; i++)
        {
            for (int j = 0; j < COL; j++)
            {
                board[i][j] = " ";
            }
        }
    }

    private static void display()
    {
        for (int i = 0; i < ROW; i++)
        {
            for (int j = 0; j < COL; j++)
            {
                System.out.print(board[i][j]);
                if (j < COL - 1)
                {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < ROW - 1)
            {
                System.out.println("---------");
            }
        }
    }

    private static boolean isValidMove(int row, int col)
    {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player)
    {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player)
    {
        for (int i = 0; i < ROW; i++)
        {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player)
    {
        for (int i = 0; i < COL; i++)
        {
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player)
    {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie()
    {
        for (int i = 0; i < ROW; i++)
        {
            for (int j = 0; j < COL; j++)
            {
                if (board[i][j].equals(" "))
                {
                    return false;
                }
            }
        }
        return true;
    }

    private static int getPlayerMove(String coord)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter " + coord + " [1-3]: ");
        while (!scanner.hasNextInt())
        {
            System.out.println("Invalid input. Please enter a valid " + coord + " [1-3]: ");
            scanner.next();
        }
        int move = scanner.nextInt();
        while (move < 1 || move > 3)
        {
            System.out.println("Invalid " + coord + ". Please enter a valid " + coord + " [1-3]: ");
            while (!scanner.hasNextInt())
            {
                System.out.println("Invalid input. Please enter a valid " + coord + " [1-3]: ");
                scanner.next();
            }
            move = scanner.nextInt();
        }
        return move;
    }
}

