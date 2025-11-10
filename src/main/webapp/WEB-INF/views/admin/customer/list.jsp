<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<%-- 1. Định nghĩa các URL (giống file building) --%>
<c:url var="customerListURL" value="/admin/customer-list"/>
<c:url var="customerAPI" value="/api/customer"/>
<c:url var="customerEditURL" value="/admin/customer-edit"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Danh sách khách hàng</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check("breadcrumbs", "fixed");
                } catch (e) {
                }
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href='<c:url value="/admin/home"/>'> Trang chủ</a>
                </li>
                <li class="active">Quản lý khách hàng</li>
            </ul>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="widget-box ui-sortable-handle">
                    <div class="widget-header">
                        <h5 class="widget-title">Tìm kiếm</h5>
                        <div class="widget-toolbar">
                            <a href="#" data-action="collapse">
                                <i class="ace-icon fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>

                    <div class="widget-body" style="font-family: 'Times New Roman', Times, serif">
                        <div class="widget-main">
                            <%-- 2. Chuyển form HTML sang Spring Form --%>
                            <form:form modelAttribute="modelSearch" id="listForm" action="${customerListURL}" method="GET">
                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-4">
                                                <label class="name"> Tên khách hàng</label>
                                                <form:input class="form-control" path="fullName" type="text"/>
                                            </div>
                                            <div class="col-xs-4">
                                                <label class="name"> SĐT </label>
                                                <form:input class="form-control" path="customerPhone" type="text"/>
                                            </div>
                                            <div class="col-xs-4">
                                                <label class="name"> Email </label>
                                                <form:input class="form-control" path="email" type="text"/>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <c:if test="${roles eq 'ROLE_MANAGER'}">
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-4">
                                                    <div>
                                                        <label class="name">Chọn nhân viên phụ trách</label>
                                                        <form:select class="form-control" path="managementStaff">
                                                            <form:option value="">---Chọn Nhân Viên---</form:option>
                                                            <form:options items="${listStaffs}"/>
                                                        </form:select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    
                                   <div class="form-group">
                                       <div class="col-xs-12">
                                            <div class="col-xs-4">
                                                <button type="button" class="btn btn-danger" id="btnSearch">
                                                    <i class="ace-icon fa fa-search"></i>
                                                    Tìm kiếm
                                                </button>
                                            </div>
                                       </div>
                                   </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
                
                <c:if test="${roles eq 'ROLE_MANAGER'}">
                    <div class="pull-right">
                            <a href="/admin/customer-edit">
                                <button class="btn btn-info" title="thêm khách hàng">
                                    <i class="ace-icon fa fa-user-plus"></i>
                                </button>
                            </a>
                            <a href="#">
                                <button class="btn btn-danger" title="xóa khách hàng" id = "btnDeleteCustomer">
                                      <i class="ace-icon fa fa-user-times"></i>
                                </button>
                            </a>
                    </div>
                </c:if>
                
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <div class="table-responsive">
                    <display:table name="modelSearch.listResult"
                       cellspacing="0" cellpadding="0"
                       requestURI="${customerListURL}"
                       partialList="true" sort="external"
                       size="${modelSearch.totalItems}"
                       defaultsort="2" defaultorder="ascending"
                       id="tableList"
                       pagesize="${modelSearch.maxPageItems}"
                       export="false"
                       class="table table-fcv-ace table-striped table-bordred table-hover dataTable no-footer"
                       style="margin: 3em 0 1.5em;">
                            <display:column title = "<fieldset class = 'form-group'>
                            <input type = 'checkbox' id = 'checkALl' class = 'check-box-element'>
                            </fieldset>" class = "center select-cell" headerClass="center select-cell">
                               <fieldset>
                                <input type = "checkbox" name = "checkList" value = "${tableList.id}"
                                id = "checkbox_${tableList.id}" class = "check-box-element"/>
                            </fieldset>
                            </display:column>
                            <display:column headerClass="text-left" property="fullName" title="Tên khách hàng" />
                            <display:column headerClass="text-left" property="customerPhone" title="Di động"/>
                            <display:column  headerClass="text-left" property="email" title="Email" />
                            <display:column  headerClass="text-left" property="demand" title="Nhu cầu" />
                            <display:column  headerClass="text-left" property="createdBy" title="Người thêm" />
                            <display:column  headerClass="text-left" property="createdDate" title="Ngày thêm" />
                            <display:column  headerClass="text-left" property="status" title="Trạng thái" />
                            <display:column headerClass="col-action" title="Thao tác" escapeXml="false">
                            <security:authorize access="hasRole('MANAGER')">
                                <a class="btn btn-xs btn-success" title="Giao khách hàng" onclick="assignmentCustomer(${tableList.id})">
                                    <i class="ace-icon fa fa-list"></i>
                                </a>
                            </security:authorize>
                            <a class="btn btn-xs btn-info"
                               href="/admin/customer-edit-${tableList.id}"
                               title="Sửa thông tin khách hàng">
                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                            </a>
                            <security:authorize access="hasRole('MANAGER')">
                                <a type="button"
                                        class="btn btn-xs btn-danger"
                                        title="Xóa Khách hàng"
                                        onclick="deleteCustomer(${tableList.id})">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                </a>
                            </security:authorize>
                            </display:column>

                    </display:table>
                </div>
            </div>
        </div>
    </div>
