import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FolhaPagamentoApp {
    private static final ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();

    public static void main(String[] args) {
        importarRegistros();

        Scanner scanner = new Scanner(System.in); // Criar o objeto Scanner fora do loop
        while (true) {
            exibirMenu();
            System.out.print("Opção: ");
            String opcao;
            try {
                opcao = scanner.nextLine();
            } catch (NoSuchElementException e) {
                // Se ocorrer uma exceção de NoSuchElementException, provavelmente a entrada foi encerrada.
                // Neste caso, encerramos o programa.
                System.out.println("Encerrando o programa.");
                return;
            }

            switch (opcao) {
                case "1":
                    buscarRegistro();
                    break;
                case "2":
                    removerRegistro();
                    break;
                case "3":
                    listarRegistros();
                    break;
                case "4":
                    ordenarRegistros();
                    break;
                case "5":
                    System.out.println("Encerrando o programa.");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

            System.out.println();
        }
    }

    private static void importarRegistros() {
        try (InputStream inputStream = FolhaPagamentoApp.class.getResourceAsStream("netflix.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formato esperado de data

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 10) {
                    Employee registro = new Employee();
                    registro.setUserID(fields[0]);
                    registro.setSubscriptionType((fields[1]));
                    registro.setMonthlyRevenue((fields[2]));
                    registro.setJoinDate(fields[3]);
                    registro.setLastPaymentDate(fields[4]);
                    registro.setCountry(fields[5]);
                    registro.setAge((fields[6])); // Parse da data
                    registro.setGender(fields[7]);
                    registro.setDevice((fields[8]));
                    registro.setPlanDuration((fields[9]));

                    arvore.inserir(registro);
                }
            }
            System.out.println("Registros importados com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void exibirMenu() {
        System.out.println("------ Menu ------");
        System.out.println("1. Buscar registro");
        System.out.println("2. Remover registro");
        System.out.println("3. Listar registros");
        System.out.println("4. Ordenar registros");
        System.out.println("5. Sair");
    }

    private static void removerRegistro() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Digite a chave (userID) do registro a ser removido: ");
            String nome = scanner.nextLine();

            Employee registroRemover = new Employee();
            registroRemover.setUserID(nome);

            boolean removido = arvore.remover(registroRemover);
            if (removido) {
                System.out.println("Registro removido com sucesso!");
            } else {
                System.out.println("Registro não encontrado.");
            }
        }
    }

    private static void listarRegistros() {
        List<Employee> registros = arvore.listarEmOrdem();
        for (Employee registro : registros) {
            System.out.println(registro);
        }
    }

    private static void buscarRegistro() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Digite parte do nome do registro a ser buscado: ");
            String parteNome = scanner.nextLine().toUpperCase(); // Convertendo para maiúsculas para facilitar a comparação

            List<Employee> resultados = arvore.listarEmOrdem();
            List<Employee> registrosEncontrados = new ArrayList<>();

            for (Employee registro : resultados) {
                if (registro.getCountry().toUpperCase().contains(parteNome)) {
                    registrosEncontrados.add(registro);
                }
            }

            if (!registrosEncontrados.isEmpty()) {
                System.out.println("Registros encontrados:");
                for (Employee registro : registrosEncontrados) {
                    System.out.println(registro);
                }
            } else {
                System.out.println("Nenhum registro encontrado com a sequência informada.");
            }
        }
    }


    private static void ordenarRegistros() {
        List<Employee> registros = arvore.listarEmOrdem();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Escolha o atributo para ordenação:");
            System.out.println("1. User ID");
            System.out.println("2. Subscription Type");
            System.out.println("3. Monthly Revenue");
            System.out.println("4. Join Date");
            System.out.println("5. Last Payment Date");
            System.out.println("6. Country");
            System.out.println("7. Age");
            System.out.println("8. Gender");
            System.out.println("9. Device");
            System.out.println("10. Plan Duration");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    Collections.sort(registros, Comparator.comparing(Employee::getUserID));
                    break;
                case 2:
                    Collections.sort(registros, Comparator.comparing(Employee::getSubscriptionType));
                    break;
                case 3:
                    Collections.sort(registros, Comparator.comparing(Employee::getMonthlyRevenue));
                    break;
                case 4:
                    Collections.sort(registros, Comparator.comparing(Employee::getJoinDate));
                    break;
                case 5:
                    Collections.sort(registros, Comparator.comparing(Employee::getLastPaymentDate));
                    break;
                case 6:
                    Collections.sort(registros, Comparator.comparing(Employee::getCountry));
                    break;
                case 7:
                    Collections.sort(registros, Comparator.comparing(Employee::getAge));
                    break;
                case 8:
                    Collections.sort(registros, Comparator.comparing(Employee::getGender));
                    break;
                case 9:
                    Collections.sort(registros, Comparator.comparing(Employee::getDevice));
                    break;
                case 10:
                    Collections.sort(registros, Comparator.comparing(Employee::getPlanDuration));
                    break;
                default:
                    System.out.println("Opção inválida. Nenhum registro foi ordenado.");
                    return;
            }

            System.out.println("Registros ordenados:");
            for (Employee registro : registros) {
                System.out.println(registro);
            }
        }
    }
}
