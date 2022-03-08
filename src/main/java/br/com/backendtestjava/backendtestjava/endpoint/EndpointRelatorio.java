package br.com.backendtestjava.backendtestjava.endpoint;

import br.com.backendtestjava.backendtestjava.entity.Relatorio;
import br.com.backendtestjava.backendtestjava.respository.RepositoryRelatorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relatorio")
public class EndpointRelatorio {

    @Autowired
    private RepositoryRelatorio repositoryRelatorio;

    @GetMapping
    public List<Relatorio> listAll() {
        return repositoryRelatorio.findAll();
    }

    @PostMapping
    public void addRelatorio(@RequestBody Relatorio relatorio) {
        if (relatorio.getId() != null) {
            relatorio.setId(null);
        }
        repositoryRelatorio.save(relatorio);
    }

    @PutMapping (path = "/{id}")
    public void editRelatorio(@PathVariable ("id") Long id, @RequestBody Relatorio relatorio) {
        if (repositoryRelatorio.findById(id).isPresent()) {
            relatorio.setId(id);
            repositoryRelatorio.save(relatorio);
        }
    }

    @DeleteMapping (path = "/{id}")
    public void deleteRelatorio(@PathVariable ("id") Long id) {
        repositoryRelatorio.deleteById(id);
    }
}
