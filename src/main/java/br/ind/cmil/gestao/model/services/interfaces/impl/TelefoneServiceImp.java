package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.dto.FuncionarioDTO;
import br.ind.cmil.gestao.dto.TelefoneDTO;
import br.ind.cmil.gestao.exceptions.TelefoneException;
import br.ind.cmil.gestao.model.dto.mappers.FuncionarioMapper;
import br.ind.cmil.gestao.model.dto.mappers.TelefoneMapper;
import br.ind.cmil.gestao.model.entidades.Telefone;
import br.ind.cmil.gestao.model.repositorys.ITelefoneRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ind.cmil.gestao.model.services.interfaces.TelefoneService;

/**
 *
 * @author abraao
 */
@Service
public class TelefoneServiceImp implements TelefoneService {

    private final ITelefoneRepository tr;
    private final TelefoneMapper tm;    

    public TelefoneServiceImp(ITelefoneRepository tr, TelefoneMapper tm) {
        this.tr = tr;
        this.tm = tm;
    }
  
    

    @Override
    @Transactional(readOnly = false)
    public TelefoneDTO save(Long pessoa_id,TelefoneDTO telefone) {
        telefone.id();
        validarAtributos(telefone);

        Telefone t = tm.toEntity(telefone);
        // Telefone addTelefone = new Telefone();
        if (t.getId() == null) {
            //Pessoa pessoa = ps.getPessoaById(pessoa_id);
            //t.setPessoa(pessoa);
            return tm.toDTO(tr.save(t));

        }
        return update(telefone);
    }

    @Override
    @Transactional(readOnly = true)
    public TelefoneDTO buscarTelefonePorId(Long id) {
        return tr.findById(id).map(tm::toDTO).orElseThrow(() -> new TelefoneException(String.valueOf(id), "Este id não consta no bd! "));

    }

    @Override
    @Transactional(readOnly = true)
    public void delete(Long id) {
        tr.deleteById(id);
    }

    @Transactional(readOnly = false)
    private TelefoneDTO update(TelefoneDTO telefone) {
        Optional<Telefone> dbTelefone = tr.findById(telefone.id());

        if (dbTelefone.isEmpty()) {
            return null;
        }

        Telefone upTelefone = dbTelefone.get();
        upTelefone.setNumero(telefone.numero());
        upTelefone.setTipo(tm.convertTipoTelefoneValue(telefone.tipo()));
        //upTelefone.setPessoa(pm.toEntity((FuncionarioDTO) telefone.pessoa()));
        upTelefone.setId(telefone.id());
        return tm.toDTO(tr.save(upTelefone));
    }

    @Override
    public TelefoneDTO buscarPorNumero(String cep) {
        return tr.findByNumero(cep).map(tm::toDTO).orElseThrow(() -> new TelefoneException(cep, "Este id não consta no bd! "));

    }

    @Override
    public List<TelefoneDTO> list(Pageable pageable) {
        return tr.searchAll(pageable).stream().map(tm::toDTO).collect(Collectors.toList());

    }

    private void validarAtributos(TelefoneDTO t) {
        Optional<Telefone> telefone = tr.findByNumero(t.numero());
        if (telefone.isPresent() && !Objects.equals(telefone.get().getId(), t.id())) {
            throw new DataIntegrityViolationException("número já cadastro no sistema!");
        }

    }

}
