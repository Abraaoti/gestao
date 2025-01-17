
package br.ind.cmil.gestao.telefone.service.imp;

import br.ind.cmil.gestao.datatables.Datatables;
import br.ind.cmil.gestao.datatables.DatatablesColunas;
import br.ind.cmil.gestao.enums.TipoTelefone;
import br.ind.cmil.gestao.pessoa.domain.Pessoa;
import br.ind.cmil.gestao.pessoa.repository.PessoaRepository;
import br.ind.cmil.gestao.telefone.domain.Telefone;
import br.ind.cmil.gestao.telefone.mapper.TelefoneMapper;
import br.ind.cmil.gestao.telefone.model.TelefoneDTO;
import br.ind.cmil.gestao.telefone.repository.TelefoneRepository;
import br.ind.cmil.gestao.telefone.service.TelefoneService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ti
 */
@Service
public class TelefoneServiceImp implements TelefoneService {

    private final TelefoneRepository tr;
    private final TelefoneMapper telefoneMapper;
    private final Datatables datatables;
    private final PessoaRepository pessoaService;

    public TelefoneServiceImp(TelefoneRepository tr, TelefoneMapper telefoneMapper, Datatables datatables, PessoaRepository pessoaService) {
        this.tr = tr;
        this.telefoneMapper = telefoneMapper;
        this.datatables = datatables;
        this.pessoaService = pessoaService;
    }

   

    @Override
    @Transactional(readOnly = false)
    public Long salvar(TelefoneDTO telefoneDTO) {

        Telefone telefone = telefoneMapper.toEntity(telefoneDTO);
        validarAtributos(telefone);
        if (telefoneDTO.id() == null) {
            Pessoa pessoa = pessoaService.findByNome(telefoneDTO.pessoa()).get();
            telefone.setPessoa(pessoa);
            return tr.save(telefone).getPessoa().getId();
        }
        return update(telefoneDTO);
    }

    @Transactional(readOnly = false)
    private Long update(TelefoneDTO telefoneDTO) {
        Optional<Telefone> dbTelefone = tr.findById(telefoneDTO.id());

        if (dbTelefone.isEmpty()) {
            return null;
        }
        Telefone upTelefone = dbTelefone.get();
        upTelefone.setNumero(telefoneDTO.numero());
        upTelefone.setTipo(TipoTelefone.convertTelefoneValue(telefoneDTO.tipo()));
        Pessoa pessoa = pessoaService.findByNome(telefoneDTO.pessoa()).get();
        upTelefone.setPessoa(pessoa);
        upTelefone.setId(telefoneDTO.id());
        Telefone telefone = tr.save(upTelefone);
        return telefone.getPessoa().getId();
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
        Pessoa pessoa = pessoaService.findById(pessoa_id).get();
        return new TelefoneDTO(telefone.id(), telefone.numero(), telefone.tipo(), pessoa.getNome());
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

