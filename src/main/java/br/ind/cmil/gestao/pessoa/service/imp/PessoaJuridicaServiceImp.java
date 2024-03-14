package br.ind.cmil.gestao.pessoa.service.imp;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import br.ind.cmil.gestao.pessoa.repository.PessoaJuridicaRepository;
import br.ind.cmil.gestao.pessoa.service.PessoaJuridicaService;
import br.ind.cmil.gestao.pessoa.domain.PessoaJuridica;

/**
 *
 * @author abraaocalelessocassi
 */
@Service
public class PessoaJuridicaServiceImp implements PessoaJuridicaService {

    private final PessoaJuridicaRepository empresaRepository;

    public PessoaJuridicaServiceImp(PessoaJuridicaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

   
    @Override
    public PessoaJuridica save(PessoaJuridica empresa) {
        return empresaRepository.save(empresa);
    }

    @Override
    public PessoaJuridica update(PessoaJuridica empresa) {
        return empresaRepository.save(empresa);
    }

    @Override
    public PessoaJuridica buscarEmpresaPorId(Long id) {
        return empresaRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        empresaRepository.deleteById(id);
    }

    @Override
    public List<PessoaJuridica> getEmpresas() {
        return empresaRepository.findAll(Sort.by("id"));
    }

}
