import java.util.Scanner;

public class JogoDaVelha {

    // Função para imprimir o tabuleiro
    public static void imprimirTabuleiro(char[][] tabuleiro) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("---------");
        }
    }

    // Função para verificar se algum jogador ganhou
    public static boolean verificarVitoria(char[][] tabuleiro, char jogador) {
        // Verifica as linhas, colunas e diagonais
        for (int i = 0; i < 3; i++) {
            // Verifica as linhas
            if (tabuleiro[i][0] == jogador && tabuleiro[i][1] == jogador && tabuleiro[i][2] == jogador) {
                return true;
            }
            // Verifica as colunas
            if (tabuleiro[0][i] == jogador && tabuleiro[1][i] == jogador && tabuleiro[2][i] == jogador) {
                return true;
            }
        }
        // Verifica as diagonais
        if (tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador) {
            return true;
        }
        if (tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador) {
            return true;
        }

        return false;
    }

    // Função para verificar se o tabuleiro está cheio
    public static boolean tabuleiroCheio(char[][] tabuleiro) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] tabuleiro = new char[3][3];
        Scanner scanner = new Scanner(System.in);
        boolean jogoEmAndamento = true;
        char jogadorAtual = 'X';

        // Inicializando o tabuleiro com espaços vazios
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = ' ';
            }
        }

        while (jogoEmAndamento) {
            imprimirTabuleiro(tabuleiro);
            System.out.println("Jogador " + jogadorAtual + ", escolha a linha e a coluna (1-3 para ambos):");
            System.out.print("Linha (1-3): ");
            int linha = scanner.nextInt() - 1;  // Subtrai 1 para indexar corretamente
            System.out.print("Coluna (1-3): ");
            int coluna = scanner.nextInt() - 1;  // Subtrai 1 para indexar corretamente

            // Verifica se a posição está dentro dos limites
            if (linha < 0 || linha > 2 || coluna < 0 || coluna > 2) {
                System.out.println("Posição inválida! Escolha valores entre 1 e 3 para linha e coluna.");
                continue;
            }

            // Verifica se a posição está ocupada
            if (tabuleiro[linha][coluna] != ' ') {
                System.out.println("Posição já ocupada, tente novamente.");
                continue;
            }

            // Marca a posição escolhida pelo jogador
            tabuleiro[linha][coluna] = jogadorAtual;

            // Verifica se o jogador ganhou
            if (verificarVitoria(tabuleiro, jogadorAtual)) {
                imprimirTabuleiro(tabuleiro);
                System.out.println("Jogador " + jogadorAtual + " venceu!");
                jogoEmAndamento = false;
            } else if (tabuleiroCheio(tabuleiro)) {
                imprimirTabuleiro(tabuleiro);
                System.out.println("Empate!");
                jogoEmAndamento = false;
            } else {
                // Alterna o jogador
                jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
            }
        }

        scanner.close();
    }
}
