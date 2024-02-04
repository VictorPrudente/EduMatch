package VS13.Squad09.EduMatch.dtos.mapper;

import VS13.Squad09.EduMatch.dtos.request.ContatoCreateDTO;
import VS13.Squad09.EduMatch.dtos.request.EnderecoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.ContatoDTO;
import VS13.Squad09.EduMatch.dtos.response.EnderecoDTO;
import VS13.Squad09.EduMatch.entities.Contato;
import VS13.Squad09.EduMatch.entities.Endereco;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EnderecoMapper {
    private final ObjectMapper mapper;

    public Endereco toEntity(EnderecoCreateDTO dto){
        Endereco entity = mapper.convertValue(dto, Endereco.class);
        return entity;
    }

    public EnderecoDTO toDto(Endereco entity){
        EnderecoDTO dto = mapper.convertValue(entity, EnderecoDTO.class);
        return dto;
    }

    public List<EnderecoDTO> toDto(List<Endereco> enderecos){
        return enderecos.stream()
                .map(endereco -> toDto(endereco))
                .collect(Collectors.toList());
    }
}
