package br.ind.cmil.gestao.services.impl;

import br.ind.cmil.gestao.exceptions.CargoException;
import br.ind.cmil.gestao.datatables.Datatables;
import br.ind.cmil.gestao.datatables.DatatablesColunas;
import br.ind.cmil.gestao.domain.Cartao;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import br.ind.cmil.gestao.repositorys.CartaoRepository;
import br.ind.cmil.gestao.services.CartaoService;

/**
 *
 * @author abraao
 */
@Service
public class CartaoServiceImp implements CartaoService {

    private final CartaoRepository cartaoRepository;
    private final Datatables datatables;

    public CartaoServiceImp(CartaoRepository cartaoRepository, Datatables datatables) {
        this.cartaoRepository = cartaoRepository;
        this.datatables = datatables;
    }

 
 
    @Override
    @Transactional(readOnly = true)
    public List<Cartao> lista() {

        return cartaoRepository.searchAll().stream().collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Cartao findById(Long id) {
        return cartaoRepository.findById(id).orElseThrow(() -> new CargoException(String.valueOf(id), "Este id não consta no bd! "));
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void salvar(Cartao cartao) {
        cartao.getId();
        validarAtributos(cartao);

        if (cartao.getId() == null) {

            cartaoRepository.save(cartao);
        }

        update(cartao);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    protected Cartao update(Cartao c) {
        Optional<Cartao> cartao = cartaoRepository.findById(c.getId());
        if (cartao.isEmpty()) {
            return null;
        }

        var ca = cartao.get();
        ca.setNumero(c.getNumero());
        ca.setId(c.getId());

        return cartaoRepository.save(ca);

    }

    @Override
    public void delete(Long id) {
        cartaoRepository.delete(cartaoRepository.findById(id).orElseThrow(() -> new CargoException(String.valueOf(id), "Este id não consta no bd! ")));
    }

    private void validarAtributos(Cartao c) {
        Optional<Cartao> cartao = cartaoRepository.findByNumero(c.getNumero());
        if (cartao.isPresent() && !Objects.equals(cartao.get().getId(), c.getId())) {
            throw new DataIntegrityViolationException("cartao já cadastro no sistema!");
        }

    }

    @Override
    public Cartao findByNumero(String numero) {
        return cartaoRepository.findByNumero(numero).orElseThrow(() -> new CargoException(numero, "Este cargo não consta no bd! "));
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.CARGO);
        Page<Cartao> page = datatables.getSearch().isEmpty() ? cartaoRepository.findAll(datatables.getPageable())
                : cartaoRepository.searchAll(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }
    
    
}
