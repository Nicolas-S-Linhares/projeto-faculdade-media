import java.util.Random;
import java.util.Scanner;

class JogoDaVelha_Mapa {
    private char[][] mapa;

    public JogoDaVelha_Mapa() {
        mapa = new char[3][3];
        limpaMapa();
    }

    public int sortear(int inicio, int fim) {
        Random random = new Random();
        return random.nextInt(fim - inicio + 1) + inicio;
    }

    public void limpaMapa() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mapa[i][j] = ' ';
            }
        }
    }

    public void desenhaMapa() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(mapa[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("---------");
        }
    }

    public boolean jogar(int l, int c, char jogador) {
        if (l >= 0 && l < 3 && c >= 0 && c < 3 && mapa[l][c] == ' ') {
            mapa[l][c] = jogador;
            return true;
        }
        return false;
    }

    public boolean ganhou(char jogador) {
        for (int i = 0; i < 3; i++) {
            if (mapa[i][0] == jogador && mapa[i][1] == jogador && mapa[i][2] == jogador) return true;
            if (mapa[0][i] == jogador && mapa[1][i] == jogador && mapa[2][i] == jogador) return true;
        }
        if (mapa[0][0] == jogador && mapa[1][1] == jogador && mapa[2][2] == jogador) return true;
        if (mapa[0][2] == jogador && mapa[1][1] == jogador && mapa[2][0] == jogador) return true;
        return false;
    }
}

class JogoDaVelha_Jogador {
    private JogoDaVelha_Mapa mapa;
    private char letra;

    public JogoDaVelha_Jogador(JogoDaVelha_Mapa mapa) {
        this.mapa = mapa;
        this.letra = 'X';
    }

    public boolean joga(Scanner teclado) {
        System.out.println("Jogador " + letra + ", escolha a linha e a coluna (1-3 para ambos):");
        System.out.print("Linha: ");
        int linha = teclado.nextInt() - 1;
        System.out.print("Coluna: ");
        int coluna = teclado.nextInt() - 1;

        return mapa.jogar(linha, coluna, letra);
    }
}

class JogoDaVelha_PC {
    private JogoDaVelha_Mapa mapa;
    private char letra = 'O';

    public JogoDaVelha_PC(JogoDaVelha_Mapa mapa) {
        this.mapa = mapa;
    }

    public boolean joga() {
        Random random = new Random();
        int linha, coluna;
        do {
            linha = random.nextInt(3);
            coluna = random.nextInt(3);
        } while (!mapa.jogar(linha, coluna, letra));
        return true;
    }
}

public class JogoDaVelha {
    private JogoDaVelha_Mapa jogoMapa;
    private JogoDaVelha_PC pc;
    private JogoDaVelha_Jogador jogador;

    public JogoDaVelha() {
        jogoMapa = new JogoDaVelha_Mapa();
        jogador = new JogoDaVelha_Jogador(jogoMapa);
        pc = new JogoDaVelha_PC(jogoMapa);
    }

    public void jogar(Scanner teclado) {
        jogoMapa.limpaMapa();
        char jogadorAtual = (jogoMapa.sortear(0, 1) == 0) ? 'X' : 'O';
        boolean jogoEmAndamento = true;

        while (jogoEmAndamento) {
            jogoMapa.desenhaMapa();
            boolean jogadaValida;
            if (jogadorAtual == 'X') {
                jogadaValida = jogador.joga(teclado);
            } else {
                jogadaValida = pc.joga();
            }

            if (jogadaValida) {
                if (jogoMapa.ganhou(jogadorAtual)) {
                    jogoMapa.desenhaMapa();
                    System.out.println("Jogador " + jogadorAtual + " venceu!");
                    jogoEmAndamento = false;
                } else {
                    jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        JogoDaVelha jogo = new JogoDaVelha();
        jogo.jogar(teclado);
        teclado.close();
    }
}
