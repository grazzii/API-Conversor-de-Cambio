import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);  // Scanner para capturar a entrada do usuário

        System.out.println("Digite o valor que deseja converter: ");
        double valor = entrada.nextDouble();

        System.out.println("Digite a moeda de origem (ex: USD): ");
        String moedaOrigem = entrada.next().toUpperCase();

        System.out.println("Digite a moeda de destino (ex: BRL): ");
        String moedaDestino = entrada.next().toUpperCase();

        // Instancia o serviço de taxa de câmbio
        Servico servico = new Servico();

        try {
            // Obtém a taxa de câmbio e realiza a conversão
            double taxaDeCambio = servico.obterTaxaDeCambio(moedaOrigem, moedaDestino);  // Chama o método que obtém a taxa de câmbio
            double valorConvertido = valor * taxaDeCambio;  // Multiplica o valor original pela taxa

            // Exibe o valor convertido
            System.out.println("O valor de " + valor + " " + moedaOrigem + " convertido para " + moedaDestino + " é: " + valorConvertido + " " + moedaDestino);
        } catch (Exception e) {
            System.out.println("Erro ao obter a taxa de câmbio.");
            e.printStackTrace();  // Exibe detalhes do erro
        }

        entrada.close();
    }
}
