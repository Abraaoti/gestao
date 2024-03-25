package br.ind.cmil.gestao.administrador.services.imp;

import br.ind.cmil.gestao.administrador.domain.Administrador;
import br.ind.cmil.gestao.administrador.mapper.AdministradorMapper;
import br.ind.cmil.gestao.administrador.model.AdministradorDTO;
import br.ind.cmil.gestao.administrador.repository.AdministradorRepository;
import br.ind.cmil.gestao.administrador.services.AdministradorService;
import br.ind.cmil.gestao.datatables.Datatables;
import br.ind.cmil.gestao.datatables.DatatablesColunas;
import br.ind.cmil.gestao.usuario.domain.Usuario;
import br.ind.cmil.gestao.usuario.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ti
 */
@Service
public class AdministradorServiceImpl implements AdministradorService {

    private final AdministradorRepository ar;
    private final AdministradorMapper am;
    private final Datatables datatables;
    private final UsuarioRepository usuarioRepository;

    public AdministradorServiceImpl(AdministradorRepository ar, AdministradorMapper am, Datatables datatables, UsuarioRepository usuarioRepository) {
        this.ar = ar;
        this.am = am;
        this.datatables = datatables;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public void salvar(AdministradorDTO administradorDTO, String nome) {

        if (administradorDTO.id() == null) {
            validar(administradorDTO);
            Administrador administrador = am.toEntity(administradorDTO);
            Usuario usuario = usuarioRepository.findByNome(nome).get();
            administrador.setUsuario(usuario);
            ar.save(administrador);
        }

        update(administradorDTO);
    }

    @Transactional(readOnly = false)
    private Administrador update(AdministradorDTO administradorDTO) {
        Administrador administrador = ar.findById(administradorDTO.id()).get();
        administrador.setNome(administradorDTO.nome());
        Usuario usuario = administrador.getUsuario() == null ? null : usuarioRepository.findByNomeOrEmail(administradorDTO.usuario(), administradorDTO.usuario()).get();
        administrador.setUsuario(usuario);
        administrador.setId(administradorDTO.id());
        return ar.save(administrador);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> administradores(HttpServletRequest request) {

        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.ADMINISTRADOR);
        Page<?> page = datatables.getSearch().isEmpty() ? ar.findAll(datatables.getPageable())
                : ar.searchAll(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        Optional<Administrador> administrador = ar.findById(id);
        ar.delete(administrador.get());
    }

    @Override
    @Transactional(readOnly = true)
    public AdministradorDTO buscarPorUsuarioId(Long id) {
        return am.toDTO(ar.findById(id).get());
    }

    @Override
    @Transactional(readOnly = true)
    public AdministradorDTO buscarPorEmail(String email) {
        return am.toDTO(ar.findByUsuarioNome(email).get());
    }

    @Transactional(readOnly = true)
    @Override
    public AdministradorDTO checarDados(String nome) {

        Optional<Administrador> administrador = ar.findByUsuarioNome(nome);
        if (administrador.isEmpty() || administrador.get().getId() == null) {
            Usuario usuario = usuarioRepository.findByNome(nome).get();

            Administrador administradorUp = new Administrador(usuario);
            return am.toDTO(administradorUp);
        }
        return am.toDTO(administrador.get());

    }

    private void validar(AdministradorDTO a) {
        Optional<Administrador> administrador = ar.findByNome(a.nome());
        if (administrador.isPresent() && !Objects.equals(administrador.get().getId(), a.id())) {
            throw new DataIntegrityViolationException("Administrador já cadastro no sistema!");
        }

    }

}
