package br.ind.cmil.gestao.services.impl;

import br.ind.cmil.gestao.datatables.Datatables;
import br.ind.cmil.gestao.datatables.DatatablesColunas;
import br.ind.cmil.gestao.domain.Pessoa;
import br.ind.cmil.gestao.domain.Telefone;
import br.ind.cmil.gestao.enums.TipoTelefone;
import br.ind.cmil.gestao.mappers.TelefoneMapper;
import br.ind.cmil.gestao.model.dto.TelefoneDTO;
import br.ind.cmil.gestao.repositorys.FuncionarioRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ind.cmil.gestao.services.TelefoneService;
import br.ind.cmil.gestao.repositorys.TelefoneRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.data.domain.Page;

/**
 *
 * @author abraao
 */
@Service
public class TelefoneServiceImp implements TelefoneService {

    private final TelefoneRepository tr;
    private final TelefoneMapper telefoneMapper;
    private final Datatables datatables;
    private final FuncionarioRepository funcionarioService;

    public TelefoneServiceImp(TelefoneRepository tr, TelefoneMapper telefoneMapper, Datatables datatables, FuncionarioRepository funcionarioService) {
        this.tr = tr;
        this.telefoneMapper = telefoneMapper;
        this.datatables = datatables;
        this.funcionarioService = funcionarioService;
    }

    @Override
    @Transactional(readOnly = false)
    public Long salvar(TelefoneDTO telefoneDTO) {
        Telefone telefone = telefoneMapper.toEntity(telefoneDTO);
        validarAtributos(telefone);
        if (telefoneDTO.id() == null) {
            Pessoa pessoa = funcionarioService.findById(telefoneDTO.pessoa()).get();
            telefone.setPessoa(pessoa);
           return tr.save(telefone).getId();
        }
        return update(telefoneDTO).pessoa();
    }

    @Override
    @Transactional(readOnly = true)
    public TelefoneDTO buscarTelefonePorId(Long id) {
        return tr.findById(id).map(telefone -> telefoneMapper.toDTO(telefone)).get();

    }

    @Override
    @Transactional(readOnly = true)
    public void delete(Long id) {
        tr.deleteById(id);
    }

    @Transactional(readOnly = false)
    private TelefoneDTO update(TelefoneDTO telefoneDTO) {
        Optional<Telefone> dbTelefone = tr.findById(telefoneDTO.id());

        if (dbTelefone.isEmpty()) {
            return null;
        }
        Telefone upTelefone = dbTelefone.get();
        upTelefone.setNumero(telefoneDTO.numero());
        upTelefone.setTipo(TipoTelefone.convertTelefoneValue(telefoneDTO.tipo()));
         Pessoa pessoa = funcionarioService.findById(telefoneDTO.pessoa()).get();
        upTelefone.setPessoa(pessoa);
        upTelefone.setId(telefoneDTO.id());
        return telefoneMapper.toDTO(tr.save(upTelefone));
    }

    @Override
    public TelefoneDTO buscarPorNumero(String numero) {
        return tr.findByNumero(numero).map(telefoneMapper::toDTO).get();

    }

    @Override
    public List<TelefoneDTO> list(Pageable pageable) {
        return tr.searchAll(pageable).stream().map(telefoneMapper::toDTO).collect(Collectors.toList());

    }

    private void validarAtributos(Telefone t) {
        Optional<Telefone> telefone = tr.findByNumero(t.getNumero());
        if (telefone.isPresent() && !Objects.equals(telefone.get().getId(), t.getId())) {
            throw new DataIntegrityViolationException("número já cadastro no sistema!");
        }

    }

    @Override
    public TelefoneDTO criar(Long pessoa_id, TelefoneDTO telefone) {
        Pessoa pessoa = funcionarioService.findById(pessoa_id).get();
        return new TelefoneDTO(telefone.id(), telefone.numero(), telefone.tipo(), pessoa.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.TELEFONE);
        Page<Telefone> page = datatables.getSearch().isEmpty() ? tr.findAll(datatables.getPageable())
                : tr.findAllByTelefone(TipoTelefone.convertTelefoneValue(datatables.getSearch()), datatables.getPageable());
        return datatables.getResponse(page);
    }

}
