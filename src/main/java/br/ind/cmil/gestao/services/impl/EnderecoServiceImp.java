package br.ind.cmil.gestao.services.impl;

import br.ind.cmil.gestao.datatables.Datatables;
import br.ind.cmil.gestao.datatables.DatatablesColunas;
import br.ind.cmil.gestao.domain.Endereco;
import br.ind.cmil.gestao.domain.Pessoa;
import br.ind.cmil.gestao.mappers.EnderecoMapper;
import br.ind.cmil.gestao.model.dto.EnderecoDTO;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ind.cmil.gestao.services.EnderecoService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.data.domain.Page;
import br.ind.cmil.gestao.repositorys.EnderecoRepository;
import br.ind.cmil.gestao.repositorys.FuncionarioRepository;

/**
 *
 * @author abraao
 */
@Service
public class EnderecoServiceImp implements EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoMapper enderecoMapper;
    private final FuncionarioRepository funcionarioService;
    private final Datatables datatables;

    public EnderecoServiceImp(EnderecoRepository enderecoRepository, EnderecoMapper enderecoMapper, FuncionarioRepository funcionarioService, Datatables datatables) {
        this.enderecoRepository = enderecoRepository;
        this.enderecoMapper = enderecoMapper;
        this.funcionarioService = funcionarioService;
        this.datatables = datatables;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnderecoDTO> list(Pageable pageable) {
        return enderecoRepository.searchAll(pageable).stream().map(enderecoMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = false)
    public Long salvar(EnderecoDTO enderecoDTO) {
        Endereco endereco = enderecoMapper.toEntity(enderecoDTO);

        validarAtributos(endereco);

        if (enderecoDTO.id() == null) {
            Pessoa pessoa = funcionarioService.findByNome(enderecoDTO.pessoa()).get();
            endereco.setPessoa(pessoa);
            return enderecoRepository.save(endereco).getId();

        }
        return update(enderecoDTO).id();

    }

    @Transactional(readOnly = false)
    public EnderecoDTO update(EnderecoDTO endereco) {

        return enderecoRepository.findById(endereco.id())
                .map(enderecoMapper::toDTO).get();
    }

    @Transactional(readOnly = true)
    @Override
    public EnderecoDTO buscarPorCep(String cep) {
        return enderecoRepository.findByCep(cep).map(enderecoMapper::toDTO).get();
    }

    @Transactional(readOnly = true)
    @Override
    public EnderecoDTO criar(Long pessoa_id, EnderecoDTO endereco) {
        Pessoa pessoa = funcionarioService.findById(pessoa_id).get();
        return new EnderecoDTO(endereco.id(), endereco.uf(), endereco.cidade(), endereco.bairro(), endereco.rua(), endereco.cep(), endereco.numero(), endereco.complemento(), pessoa.getNome());
    }

    private void validarAtributos(Endereco request) {
        Optional<Endereco> endereco = enderecoRepository.findByCep(request.getCep());
        if (endereco.isPresent() && !Objects.equals(endereco.get().getId(), request.getId()) && !Objects.equals(endereco.get().getPessoa().getId(), request.getPessoa().getId())) {
            throw new DataIntegrityViolationException("cep já cadastro no sistema!");
        }
        endereco = enderecoRepository.findByPessoa(request.getPessoa().getId());
        if (endereco.isPresent() && !Objects.equals(endereco.get().getPessoa().getId(), request.getPessoa().getId())) {
            throw new DataIntegrityViolationException("pessoa já cadastro no sistema!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public EnderecoDTO buscarEnderecoPorId(Long id) {
        return enderecoRepository.findById(id).map(enderecoMapper::toDTO).get();
    }

    @Override
    @Transactional(readOnly = true)
    public void delete(Long id) {
        enderecoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.ENDERECO);
        Page<Endereco> page = datatables.getSearch().isEmpty() ? enderecoRepository.findAll(datatables.getPageable())
                : enderecoRepository.findAllByEndereco(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    public boolean pessoaExists(Long id) {
        return enderecoRepository.existsByPessoaId(id);
    }
}
