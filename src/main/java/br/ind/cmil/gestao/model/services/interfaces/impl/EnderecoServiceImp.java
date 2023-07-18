package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.exceptions.ObjectNotFoundException;
import br.ind.cmil.gestao.model.dto.EnderecoDTO;
import br.ind.cmil.gestao.model.dto.mappers.EnderecoMapper;
import br.ind.cmil.gestao.model.entidades.Endereco;
import br.ind.cmil.gestao.model.entidades.Pessoa;
import br.ind.cmil.gestao.model.repositorys.IEnderecoRepository;
import br.ind.cmil.gestao.model.services.interfaces.IEnderecoService;
import br.ind.cmil.gestao.model.services.interfaces.IPessoaService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraao
 */
@Service
public class EnderecoServiceImp implements IEnderecoService {

    private final IEnderecoRepository er;
    private final EnderecoMapper em;
    private final IPessoaService ps;

    public EnderecoServiceImp(IEnderecoRepository er, EnderecoMapper em, IPessoaService ps) {
        this.er = er;
        this.em = em;
        this.ps = ps;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnderecoDTO> list(Pageable pageable) {
        return er.searchAll(pageable).stream().map(em::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = false)
    public EnderecoDTO create(EnderecoDTO e) {
        Endereco endereco = em.toEntity(e);
        Pessoa p = ps.buscarPorNome(e.pessoa().getNome());
        endereco.setPessoa(p);
        return em.toDTO(er.save(endereco));

    }
    /**private void validarAtributos(EnderecoDTO request) {
        Optional<Endereco> endereco = er.findByCep(request.cep());
        if (endereco.isPresent() && !Objects.equals(endereco.get().getId(), request.id())) {
            throw new DataIntegrityViolationException("cep já cadastro no sistema!");
        }
     * @param id}
     * @return */

    @Override
    @Transactional(readOnly = true)
    public EnderecoDTO buscarEnderecoPorId(Long id) {
        return er.findById(id).map(em::toDTO).get();
    }

    @Override
    @Transactional(readOnly = true)
    public void delete(Long id) {
        er.deleteById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public EnderecoDTO update(Long id, EnderecoDTO endereco) {

        return er.findById(id)
                .map(upEndereco -> {
                    upEndereco.setUf(endereco.uf());
                    upEndereco.setCidade(endereco.cidade());
                    upEndereco.setBairro(endereco.bairro());
                    upEndereco.setRua(endereco.rua());
                    upEndereco.setCep(endereco.cep());
                    upEndereco.setNumero(endereco.numero());
                    upEndereco.setComplemento(endereco.complemento());
                    upEndereco.setPessoa(endereco.pessoa());
                    upEndereco.setId(endereco.id());
                    return em.toDTO(er.save(upEndereco));
                }).orElseThrow(() -> new ObjectNotFoundException("Este id não consta no bd! "));
    }

    @Override
    public Endereco buscarPorCep(String cep) {
        return er.findByCep(cep).orElse(new Endereco());
    }
}
