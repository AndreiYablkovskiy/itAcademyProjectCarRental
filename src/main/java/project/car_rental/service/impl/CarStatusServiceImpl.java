package project.car_rental.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.car_rental.model.entity.CarStatus;
import project.car_rental.model.repository.CarStatusRepository;
import project.car_rental.service.CarStatusService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarStatusServiceImpl implements CarStatusService {
    private static final String REPAIR_STATUS = "repair";
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
