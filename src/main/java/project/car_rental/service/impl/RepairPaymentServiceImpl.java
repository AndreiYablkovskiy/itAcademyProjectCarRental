package project.car_rental.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.car_rental.model.entity.Order;
import project.car_rental.model.repository.RepairPaymentRepository;
import project.car_rental.service.CarService;
import project.car_rental.model.entity.RepairPayment;
import project.car_rental.service.OrderService;
import project.car_rental.service.RepairPaymentService;

@Service
@RequiredArgsConstructor
public class RepairPaymentServiceImpl implements RepairPaymentService {
   private final RepairPaymentRepository repairPaymentRepository;
   private final OrderService orderService;
   private final CarService carService;

    @Override
    public void save(RepairPayment repairPayment) {
        repairPaymentRepository.save(repairPayment);
    }

    @Override
    public RepairPayment findById(Integer id) {
        return repairPaymentRepository.getById(id);
    }

    @Override
    @Transactional
    public RepairPayment createRepairPaymentForOrder(Integer orderId) {
        RepairPayment repairPayment = new RepairPayment();
        Order order = orderService.findById(orderId).get();
        repairPayment.setOrder(order);
        return repairPayment;
    }

    @Override
    @Transactional
    public void saveRepairPaymentAndUpdateCarStatus(Integer carId, Integer carStatusId, RepairPayment repairPayment) {
        carService.updateCarStatus(carId, carStatusId);
        save(repairPayment);
    }
}