</div>


<%-- 5. Đổi tên Modal cho Customer --%>
<div class="modal" id="assignmentCustomerModal" role="dialog" style="font-family: 'Times New Roman', Times, serif;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"></button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <table style="margin: 3em 0 1.5em" class="table table-striped table-bordered table-hover"
                       id="staffList">
                    <thead>
                    <tr>
                        <th>Chọn</th>
                        <th>Tên Nhân Viên</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <input type="hidden" id="customerId" name="customerId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" id="btnAssignmentCustomer">Giao khách hàng</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>

<%-- 6. Đổi tên toàn bộ JavaScript --%>
<script>


    // Mở Modal và load danh sách staff
    function assignmentCustomer(customerId) {
        $("#assignmentCustomerModal").modal("show");
        loadStaffs(customerId);
        $('#customerId').val(customerId);
    }

    // Tải danh sách staff (AJAX GET)
    function loadStaffs(customerId) {
        $.ajax({
            type: "GET",
            url: "${customerAPI}/" + customerId + '/staffs',
            dataType: "JSON",
            success: function (response) {
                var row = '';
                $.each(response.data, function (index, item) {
                    row += '<tr>';
                    row += '<td class="text-center"><input type="checkbox" value="' + item.staffId + '" id="checkbox_' + item.staffId + '" class="check-box-element" ' + item.checked + '/></td>';
                    row += '<td class="text-center">' + item.fullName + '</td>';
                    row += '</tr>';
                });
                $('#staffList tbody').html(row);
                console.log("Success Load Staffs");
            },
            error: function (response) {
                console.log("Fail Load Staffs");
                console.log(response);
            }
        })
    }

    // Gán staff cho customer (AJAX PUT)
    $('#btnAssignmentCustomer').click(function (e) {
        e.preventDefault();
        var data = {};
        data['customerId'] = $('#customerId').val();
        var staffs = $('#staffList').find('tbody input[type="checkbox"]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffs'] = staffs;
        
        assignmentCustomerForStaff(data);
    });

    function assignmentCustomerForStaff(data) {
        $.ajax({
            type: "POST", // Thường là POST hoặc PUT
            url: "${customerAPI}/" + 'assignment',
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function (response) {
                console.log("Success Assignment");
                window.location.href = '<c:url value = "/admin/customer-list?message=success" />';
            },
            error: function (response) {
                console.info("Fail Assignment");
                window.location.href = '<c:url value = "/admin/customer-list?message=error" />';
            }
        })
    }
    
    // Nút tìm kiếm
    $('#btnSearch').click(function (e) {
        e.preventDefault();
        $('#listForm').submit();
    });


    // Xóa 1 customer
    function deleteCustomer(customerId) {
        showAlertBeforeDelete(function () {
            handleDeleteCustomer([customerId]);
        })
    }



    // Xóa nhiều customer
    $('#btnDeleteCustomer').click(function (e) {
        e.preventDefault();
        var customerIds = $('#tableList').find('tbody input[type="checkbox"]:checked').map(function () {
            return $(this).val();
        }).get();
        if (customerIds.length === 0) {
            alert("Vui lòng chọn ít nhất một khách hàng để xóa!");
            return;
        }
        handleDeleteCustomer(customerIds);
    });

    // Hàm xóa (AJAX DELETE)
    function handleDeleteCustomer(data) {

        $.ajax({
            type: "DELETE",
            url: "${customerAPI}", // URL API, server sẽ nhận list ID từ Request Body
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function (response) {
                alert("Delete customer successfully!");
                window.location.href = '<c:url value = "/admin/customer-list?message=delete_success" />';
            },
            error: function (response) {
                console.log("Fail Delete");
                window.location.href = '<c:url value = "/admin/customer-list?message=error" />';
            }
        })

    }

    // Checkbox "Chọn tất cả"
    $('#checkAll').on('change', function () {
        $('input[type="checkbox"].check-box-element').prop('checked', this.checked);
    });
</script>
</body>
</html>