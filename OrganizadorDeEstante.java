package organizadordeestante;

import java.util.Scanner;

public class OrganizadorDeEstante {

    public static void main(String[] args) {

        int escolha;
        String[] menuDeOpcoes = {"Inserir livro na prateleira", "Excluir livro", "Consultar livro", "Listar livros",
            "Interromper aplicação"};

        for (int i = 0; i < livrosNaPrateleira.length; i++) {
            for (int j = 0; j < livrosNaPrateleira[i].length; j++) {

                // configura as categorias de cada prateleira
                if (j == 0) {
                    livrosNaPrateleira[i][j] = categorias[i];
                    continue;
                }

                livrosNaPrateleira[i][j] = "";

            }
        }

        adicionarLivro();

        menuDeOpcoes:
        while (true) {

            escolha = solicitarTarefa(menuDeOpcoes);

            switch (escolha) {
                case 0:
                    adicionarLivro();
                    break;
                case 1:
                    excluirLivro();
                    break;
                case 2:
                    consultarLivros();
                    break;
                case 3:
                    listarLivros();
                    break;
                case 4:
                    break menuDeOpcoes;
            }

        }

    }

    public static void adicionarLivro() {

        System.out.println("-------------- ADICIONAR --------------");

        System.out.println("Use números para selecionar a categoria do livro");

        insercaoDeLivros:
        while (true) {

            boolean solicitouParada;
            boolean livroJaExisteNaLista;
            int quantidadeDeLivrosNaPrateleira;
            int posicaoDisponivel;
            int numeroDaCategoria;
            int posicaoDoLivro = 0;
            String tituloDoLivro;

            // exibe as categorias disponíveis
            for (int i = 0; i < categorias.length; i++) {
                System.out.println((i + 1) + ". " + categorias[i]);
            }

            // recebe a categoria em que o livro será inserido
            while (true) {

                System.out.print("\nNúmero da categoria: ");
                numeroDaCategoria = s.nextInt();

                if (numeroDaCategoria <= 0 || numeroDaCategoria > categorias.length) {
                    System.out.print("\n'" + numeroDaCategoria + "' não é válido. ");
                    System.out.println("Informe uma categoria dentre as " + categorias.length);
                    continue;
                }

                numeroDaCategoria--;
                break;
            }

            posicaoDisponivel = encontrarPosicaoDisponivel(numeroDaCategoria);

            if (posicaoDisponivel == 0) {
                System.out.print("A prateleira sobre " + categorias[numeroDaCategoria] + " está cheia!\n");
                continue;
            }

            System.out.print("\nInforme o título do livro: ");
            tituloDoLivro = s.next();
            tituloDoLivro = tituloDoLivro + s.nextLine();
            tituloDoLivro = converterPrimeiraLetraEmMaiuscula(tituloDoLivro);

            livroJaExisteNaLista = verificarSeLivroExiste(tituloDoLivro, numeroDaCategoria);

            if (livroJaExisteNaLista) {
                System.out.println("\n'" + tituloDoLivro + "' já foi inserido na prateleira sobre " + categorias[numeroDaCategoria] + "\n");
                continue;
            }

            posicaoDisponivel = encontrarPosicaoDisponivel(numeroDaCategoria);

            livrosNaPrateleira[numeroDaCategoria][posicaoDisponivel] = tituloDoLivro;
            ordenarAlfabeticamente();

            quantidadeDeLivrosNaPrateleira = contabilizarLivrosNaPrateleira(numeroDaCategoria);

            for (int i = 1; i <= quantidadeDeLivrosNaPrateleira; i++) {
                if (livrosNaPrateleira[numeroDaCategoria][i].equals(tituloDoLivro)) {
                    posicaoDoLivro = i;
                    break;
                }
            }

            if (quantidadeDeLivrosNaPrateleira > 1) {

                System.out.print("\n'" + tituloDoLivro + "' foi armazenado na " + posicaoDoLivro + "ª posição ");
                System.out.println("da prateleira sobre " + categorias[numeroDaCategoria]);

                if (livrosNaPrateleira[numeroDaCategoria][posicaoDoLivro + 1] != "") {
                    System.out.print("'" + livrosNaPrateleira[numeroDaCategoria][posicaoDoLivro + 1]);
                    System.out.println("' é o livro posterior!");
                }

                if (livrosNaPrateleira[numeroDaCategoria][posicaoDoLivro - 1] != "") {

                    if (livrosNaPrateleira[numeroDaCategoria][posicaoDoLivro - 1] != categorias[numeroDaCategoria]) {
                        System.out.print("'" + livrosNaPrateleira[numeroDaCategoria][posicaoDoLivro - 1]);
                        System.out.println("' é o livro anterior!");
                    }

                }

            } else {
                System.out.print("\n'" + tituloDoLivro + "' foi armazenado na ");
                System.out.println(posicaoDoLivro + "ª posição da prateleira sobre " + categorias[numeroDaCategoria]);
            }

            solicitouParada = verificarContinuidade();

            if (solicitouParada) {
                break insercaoDeLivros;
            }

        }

    }

