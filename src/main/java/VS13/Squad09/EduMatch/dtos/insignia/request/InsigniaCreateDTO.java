package VS13.Squad09.EduMatch.dtos.insignia.request;

import VS13.Squad09.EduMatch.entities.enums.Dificuldade;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.entities.enums.Trilha;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsigniaCreateDTO {

    @NotBlank(message = "Não é possível inserir uma insígnia no banco sem passar a URL da sua imagem.")
    @Schema(description = "Url da imagem da insignia." +
            " É interessante que as imagens sejam salvas com o nome semelhante a sua tag." +
            " Por exemplo: 'portugues_facil.pgn'",
            example = "https://img.freepik.com/fotos-premium/arvore-psicodelica-ao-por-do-sol-hd-wallpaper_899449-86011.jpg")
    private String imagemUrl;

    @NotBlank(message = "Não é possível inserir uma insígnia no banco sem definir um título a ela.")
    @Schema(description = "Título da insignia", example = "Português Fácil")
    private String titulo;

    @NotBlank(message = "Não é possível inserir uma insígnia no banco sem dar uma descrição a ela.")
    @Schema(description = "Descrição referente a insignia e como obtê-la", example = "Esta insignia é recompensada a todos que completarem a prova de Português na dificuldade Fácil.")
    private String descricao;

    @NotBlank(message = "Não é possível inserir uma insígnia no banco sem dar uma tag a ela.")
    @Schema(description = "A tag é o identificador que o sistema utiliza para encontra-la e atribui-la a um usuário. " +
            "Ela deve ser escrita seguindo o padrão: TRILHA + _ + OBJETIVO para as querries do banco a acharem, por exemplo: 'PORUGUES_FACIL' para finalização da prova de portugues ou 'PORTUGUES_100' para adquirir após acertar 100 questões." +
            "OBS: Não utilizar carácteres especiais (ç, á, é, ê, etc)", example = "PORTUGUES_FACIL")
    private String tag;
}