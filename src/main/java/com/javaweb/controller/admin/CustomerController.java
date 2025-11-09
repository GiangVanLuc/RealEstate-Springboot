package com.javaweb.controller.admin;


import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.TransactionType;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.service.CustomerService;
import com.javaweb.service.IUserService;
import com.javaweb.service.TransactionService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
        mav.addObject("staffmaps", iuserService.getStaffs());

        String pageParam = null;
        for (Object paramNameObject : request.getParameterMap().keySet()) {
            String paramName = (String) paramNameObject;
            if (paramName.endsWith("-p")) {
                pageParam = request.getParameter(paramName);
                break;
            }
        }

        if (pageParam != null) {
            customerDTO.setPage(Integer.parseInt(pageParam));
        } else {
            customerDTO.setPage(1);
        }


        return mav;
    }

    @GetMapping(value = "/admin/customer-edit")
    public ModelAndView addCustomer(@ModelAttribute("customerEdit") CustomerDTO customerDTO) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("customer", customerDTO);
        return mav;
    }

    @RequestMapping(value = "/admin/customer-edit-{id}")
    public ModelAndView addCustomer(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");

        // find customer by id
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(id);
        customerDTO.setFullName("Nguyen Van Manh");
        customerDTO.setCustomerPhone("0913746245");
        customerDTO.setEmail("lucgiang0904@gmail.com");
        customerDTO.setDemand("Cần thuê căn hộ chung cư");
        customerDTO.setStatus("Đang xử lý");
        customerDTO.setPage(1);
        customerDTO.setLimit(2);
        customerDTO.setMaxPageItems(1);
        mav.addObject("transactionType", TransactionType.transactionType());
        mav.addObject("customerEdit", customerDTO);
        // findCodeAndCustomerId
        // 2 cai danh sach giao dich theo loại giao dịch
        //listType1
        //listType2

        return mav;
    }
}
