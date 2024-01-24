package VS13.Squad09.EduMatch.dtos.request;

import VS13.Squad09.EduMatch.entities.Certificado;
import VS13.Squad09.EduMatch.entities.Contato;
import VS13.Squad09.EduMatch.entities.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class UsuarioCreateDTO {
    @Positive
    private Integer id;

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String sobrenome;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String senha;

    @NotNull
    @NotBlank
    private String CPF;

    @NotNull
    @NotBlank
    private Integer idade;

    @NotNull
    private Integer pontuacao;

    @NotNull
    @NotBlank
    private Endereco endereco;

    @NotNull
    private Contato contato;

    @NotNull
    private List<Certificado> certificados;

    @NotNull
    private Integer id_escola;

    @NotNull
    private Integer id_empresa;

}