    public static boolean verificarSeLivroExiste(String livro, int categoria) {
        boolean livroRepetido = false;

        livro = livro.toLowerCase();

        for (int i = 1; i < livrosNaPrateleira[categoria].length; i++) {
            if (livrosNaPrateleira[categoria][i].toLowerCase().equals(livro)) {
                livroRepetido = true;
                break;
            }
        }

        return livroRepetido;
    }

    public static int contabilizarLivrosNaPrateleira(int categoria) {
        int quantidadeDeLivrosNaPrateleira = 0;

        for (int i = 1; i < livrosNaPrateleira[categoria].length; i++) {
            if (livrosNaPrateleira[categoria][i] != "") {
                quantidadeDeLivrosNaPrateleira++;
                continue;
            }

            break;
        }

        return quantidadeDeLivrosNaPrateleira;
    }

    public static boolean verificarContinuidade() {
        boolean continuar = true;
        char resposta;

        System.out.println("-----------------------------------------------------");

        verificadorDeContinuidade:
        while (true) {
            System.out.print("Deseja continuar? (S/N): ");
            resposta = s.next().charAt(0);

            resposta = Character.toLowerCase(resposta);

            switch (resposta) {
                case 's':
                    continuar = false;
                    break verificadorDeContinuidade;
                case 'n':
                    System.out.println("-----------------------------------------------------");
                    break verificadorDeContinuidade;
                default:
                    System.out.println("\n'" + resposta + "' não é válido. Digite S ou N para continuar\n");
                    continue;
            }

        }

        return continuar;
    }

    public static int encontrarPosicaoDisponivel(int categoria) {

        int posicaoDisponivel = 0;

        for (int j = 1; j < livrosNaPrateleira[categoria].length; j++) {
            if (livrosNaPrateleira[categoria][j].equals("")) {
                posicaoDisponivel = j;
                break;
            }
        }

        return posicaoDisponivel;
    }

