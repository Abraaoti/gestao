package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.model.entidades.Endereco;
import br.ind.cmil.gestao.model.repositorys.IEnderecoRepository;
import br.ind.cmil.gestao.model.services.interfaces.IEnderecoService;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraao
 */
@Service
public class EnderecoServiceImp implements IEnderecoService {

    private final IEnderecoRepository enderecoRepo;

    public EnderecoServiceImp(IEnderecoRepository enderecoRepo) {
        this.enderecoRepo = enderecoRepo;
    }

    @Override
    @Transactional(readOnly = false)
    public void save(Endereco endereco) {

        if (endereco.getId() == null) {

            enderecoRepo.save(endereco);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Endereco buscarEnderecoPorId(Long id) {
        return enderecoRepo.findById(id).orElse(new Endereco());
    }

    @Override
    @Transactional(readOnly = true)
    public void delete(Long id) {
        enderecoRepo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Endereco endereco) {
        Optional<Endereco> dbEndereco = enderecoRepo.findById(endereco.getId());
        if (dbEndereco.isPresent()) {
            Endereco upEndereco = dbEndereco.get();
            upEndereco.setUf(endereco.getUf());
            upEndereco.setCidade(endereco.getCidade());
            upEndereco.setBairro(endereco.getBairro());
            upEndereco.setRua(endereco.getRua());
            upEndereco.setCep(endereco.getCep());
            upEndereco.setNumero(endereco.getNumero());
            upEndereco.setComplemento(endereco.getComplemento());
            upEndereco.setPessoa(endereco.getPessoa());
            upEndereco.setId(endereco.getId());
        }
    }

    @Override
    public Endereco buscarPorCep(String cep) {
        return enderecoRepo.findByCep(cep).orElse(new Endereco());
    }
}
