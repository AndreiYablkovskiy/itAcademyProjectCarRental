package project.EE.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.EE.model.entity.RepairPayment;
import project.EE.model.repository.RepairPaymentRepository;
import project.EE.service.RepairPaymentService;

@Service
@RequiredArgsConstructor
public class RepairPaymentServiceImpl implements RepairPaymentService {
   private final RepairPaymentRepository repairPaymentRepository;

    @Override
    public void save(RepairPayment repairPayment) {
        repairPaymentRepository.save(repairPayment);
    }
}
