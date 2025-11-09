package com.javaweb.api.admin;


import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.BuildingService;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController(value = "customerAPIOfAdmin")
@Transactional
@RequestMapping("/api/customer")
public class CustomerAPI {

    @Autowired
    private CustomerService customerService;


//    @Autowired
//    private TransactionTypeService transactionTypeService;

    @PostMapping
    public ResponseEntity<CustomerDTO> addOrUpdateCustomer(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.addOrUpdateCustomer(customerDTO));
    }




//    @GetMapping("/{id}/staffs")
//    public ResponseDTO loadStaffs(@PathVariable Long id) {
//        // Dummy implementation for demonstration
//        ResponseDTO responseDTO = buildingService.listStaffs(id);
//        return responseDTO;
//    }

//    @GetMapping
//    public List<CustomerSearchResponse> searchCustomers(CustomerSearchRequest request) {
//        // Dummy implementation for demonstration
//        return customerService.searchCustomers(request);
//    }

    @PostMapping("/transaction")
    public void addOrUpdateTransaction(@RequestBody TransactionDTO transactionDTO) {
        System.out.println("ok");
    }

}
