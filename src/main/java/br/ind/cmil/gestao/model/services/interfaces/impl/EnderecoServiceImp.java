package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.exceptions.ObjectNotFoundException;
import br.ind.cmil.gestao.model.dto.EnderecoDTO;
import br.ind.cmil.gestao.model.dto.FuncionarioDTO;
import br.ind.cmil.gestao.model.dto.mappers.EnderecoMapper;
import br.ind.cmil.gestao.model.dto.mappers.PessoaMapper;
import br.ind.cmil.gestao.model.entidades.Endereco;
import br.ind.cmil.gestao.model.entidades.Pessoa;
import br.ind.cmil.gestao.model.repositorys.IEnderecoRepository;
import br.ind.cmil.gestao.model.services.interfaces.IEnderecoService;
import br.ind.cmil.gestao.model.services.interfaces.IPessoaService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
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
    private final PessoaMapper pm;
    private final IPessoaService ps;

    public EnderecoServiceImp(IEnderecoRepository er, EnderecoMapper em, PessoaMapper pm, IPessoaService ps) {
        this.er = er;
        this.em = em;
        this.pm = pm;
        this.ps = ps;
    }

   

    @Override
    @Transactional(readOnly = true)
    public List<EnderecoDTO> list(Pageable pageable) {
        return er.searchAll(pageable).stream().map(em::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = false)
    public EnderecoDTO create(Long pessoa_id, EnderecoDTO e) {
        e.id();
        validarAtributos(e);
        Endereco endereco = em.toEntity(e);

        if (endereco.getId() == null) {
            Pessoa pessoa = ps.getPessoaById(pessoa_id);
            endereco.setPessoa(pessoa);
            return em.toDTO(er.save(endereco));

        }
        return update(e);

    }

    private void validarAtributos(EnderecoDTO request) {
        Optional<Endereco> endereco = er.findByCep(request.cep());
        if (endereco.isPresent() && !Objects.equals(endereco.get().getId(), request.id()) && !Objects.equals(endereco.get().getPessoa().getId(), request.pessoa().getId())) {
            throw new DataIntegrityViolationException("cep já cadastro no sistema!");
        }
        endereco = er.findByPessoa(request.pessoa().getId());
        if (endereco.isPresent() && !Objects.equals(endereco.get().getPessoa().getId(), request.pessoa().getId())) {
            throw new DataIntegrityViolationException("pessoa já cadastro no sistema!");
        }
    }

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
    public EnderecoDTO update(EnderecoDTO endereco) {

        return er.findById(endereco.id())
                .map(upEndereco -> {
                    upEndereco.setUf(endereco.uf());
                    upEndereco.setCidade(endereco.cidade());
                    upEndereco.setBairro(endereco.bairro());
                    upEndereco.setRua(endereco.rua());
                    upEndereco.setCep(endereco.cep());
                    upEndereco.setNumero(endereco.numero());
                    upEndereco.setComplemento(endereco.complemento());
                    upEndereco.setPessoa(pm.toEntity((FuncionarioDTO) endereco.pessoa()));
                    upEndereco.setId(endereco.id());
                    return em.toDTO(er.save(upEndereco));
                }).orElseThrow(() -> new ObjectNotFoundException("Este id não consta no bd! "));
    }

    @Override
    public Endereco buscarPorCep(String cep) {
        return er.findByCep(cep).orElse(new Endereco());
    }
}
