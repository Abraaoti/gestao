package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.exceptions.ObjectNotFoundException;
import br.ind.cmil.gestao.model.entidades.Pessoa;
import br.ind.cmil.gestao.model.repositorys.IPessoaRepository;
import br.ind.cmil.gestao.model.services.interfaces.IPessoaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author abraao
 */
@Service
public class PessoaServiceImp implements IPessoaService {

    private final IPessoaRepository pr;

    public PessoaServiceImp(IPessoaRepository pr) {
        this.pr = pr;
    }

    @Override
    public List<Pessoa> getAllNome(String nome) {
        List<Pessoa> pessoas = new ArrayList<>();
        if (nome == null) {
            pr.findAll().forEach(pessoas::add);
        } else {
            pr.findByNomeContaining(nome).forEach(pessoas::add);
        }

        if (pessoas.isEmpty()) {
            return null;
        }
        return pessoas;
    }

    @Override
    public Pessoa getPessoaById(Long id) {
       return  pr.findById(id).orElseThrow(() -> new ObjectNotFoundException("Not found Tutorial with id = " + id));
    }

  
    @Override
    public Pessoa buscarPorNome(String nome) {
        return pr.findByNome(nome).orElseThrow(() -> new ObjectNotFoundException("Not found Tutorial with nome = " +nome));
    }

}