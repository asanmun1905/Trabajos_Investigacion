public class Solucion8Reinas {

    public static void main(String[] args) {
        int n = 8;
        int[][] tablero = new int[n][n];
        
        System.out.println("Buscando soluciones para " + n + " reinas...");
        int totalSoluciones = resolver(tablero, 0, n, true);
        
        System.out.println("\nTotal de soluciones encontradas: " + totalSoluciones);
    }

    public static int resolver(int[][] tablero, int fila, int n, boolean imprimir) {
        if (fila == n) {
            if (imprimir) {
                imprimirTablero(tablero);
            }
            return 1;
        }

        int soluciones = 0;

        for (int col = 0; col < n; col++) {
            if (esSeguro(tablero, fila, col, n)) {
                tablero[fila][col] = 1;
                soluciones += resolver(tablero, fila + 1, n, imprimir);
                tablero[fila][col] = 0; 
            }
        }

        return soluciones;
    }

    public static boolean esSeguro(int[][] tablero, int fila, int col, int n) {
        // Vertical
        for (int i = 0; i < fila; i++) {
            if (tablero[i][col] == 1) return false;
        }

        // Diagonal Superior Izquierda
        for (int i = 1; i <= fila && col - i >= 0; i++) {
            if (tablero[fila - i][col - i] == 1) return false;
        }

        // Diagonal Superior Derecha
        for (int i = 1; i <= fila && col + i < n; i++) {
            if (tablero[fila - i][col + i] == 1) return false;
        }

        return true;
    }

    public static void imprimirTablero(int[][] tablero) {
        int n = tablero.length;
        
        String RESET = "\u001B[0m";
        String BG_BLANCO = "\u001B[47m";
        String BG_NEGRO = "\u001B[40m";
        String REINA = "\u001B[31;1m♛\u001B[0m";
        String VACIO = " ";

        System.out.println("\nSolución:");
        for (int f = 0; f < n; f++) {
            for (int c = 0; c < n; c++) {
                String bg = ((f + c) % 2 == 0) ? BG_BLANCO : BG_NEGRO;
                System.out.print(bg + " ");
                if (tablero[f][c] == 1) {
                    System.out.print(REINA + bg + " ");
                } else {
                    System.out.print(VACIO + " ");
                }
                System.out.print(RESET);
            }
            System.out.println();
        }
        System.out.println();
    }
}
