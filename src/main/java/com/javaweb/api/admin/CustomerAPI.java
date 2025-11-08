package com.javaweb.api.admin;

import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.CustomerSearchDTO;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerAPI {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO createdCustomer = customerService.addCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(id, customerDTO);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Xóa khách hàng thành công");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteCustomers(@RequestBody List<Long> ids) {
        customerService.deleteCustomers(ids);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Xóa khách hàng thành công");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        CustomerDTO customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<Page<CustomerDTO>> getAllCustomers(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String searchValue) {
        
        Page<CustomerDTO> customers;
        
        if (searchValue != null && !searchValue.isEmpty()) {
            CustomerSearchDTO searchDTO = new CustomerSearchDTO();
            searchDTO.setSearchValue(searchValue);
            searchDTO.setPage(page);
            searchDTO.setSize(size);
            customers = customerService.searchCustomers(searchDTO);
        } else {
            customers = customerService.getAllCustomers(page, size);
        }
        
        return ResponseEntity.ok(customers);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<CustomerDTO>> searchCustomers(@RequestBody CustomerSearchDTO searchDTO) {
        Page<CustomerDTO> customers = customerService.searchCustomers(searchDTO);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Long>> getStatistics() {
        Map<String, Long> statistics = customerService.getStatisticsByCustomerType();
        return ResponseEntity.ok(statistics);
    }
}
