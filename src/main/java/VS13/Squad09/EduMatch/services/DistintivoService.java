package VS13.Squad09.EduMatch.services;


import VS13.Squad09.EduMatch.dtos.response.DistintivoDTO;
import VS13.Squad09.EduMatch.entities.Distintivo;
import VS13.Squad09.EduMatch.repositories.DistintivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DistintivoService {

    private final DistintivoRepository distintivoRepository;

    public Page<DistintivoDTO> findAll(Pageable page) {
        return distintivoRepository.findAll(page)
                .map(this::toDTO);
    }



    // METODOS ADICIONAIS

    private DistintivoDTO toDTO(Distintivo distintivo){
        DistintivoDTO distintivoDTO = new DistintivoDTO();
        BeanUtils.copyProperties(distintivo, distintivoDTO);
        return distintivoDTO;
    }

    private Distintivo toEntity(DistintivoDTO dto){
        Distintivo distintivo = new Distintivo();
        BeanUtils.copyProperties(dto, distintivo);
        return distintivo;
    }

}
