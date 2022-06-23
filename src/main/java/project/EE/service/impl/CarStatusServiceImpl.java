package project.EE.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.EE.model.entity.CarStatus;
import project.EE.model.repository.CarStatusRepository;
import project.EE.service.CarStatusService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarStatusServiceImpl implements CarStatusService {
    public static final String REPAIR_STATUS = "repair";
    private final CarStatusRepository carStatusRepository;

    @Override
    public CarStatus findById(Integer id) {
        return carStatusRepository.getById(id);
    }

    @Override
    public List<CarStatus> findAllWithoutRepairStatus() {
        return carStatusRepository.findByNameNot(REPAIR_STATUS);
    }

}
