package erp.arquitetura.validacao;

public class Telefone {
    
    // Método para validar o formato "Dígitos-Dígitos" ou aceitar vazio
    public static boolean validarFormato(String entrada) {
        if (entrada == null || entrada.isEmpty()) {
            return true; // Aceita vazio
        }
        String regex = "\\d+-\\d+"; // Expressão regular
        return entrada.matches(regex);
    }
}