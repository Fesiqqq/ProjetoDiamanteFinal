import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static final int MAX = 8;

    public static void main(String[] args) {

        String[] nomes = new String[MAX];
        int[] pontuacoes = new int[MAX];

        int quantidadeJogadores = 0;
        int opcao;

        do {
            System.out.println("\n=== SISTEMA DE TORNEIO ===");
            System.out.println("1 - Cadastrar Jogador");
            System.out.println("2 - Registrar Pontuação");
            System.out.println("3 - Exibir Ranking");
            System.out.println("4 - Buscar Jogador");
            System.out.println("5 - Encerrar Torneio");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    quantidadeJogadores = cadastrarJogador(nomes, quantidadeJogadores);
                    break;

                case 2:
                    registrarPontuacao(nomes, pontuacoes, quantidadeJogadores);
                    break;

                case 3:
                    exibirRanking(nomes, pontuacoes, quantidadeJogadores);
                    break;

                case 4:
                    buscarJogador(nomes, pontuacoes, quantidadeJogadores);
                    break;

                case 5:
                    encerrarTorneio(nomes, pontuacoes, quantidadeJogadores);
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 5);

    }

    // OPÇÃO 1
    public static int cadastrarJogador(String[] nomes, int quantidadeJogadores) {

        if (quantidadeJogadores >= MAX) {
            System.out.println("Torneio cheio! Não é possível cadastrar mais jogadores.");
            return quantidadeJogadores;
        }

        System.out.print("Digite o nome do jogador: ");
        String nome = scanner.nextLine();

        nomes[quantidadeJogadores] = nome;
        quantidadeJogadores++;

        System.out.println("Jogador \"" + nome + "\" cadastrado com sucesso! (" 
                + quantidadeJogadores + "/8)");

        return quantidadeJogadores;
    }

    // OPÇÃO 2
    public static void registrarPontuacao(String[] nomes, int[] pontuacoes, int quantidadeJogadores) {

        System.out.print("Nome do jogador: ");
        String nome = scanner.nextLine();

        int posicao = buscarIndiceJogador(nomes, quantidadeJogadores, nome);

        if (posicao == -1) {
            System.out.println("Jogador nao encontrado no torneio.");
            return;
        }

        System.out.print("Pontuação: ");
        int pontos = scanner.nextInt();
        scanner.nextLine();

        if (pontos < 0) {
            System.out.println("Pontuações negativas não são permitidas.");
            return;
        }

        pontuacoes[posicao] = pontos;

        System.out.println("Pontuação de " + nome + " registrada: "
                + pontos + " pontos.");
    }

    // OPÇÃO 3
    public static void exibirRanking(String[] nomes, int[] pontuacoes, int quantidadeJogadores) {

        if (quantidadeJogadores == 0) {
            System.out.println("Nenhum jogador cadastrado.");
            return;
        }

        String[] nomesCopia = new String[quantidadeJogadores];
        int[] pontosCopia = new int[quantidadeJogadores];

        for (int i = 0; i < quantidadeJogadores; i++) {
            nomesCopia[i] = nomes[i];
            pontosCopia[i] = pontuacoes[i];
        }

        // Bubble Sort decrescente
        for (int i = 0; i < quantidadeJogadores - 1; i++) {

            for (int j = 0; j < quantidadeJogadores - 1 - i; j++) {

                if (pontosCopia[j] < pontosCopia[j + 1]) {

                    int tempPontos = pontosCopia[j];
                    pontosCopia[j] = pontosCopia[j + 1];
                    pontosCopia[j + 1] = tempPontos;

                    String tempNome = nomesCopia[j];
                    nomesCopia[j] = nomesCopia[j + 1];
                    nomesCopia[j + 1] = tempNome;
                }
            }
        }

        System.out.println("\n=== RANKING DO TORNEIO ===");

        for (int i = 0; i < quantidadeJogadores; i++) {

            if (pontosCopia[i] == 0) {
                System.out.println((i + 1) + ". "
                        + nomesCopia[i] + " - sem pontuacao");
            } else {
                System.out.println((i + 1) + ". "
                        + nomesCopia[i] + " - "
                        + pontosCopia[i] + " pts");
            }
        }
    }

    // OPÇÃO 4
    public static void buscarJogador(String[] nomes, int[] pontuacoes, int quantidadeJogadores) {

        System.out.print("Nome do jogador: ");
        String nome = scanner.nextLine();

        int posicao = buscarIndiceJogador(nomes, quantidadeJogadores, nome);

        if (posicao == -1) {
            System.out.println("Jogador nao encontrado no torneio.");
        } else {
            System.out.println("Jogador: " + nomes[posicao]
                    + " | Pontuacao atual: "
                    + pontuacoes[posicao] + " pts");
        }
    }

    // OPÇÃO 5
    public static void encerrarTorneio(String[] nomes, int[] pontuacoes, int quantidadeJogadores) {

        System.out.println("\n=== TORNEIO ENCERRADO ===");

        if (quantidadeJogadores == 0) {
            System.out.println("Nenhum jogador cadastrado. Encerrando...");
            return;
        }

        int maior = pontuacoes[0];
        int indiceCampeao = 0;

        for (int i = 1; i < quantidadeJogadores; i++) {

            if (pontuacoes[i] > maior) {
                maior = pontuacoes[i];
                indiceCampeao = i;
            }
        }

        System.out.println("Campeao: " + nomes[indiceCampeao]
                + " com " + pontuacoes[indiceCampeao] + " pontos!");

        System.out.println("Parabens ao vencedor! GG WPEZ!");
    }

    // FUNÇÃO AUXILIAR
    public static int buscarIndiceJogador(String[] nomes, int quantidadeJogadores, String nome) {

        for (int i = 0; i < quantidadeJogadores; i++) {

            if (nomes[i].equalsIgnoreCase(nome)) {
                return i;
            }
        }

        return -1;
    }
}