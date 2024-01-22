package VS13.Squad09.EduMatch.controllers;


import VS13.Squad9.EduMatch.dtos.request.EnderecoCreateDTO;
import VS13.Squad9.EduMatch.dtos.response.EnderecoDTO;
import VS13.Squad9.EduMatch.services.EnderecoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/endereco")
public class EnderecoController {

    private EnderecoService enderecoService;


    @PostMapping("/usuario/{idUsuario}")
    public ResponseEntity<EnderecoDTO> salvar(@PathVariable("idUsuario") Integer idUsuario,
                                              @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws Exception{
        return ResponseEntity.ok(enderecoService.salvar(idUsuario, enderecoCreateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable id)


}
