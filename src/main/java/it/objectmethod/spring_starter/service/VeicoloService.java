package it.objectmethod.spring_starter.service;

import it.objectmethod.spring_starter.mapper.VeicoloMapper;
import it.objectmethod.spring_starter.repository.VeicoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeicoloService {
    private final VeicoloRepository veicoloRepository;
    private final VeicoloMapper veicoloMapper;

    @Autowired
    public VeicoloService(VeicoloRepository veicoloRepository, VeicoloMapper veicoloMapper) {
        this.veicoloRepository = veicoloRepository;
        this.veicoloMapper = veicoloMapper;
    }
}