    public static void excluirLivro() {

        System.out.println("\n-------------- EXCLUIR --------------\n");

        exclusaoDeLivros:
        while (true) {
            String[] prateleirasQueNaoEstaoVazias = new String[categorias.length];

            for (int i = 0; i < prateleirasQueNaoEstaoVazias.length; i++) {
                prateleirasQueNaoEstaoVazias[i] = "";
            }

            boolean solicitouParada = false;
            int prateleiraSelecionada = 0;
            int numeroDoLivro = 0;
            int quantidadeDeLivrosNaPrateleira = 0;
            int quantidadeDePrateleirasQuePossuemLivros = 0;
            int unicaPrateleiraQuePossuiLivro = 0;

            // Filtra quais categorias possuem ao menos um livro
            for (int i = 0; i < livrosNaPrateleira.length; i++) {
                for (int j = 1; j < livrosNaPrateleira[i].length; j++) {
                    if (livrosNaPrateleira[i][j] != "") {

                        quantidadeDePrateleirasQuePossuemLivros++;

                        // Armazena as categorias que possuem ao menos um livro
                        // Serve pra evitar mostrar prateleira vazia
                        for (int k = 0; k < prateleirasQueNaoEstaoVazias.length; k++) {
                            if (prateleirasQueNaoEstaoVazias[k].equals("")) {
                                prateleirasQueNaoEstaoVazias[k] = categorias[i];
                                break;
                            }
                        }

                        // Caso só haja uma prateleira com livros
                        // A variável recebe a posição da prateleira
                        unicaPrateleiraQuePossuiLivro = i;

                        break;
                    }
                }
            }

            if (quantidadeDePrateleirasQuePossuemLivros == 0) {
                System.out.println("\nAs estantes não possuem livros!\n");
                break exclusaoDeLivros;
            }

            // Se só uma prateleira possuir livros e todas as outras estiverem vazias
            // Não precisa solicitar a categoria
            if (quantidadeDePrateleirasQuePossuemLivros > 1) {

                for (int i = 0; i < quantidadeDePrateleirasQuePossuemLivros; i++) {
                    System.out.println((i + 1) + ". " + prateleirasQueNaoEstaoVazias[i]);
                }

                while (true) {
                    System.out.print("\nNúmero da categoria em que o livro está catalogado: ");
                    prateleiraSelecionada = s.nextInt();

                    if (prateleiraSelecionada <= 0 || prateleiraSelecionada > quantidadeDePrateleirasQuePossuemLivros) {
                        System.out.print("\n'" + prateleiraSelecionada + "' não é válido. ");
                        System.out.println("Informe uma categoria dentre as " + quantidadeDePrateleirasQuePossuemLivros);
                        continue;
                    }

                    prateleiraSelecionada--;

                    for (int i = 0; i < categorias.length; i++) {
                        if (categorias[i].equals(prateleirasQueNaoEstaoVazias[prateleiraSelecionada])) {
                            prateleiraSelecionada = i;
                            break;
                        }
                    }

                    break;
                }

            } else {
                prateleiraSelecionada = unicaPrateleiraQuePossuiLivro;
            }

            // contabiliza quantos livros existem na prateleira selecionada
            for (int i = 1; i < livrosNaPrateleira[prateleiraSelecionada].length; i++) {
                if (livrosNaPrateleira[prateleiraSelecionada][i] != "") {
                    quantidadeDeLivrosNaPrateleira++;
                    continue;
                }

                break;
            }

            if (quantidadeDeLivrosNaPrateleira == 0) {
                System.out.print("\nA prateleira sobre está vazia!\n");
                break exclusaoDeLivros;
            }

            for (int i = 1; i <= quantidadeDeLivrosNaPrateleira; i++) {
                System.out.println(i + ". " + livrosNaPrateleira[prateleiraSelecionada][i]);
            }

            while (true) {
                System.out.print("\nNúmero do livro que será excluído: ");
                numeroDoLivro = s.nextInt();

                if (numeroDoLivro < 1 || numeroDoLivro > quantidadeDeLivrosNaPrateleira) {
                    System.out.print("\n'" + numeroDoLivro + "' é inválido. ");
                    System.out.println("Selecione um dentre os " + quantidadeDeLivrosNaPrateleira + " livros na prateleira!");
                    continue;
                }

                break;
            }

            System.out.print("\n----> " + livrosNaPrateleira[prateleiraSelecionada][numeroDoLivro]);
            livrosNaPrateleira[prateleiraSelecionada][numeroDoLivro] = "";
            System.out.println(" excluído com sucesso!\n");

            arrastarPosicaoVazia();

            solicitouParada = verificarContinuidade();

            if (solicitouParada) {
                break exclusaoDeLivros;
            }

        }

    }

    public static void ordenarAlfabeticamente() {
        
        String auxiliar;
        boolean houveTroca;

        for (int i = 0; i < livrosNaPrateleira.length; i++) {

            int quantidadeDeLivrosNaPrateleira = contabilizarLivrosNaPrateleira(i);

            if (quantidadeDeLivrosNaPrateleira > 1) {

                percorrerArray:
                for (int j = 0; j < livrosNaPrateleira[i].length; j++) {
                    houveTroca = false;
                    for (int k = 1; k < livrosNaPrateleira[i].length - 1; k++) {

                        int retorno = livrosNaPrateleira[i][k].compareTo(livrosNaPrateleira[i][k + 1]);

                        if (livrosNaPrateleira[i][k + 1].equals("")) {
                            break;
                        }
                        if (retorno > 0) {
                            auxiliar = livrosNaPrateleira[i][k];
                            livrosNaPrateleira[i][k] = livrosNaPrateleira[i][k + 1];
                            livrosNaPrateleira[i][k + 1] = auxiliar;
                            houveTroca = true;
                        }

                    }

                    if (!houveTroca) {
                        break percorrerArray;
                    }

                }
            }

        }

    }

    public static int contabilizarPrateleirasQuePossuemLivros() {
        int quantidadeDePrateleirasQuePossuemLivros = 0;

        for (int i = 0; i < livrosNaPrateleira.length; i++) {
            for (int j = 1; j < livrosNaPrateleira[i].length; j++) {
                if (livrosNaPrateleira[i][j] != "") {
                    quantidadeDePrateleirasQuePossuemLivros++;
                    break;
                }
            }
        }

        return quantidadeDePrateleirasQuePossuemLivros;
    }

    public static void arrastarPosicaoVazia() {

        int posicaoVazia = 0;
        String auxiliar;

        laçoExterno:
        for (int i = 0; i < livrosNaPrateleira.length; i++) {

            int categoria = i;

            posicaoVazia = encontrarPosicaoDisponivel(categoria);

            for (int j = posicaoVazia; j < livrosNaPrateleira[i].length - 1; j++) {

                if (livrosNaPrateleira[i][j + 1].equals("")) {
                    break laçoExterno;
                }

                auxiliar = livrosNaPrateleira[i][j];
                livrosNaPrateleira[i][j] = livrosNaPrateleira[i][j + 1];
                livrosNaPrateleira[i][j + 1] = auxiliar;
            }
        }

    }

