package VS13.Squad9.EduMatch.menu;

import entities.Endereco;
import entities.Usuario;
import exceptions.InputException;
import services.EnderecoService;

import java.util.Scanner;

public class EnderecoMenu {

    private final EnderecoService enderecoService = new EnderecoService();
    private final String erro = "\nVocê não tem nenhum endereço cadastrado.";
    private final String erroCadastro = "\nVocê já possui um endereço cadastrado.";
    private final String input = "Opção: ";

    private MenuTextos menuTextos = new MenuTextos();

    public void enderecoOpcoes(Scanner sc, int opcao, Usuario usuario) {

        switch (opcao) {
            case 1:
                listar(usuario);
                break;

            case 2:
                salvar(sc, usuario);
                break;

            case 3:
                atualizar(sc, usuario);
                break;

            case 4:
                deletar(usuario, sc);
                break;
            case 5:
                System.out.println("\nRetornando ao menu principal.");
            default:
                System.out.println("\nOpção inválida. Tente novamente");
                break;
        }
    }

    private Endereco cadastrar(Scanner sc){
        while (true){
            String complemento;
            String CEP;
            String cidade;
            String estado;
            String pais;
            String rua;
            int numero;
            try{
                do {
                    System.out.print("Rua: ");
                    rua = sc.nextLine();
                } while (rua.isBlank());

                System.out.print("Número: ");
                numero = menuTextos.entradaUsuario(sc, "");

                do {
                    System.out.print("Complemento: ");
                    complemento = sc.nextLine();
                } while (complemento.isBlank());

                do {
                    System.out.print("CEP: ");
                    CEP = sc.nextLine();
                } while (CEP.isBlank() || CEP.matches(".*[a-zA-Z].*"));

                do {
                    System.out.print("Cidade: ");
                    cidade = sc.nextLine();
                } while (cidade.isBlank() || cidade.matches(".*\\d.*"));

                do {
                    System.out.print("Estado: ");
                    estado = sc.nextLine();
                } while (estado.isBlank() || estado.matches(".*\\d.*"));

                do {
                    System.out.print("País: ");
                    pais = sc.nextLine();
                } while (pais.isBlank() || pais.matches(".*\\d.*"));

                return new Endereco(rua, numero, complemento, CEP, cidade, estado, pais);
            } catch (InputException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }

    private boolean temEndereco(Usuario usuario){
        return usuario.getEndereco() != null;
    }

    private void listar(Usuario usuario){
        if (temEndereco(usuario)){
            System.out.println("ENDEREÇO CADASTRADO");
            System.out.println(usuario.getEndereco());
        } else {
            System.out.println(erro);
        }
    }

    private void salvar(Scanner sc, Usuario usuario){
        if (!temEndereco(usuario)) {
            System.out.println("\nCADASTRO DE UM NOVO ENDEREÇO");
            Endereco novoEndereco = cadastrar(sc);
            novoEndereco.setId_usuario(usuario.getId());
            if (enderecoService.salvar(novoEndereco)) {
                usuario.setEndereco(novoEndereco);
            }
        } else
            System.out.println(erroCadastro);
    }

    private void atualizar(Scanner sc, Usuario usuario){
        if(temEndereco(usuario)) {
            System.out.println("ATUALIZAR O ENDEREÇO");
            System.out.println(usuario.getEndereco());
            System.out.print("Insira o id do seu endereço para atualiza-lo ou 0 para cancelar a operação: ");
            int id = menuTextos.entradaUsuario(sc, "");
            if (id == 0){
                return;
            }
            Endereco enderecoAtualizado = cadastrar(sc);
            enderecoAtualizado.setId(usuario.getEndereco().getId());
            if (enderecoService.atualizar(id, enderecoAtualizado)) {
                usuario.setEndereco(enderecoAtualizado);
            }
        } else {
            System.out.println(erro);
        }
    }

    private void deletar(Usuario usuario, Scanner sc) {
        if (temEndereco(usuario)) {
            System.out.println("DELETAR ENDEREÇO");
            System.out.println(usuario.getEndereco());
            System.out.print("Insira o id do seu endereço para deleta-lo ou 0 para cancelar a operação: ");
            int id = menuTextos.entradaUsuario(sc, "");
            if (id == 0){
                return;
            }
            if (enderecoService.deletar(usuario.getEndereco().getId())) {
                usuario.setEndereco(null);
            }
        } else {
            System.out.println(erro);
        }
    }
}
