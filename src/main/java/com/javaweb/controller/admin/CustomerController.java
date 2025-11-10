package com.javaweb.controller.admin;


import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.StatusCode;
import com.javaweb.enums.TransactionType;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.MyUserDetail;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.CustomerService;
import com.javaweb.service.IUserService;
import com.javaweb.service.TransactionService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller(value = "customerControllerOfAdmin")
public class CustomerController {

    @Autowired
    private IUserService iuserService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionService transactionService;







    @GetMapping(value = "/admin/customer-list")
    public ModelAndView customerList(@ModelAttribute("modelSearch") CustomerDTO customerDTO, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/list");
        mav.addObject("listStaffs", iuserService.getStaffs());

        // Lấy thông tin phân trang từ displaytag
        DisplayTagUtils.of(request, customerDTO);

        // Lấy user hiện tại
        MyUserDetail user = SecurityUtils.getPrincipal();

        // Tìm kiếm danh sách khách hàng
        List<CustomerDTO> customers = customerService.searchCustomers(user, customerDTO, PageRequest.of(customerDTO.getPage() - 1, customerDTO.getMaxPageItems()));

        // Set dữ liệu vào DTO
        customerDTO.setListResult(customers);

        customerDTO.setTotalItems(customerService.totalItems(user, customerDTO));

        // Lấy vai trò người dùng
        List<String> roles = SecurityUtils.getAuthorities();

        // Gửi dữ liệu sang view
        mav.addObject("customerList", customers);
        mav.addObject("roles", SecurityUtils.getAuthorities().get(0));


        return mav;
    }

    @GetMapping(value = "/admin/customer-edit")
    public ModelAndView addCustomer(@ModelAttribute("customerEdit") CustomerDTO customerDTO) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("statusCode", StatusCode.getStatusCode());
        return mav;
    }

    @GetMapping(value = "/admin/customer-edit-{id}")
    public ModelAndView updateCustomer(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("statusCode", StatusCode.getStatusCode());

        CustomerDTO customerDTO = customerService.findById(id);
        mav.addObject("customerEdit", customerDTO);
        mav.addObject("transactionType", TransactionType.transactionType());
        mav.addObject("CSKHList", transactionService.findByCodeAndCustomerId("CSKH", id));
        mav.addObject("DDXList", transactionService.findByCodeAndCustomerId("DDX", id));
        // findCodeAndCustomerId
        // 2 cai danh sach giao dich theo loại giao dịch
        //listType1
        //listType2

        return mav;
    }
}
