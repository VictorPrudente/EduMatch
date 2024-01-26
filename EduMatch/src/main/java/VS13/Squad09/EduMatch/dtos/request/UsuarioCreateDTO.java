package VS13.Squad09.EduMatch.dtos.request;

import VS13.Squad09.EduMatch.entities.Certificado;
import VS13.Squad09.EduMatch.entities.Contato;
import VS13.Squad09.EduMatch.entities.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import VS13.Squad09.EduMatch.entities.Endereco;
import VS13.Squad09.EduMatch.entities.Contato;
import VS13.Squad09.EduMatch.entities.Certificado;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCreateDTO {

        private Integer id;
        @NotNull
        private String nome;
        @NotNull
        private String sobrenome;
        @NotNull
        private String email;
        @NotNull
        private String senha;
        @CPF
        private String CPF;
        @NotNull
        private Integer idade;
        private Integer pontuacao;
        @NotNull
        private Integer idEndereco;
        @NotNull
        private Integer idContato;
        @NotNull
        private Integer idCertificado;
        private Integer idEscola;
        private Integer idEmpresa;

}
