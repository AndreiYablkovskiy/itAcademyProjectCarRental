package project.EE.service;

import project.EE.model.entity.RepairPayment;

public interface RepairPaymentService {
    void save(RepairPayment repairPayment);
    RepairPayment findById(Integer id);
    RepairPayment createRepairPaymentForOrder(Integer orderId);
    void saveRepairPaymentAndUpdateCarStatus(Integer carId, Integer carStatusId, RepairPayment repairPayment);
}
