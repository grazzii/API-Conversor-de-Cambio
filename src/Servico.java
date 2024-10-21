import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Servico {  // Sem package
    private static final String CHAVE_API = "3f0b2ffa9ad886555bb6cf0b";  // Chave da API
    private static final String URL_API = "https://v6.exchangerate-api.com/v6/" + CHAVE_API + "/pair/";  // URL base da API

    public double obterTaxaDeCambio(String moedaOrigem, String moedaDestino) throws Exception {
        // Monta a URL com a moeda de origem e destino
        String urlString = URL_API + moedaOrigem + "/" + moedaDestino;
        URL url = new URL(urlString);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();  // Abre uma conexão HTTP
        conexao.setRequestMethod("GET");

        // Verifica o código de resposta HTTP
        int codigoResposta = conexao.getResponseCode();
        if (codigoResposta != 200) {  // 200 = HTTP OK
            throw new RuntimeException("Erro: código de resposta HTTP " + codigoResposta);
        }

        // Lê a resposta da API
        BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        StringBuilder resposta = new StringBuilder();
        String linha;

        // Lê a resposta linha por linha
        while ((linha = leitor.readLine()) != null) {
            resposta.append(linha);  // Adiciona cada linha ao StringBuilder
        }
        leitor.close();  // Fecha o leitor

        // Converte a resposta em JSON
        JsonObject jsonObject = JsonParser.parseString(resposta.toString()).getAsJsonObject();  // Transforma a string da resposta em um objeto JSON

        // Retorna a taxa de câmbio do campo "conversion_rate"
        return jsonObject.get("conversion_rate").getAsDouble();  // Extrai o valor da taxa de câmbio
    }
}
