package com.javaweb.api.admin;


import com.javaweb.model.dto.AssignmentDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.StaffAssignmentDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.TransactionResponseDTO;
import com.javaweb.service.BuildingService;
import com.javaweb.service.CustomerService;
import com.javaweb.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController(value = "customerAPIOfAdmin")
@Transactional
@RequestMapping("/api/customer")
public class CustomerAPI {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionService transactionService;



    @PostMapping
    public void createCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.addOrUpdateCustomer(customerDTO);
    }

    @PutMapping("/{customerId}")
    public void updateCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.addOrUpdateCustomer(customerDTO);
    }

    @DeleteMapping
    public void deleteCustomer(@RequestBody List<Long> ids) {
        if(ids.size() > 0) {
            customerService.deleteCustomer(ids);
        }
    }




    @GetMapping("/{customerId}/staffs")
    public ResponseEntity<?> getStaffs(@PathVariable Long customerId) {
        List<StaffAssignmentDTO> staffs = customerService.findStaffsByCustomerId(customerId);
        Map<String, Object> response = new HashMap<>();
        response.put("data", staffs);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/assignment")
    public ResponseEntity<Void> assignCustomer(@RequestBody AssignmentDTO assignmentDTO) {
        customerService.assignCustomer(assignmentDTO);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/transaction")
    public void createTransaction(@RequestBody TransactionDTO transactionDTO) {
        transactionService.addOrUpdateTransaction(transactionDTO);
    }

    @GetMapping("/{transactionId}/details")
    public TransactionResponseDTO loadTransactionDetail(@PathVariable Long transactionId) {
        return transactionService.loadTransactionDetail(transactionId);
    }

}
