package project.EE.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.EE.model.entity.CarStatus;
import project.EE.model.repository.CarStatusRepository;
import project.EE.service.CarStatusService;

@Service
@RequiredArgsConstructor
public class CarStatusServiceImpl implements CarStatusService {
    private final CarStatusRepository carStatusRepository;

    @Override
    public CarStatus findById(Integer id) {
        return carStatusRepository.getById(id);
    }
}
