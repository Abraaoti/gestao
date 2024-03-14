package br.ind.cmil.gestao.endereco.service.imp;

import br.ind.cmil.gestao.datatables.Datatables;
import br.ind.cmil.gestao.datatables.DatatablesColunas;
import br.ind.cmil.gestao.endereco.domain.Endereco;
import br.ind.cmil.gestao.endereco.mapper.EnderecoMapper;
import br.ind.cmil.gestao.endereco.model.EnderecoDTO;
import br.ind.cmil.gestao.endereco.repository.EnderecoRepository;
import br.ind.cmil.gestao.endereco.service.EnderecoService;
import br.ind.cmil.gestao.funcionario.repository.FuncionarioRepository;
import br.ind.cmil.gestao.pessoa.domain.Pessoa;
import br.ind.cmil.gestao.pessoa.repository.PessoaRepository;
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
public class EnderecoServiceImp implements EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoMapper enderecoMapper;
    private final PessoaRepository pessoaService;
    private final Datatables datatables;

    public EnderecoServiceImp(EnderecoRepository enderecoRepository, EnderecoMapper enderecoMapper, PessoaRepository pessoaService, Datatables datatables) {
        this.enderecoRepository = enderecoRepository;
        this.enderecoMapper = enderecoMapper;
        this.pessoaService = pessoaService;
        this.datatables = datatables;
    }

   

    @Override
    @Transactional(readOnly = true)
    public List<EnderecoDTO> list(Pageable pageable) {
        return enderecoRepository.searchAll(pageable).stream().map(enderecoMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = false)
    public EnderecoDTO salvar(EnderecoDTO enderecoDTO) {
        Endereco endereco = enderecoMapper.toEntity(enderecoDTO);

        validarAtributos(endereco);

        if (enderecoDTO.id() == null) {
            Pessoa pessoa = pessoaService.findByNome(enderecoDTO.pessoa()).get();
            endereco.setPessoa(pessoa);
            return enderecoMapper.toDTO(enderecoRepository.save(endereco));

        }
        return update(enderecoDTO);

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
    public EnderecoDTO criar(Long pessoa_id, EnderecoDTO enderecoDTO) {   

        if (enderecoDTO.id() == null) {
            Pessoa pessoa = pessoaService.findById(pessoa_id).get();
            return new EnderecoDTO(enderecoDTO.id(), enderecoDTO.uf(), enderecoDTO.cidade(), enderecoDTO.bairro(), enderecoDTO.rua(), enderecoDTO.cep(), enderecoDTO.numero(), enderecoDTO.complemento(), pessoa.getNome());
        }
        return enderecoMapper.toDTO(enderecoRepository.findByPessoa(pessoa_id).get());
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
        return  pessoaService.existsById(id);
    }
}
