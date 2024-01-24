package VS13.Squad09.EduMatch.interfaces;

import java.util.List;

public interface Service <T> {

    Object salvar(Integer id, T t);
    Object atualizar(Integer id, T t);
    void deletar(Integer id);
    List<Object> listarTodos();

}
