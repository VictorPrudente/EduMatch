package interfaces;

public interface Service <T>{

    boolean salvar(T t);
    boolean atualizar(int id, T t);
    boolean deletar(T t);
    void listarTodos();

}
