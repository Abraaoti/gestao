package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.model.entidades.Telefone;
import br.ind.cmil.gestao.model.repositorys.ITelefoneRepository;
import br.ind.cmil.gestao.model.services.interfaces.ITelefoneService;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraao
 */
@Service
public class TelefoneServiceImp implements ITelefoneService {

    private final ITelefoneRepository telefoneRepo;

    public TelefoneServiceImp(ITelefoneRepository telefoneRepo) {
        this.telefoneRepo = telefoneRepo;
    }

    @Override
    @Transactional(readOnly = false)
    public Telefone save(Telefone telefone) {
        Telefone addTelefone = new Telefone();
        if (telefone.getId() == null) {
            addTelefone.setNumero(telefone.getNumero());
            addTelefone.setPessoa(telefone.getPessoa());
            addTelefone.setTipo(convertTipoTelefoneValue("pessoal"));
            telefoneRepo.save(addTelefone);
            return ;
        }
            return null ;

    }

    @Override
    @Transactional(readOnly = true)
    public Telefone buscarTelefonePorId(Long id) {
        return telefoneRepo.findById(id).orElse(new Telefone());
    }

    @Override
    @Transactional(readOnly = true)
    public void delete(Long id) {
        telefoneRepo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public Telefone update(Telefone telefone) {
        Optional<Telefone> dbTelefone = telefoneRepo.findById(telefone.getId());
        if (dbTelefone.isPresent()) {
            Telefone upTelefone = dbTelefone.get();
            upTelefone.setNumero(telefone.getNumero());
            upTelefone.setTipo(convertTipoTelefoneValue(telefone.getTipo().getValue()));
            upTelefone.setPessoa(telefone.getPessoa());
            upTelefone.setId(telefone.getId());
            return ;

        }
        return ;
    }

    @Override
    public Telefone buscarPorNumero(String cep) {
        return telefoneRepo.findByNumero(cep).orElse(new Telefone());
    }

    public TipoTelefone convertTipoTelefoneValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "pessoal" ->
                Genero.FEMININO;
            case "comercial" ->
                Genero.MASCULINO;
            case "residencial" ->
                Genero.OUTROS;
            default ->
                throw new IllegalArgumentException(" TipoTelefone invalido " + value);
        };
    }

}
