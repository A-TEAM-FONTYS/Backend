package nl.teama.server.service;

import nl.teama.server.entity.Data;
import nl.teama.server.entity.User;
import nl.teama.server.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataService {

    private final DataRepository dataRepository;

    @Autowired
    public DataService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public List<Data> getAllByUser(User user) {
        return this.dataRepository.getAllByUser(user);
    }

    public Optional<Data> getDataByUser(User user) {
        return this.dataRepository.getDataByUser(user);
    }
}