    public static void listarLivros() {

        for (int i = 0; i < livrosNaPrateleira.length; i++) {
            for (int j = 0; j < livrosNaPrateleira[i].length; j++) {

                // Mostra a categoria da prateleira
                if (j == 0) {

                    // Caso não haja livros na prateleira, evita mostrar a categoria
                    if (livrosNaPrateleira[i][j + 1] == "") {
                        break;
                    }

                    System.out.println("\n" + livrosNaPrateleira[i][j]);
                    continue;
                }

                // Exibe os livros disponíveis na prateleira
                if (livrosNaPrateleira[i][j] != "") {
                    System.out.println(j + ". " + livrosNaPrateleira[i][j]);
                    continue;
                }

                break;

            }

        }

        System.out.println("");

    }

    public static void consultarLivros() {

        System.out.println("-------------- CONSULTAR LIVROS --------------");

        consultor:
        while (true) {

            boolean solicitouParada = false;
            boolean encontrouLivro = false;
            String tituloDoLivro;
            String auxiliar;

            System.out.print("Nome do livro a ser consultado: ");
            tituloDoLivro = s.next();
            tituloDoLivro = tituloDoLivro + s.nextLine();
            tituloDoLivro = converterPrimeiraLetraEmMaiuscula(tituloDoLivro);

            // Transformei em maiúsculo pra evitar erros na hora de filtrar
            // (Java != java) == true --> (Case sensitive)
            auxiliar = tituloDoLivro.toLowerCase().trim();

            System.out.println("------------------------------------------");

            for (int i = 0; i < livrosNaPrateleira.length; i++) {
                for (int j = 1; j < livrosNaPrateleira[i].length; j++) {

                    if (livrosNaPrateleira[i][j].equals("")) {
                        // Verifica a primeira posição pra prateleira
                        // Se não houver um livro, significa que ela está vazia
                        // Se estiver vazia, interrompe a busca e verifica a próxima prateleira
                        break;
                    }

                    if (livrosNaPrateleira[i][j].toLowerCase().equals(auxiliar)) {
                        System.out.print("'" + tituloDoLivro + "' é o " + j + "º livro da prateleira sobre ");
                        System.out.println(categorias[i]);

                        if (j > 1) {

                            if (livrosNaPrateleira[i][j - 1].toLowerCase() != "") {
                                System.out.print("'" + livrosNaPrateleira[i][j - 1] + "'");
                                System.out.println(" é o livro anterior");
                            }

                        }

                        if (livrosNaPrateleira[i][j + 1].toLowerCase() != "") {
                            System.out.print("'" + livrosNaPrateleira[i][j + 1] + "'");
                            System.out.println(" é o livro posterior");
                        }

                        encontrouLivro = true;
                        break;
                    }

                }
            }

            if (!encontrouLivro) {
                System.out.println("'" + tituloDoLivro + "' não consta em nenhuma prateleira!");
            }

            solicitouParada = verificarContinuidade();

            if (solicitouParada) {
                break consultor;
            }

        }

    }

    public static int solicitarTarefa(String[] menu) {
        int escolha = 0;

        System.out.println(">>>>> MENU DE OPÇÕES <<<<<");

        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ". " + menu[i]);
        }

        while (true) {

            System.out.print("\nSua escolha: ");
            escolha = s.nextInt();

            if (escolha <= 0 || escolha > menu.length) {
                System.out.print("'" + escolha + "' não é válido. ");
                System.out.println("Informe uma opção dentre as " + menu.length);
                continue;
            }

            break;
        }

        escolha--;

        return escolha;
    }

    public static String converterPrimeiraLetraEmMaiuscula(String elemento) {

        char[] auxiliar = elemento.toCharArray();

        auxiliar[0] = Character.toUpperCase(auxiliar[0]);

        elemento = new String(auxiliar);

        return elemento;
    }

    public static String[] categorias = {"Programação", "Banco de dados", "Redes", "Análise de sistemas",
        "Modelagem de dados"};

    // Usei apenas um vetor de duas dimensões pra facilitar a leitura e escrita do
    // código
    // A primeira posição fica reservada à categoria da prateleira
    // Portanto adicionei mais uma posição, completando 30, conforme solicitado
    public static int quantidadeMaximaDeLivrosPorPrateleira = 31;

    public static String[][] livrosNaPrateleira = new String[categorias.length][quantidadeMaximaDeLivrosPorPrateleira];

    public static Scanner s = new Scanner(System.in);
}
