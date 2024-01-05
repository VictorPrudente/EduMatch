package testeEscola;

import entities.Escola;
import services.EscolaService;
import enums.TipoEscola;
public class Main {
    public static void main(String[] args) {

        Escola escola1 = new Escola("Garibaldi", TipoEscola.PUBLICA, "1234");
        Escola escola2 = new Escola("Alencar", TipoEscola.PRIVADA, "5698");

        EscolaService escolaService = new EscolaService();
        System.out.println(escolaService.adicionar(escola1));

        System.out.println(escolaService.buscar("1234"));

        System.out.println(escolaService.atualizar(escola1, "Van"));

        System.out.println(escolaService.adicionar(escola2));
        System.out.println(escolaService.deletar(escola2));

        System.out.println(escolaService.listar());
    }
}