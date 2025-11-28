<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerAPI" value="/api/customer" />
<c:url var="customerEditURL" value = "/admin/customer-edit"/>
<html>
<head>
    <title>Thông tin khách hàng</title>
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
                    <a href="#">Trang chủ</a>
                </li>
                <c:if test="${not empty customerEdit.id}">
                <li class="active">Chỉnh sửa thông tin khách hàng</li>
                </c:if>
                <c:if test="${empty customerEdit.id}">
                <li class="active">Thêm khách hàng</li>
                </c:if>
            </ul>
            <!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="page-header">
                <h1>
                    Thông tin khách hàng
                </h1>
            </div>
            <!-- /.page-header -->
            <!-- Bảng danh sách -->

            <div class="row" >
                <form:form modelAttribute="customerEdit" action="${customerEditURL}" id="listForm" method="POST">
                    <div class="col-xs-12" >
                        <form class="form-horizontal" role="form" id="form-edit">
                            <div class="form-group">
                                <label class="col-xs-3 control-group no-padding-right" for="name" >Tên khách hàng</label>
                                <div class="col-xs-9 mb-3">
                                    <form:input class="form-control" path="fullName" id="name"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-group no-padding-right" for="customerPhone"> Số điện thoại</label>
                                <div class="col-xs-9 mb-3">
                                    <form:input class="form-control" path="customerPhone" id="customerPhone"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-group no-padding-right" for="email">Email</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="email" id="email"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-group no-padding-right" for="companyName">Tên Công ty</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="companyName" id="companyName"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-group no-padding-right" for="demand">Nhu cầu</label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="demand" id="demand"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-group no-padding-right" for="status">Tình trạng</label>
                                <div class="col-xs-9">
                                    <form:select class="form-control" path="status" id="status">
                                        <form:option value="">---Chọn tình trạng---</form:option>
                                        <form:options items="${statusCode}"/>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-group no-padding-right"></label>
                                <div class="col-xs-9">
                                    <c:if test="${not empty customerEdit.id}">
                                        <button type="button" class="btn btn-primary" id="btnAddOrUpdateCustomer">Cập nhật thông tin</button>
                                        <button type="button" class="btn btn-primary" id = "btnCancel">Hủy thao tác</button>
                                    </c:if>
                                    <c:if test="${empty customerEdit.id}">
                                        <button type="button" class="btn btn-primary" id="btnAddOrUpdateCustomer">Thêm khách hàng </button>
                                        <button type="button" class="btn btn-primary" id = "btnCancel">Hủy thao tác</button>
                                    </c:if>
                                </div>
                            </div>
                        <form:hidden path="id" id="customerId"/>
                        <form:hidden path="modifiedDate" id="modifiedDate"/>
                        <form:hidden path="modifiedBy" id="modifiedBy"/>
                        </form>
                    </div><!-- /.span -->

            </form:form>
            </div>
                <!-- /.row -->
        </div>
        <!-- /.page-content -->
    </div>

    <c:forEach var="item" items="${transactionType}">
      <div class="col-xs-12">
        <div class="col-sm-12">
          <h3 class="header smaller lighter blue">${item.value}</h3>
          <button class="btn btn-lg btn-primary" onclick="transactionType('${item.key}','${customerEdit.id}')">
            <i class="orange ace-icon fa fa-location-arrow bigger-130"></i>Add
          </button>
        </div>
        <div class="col-xs-12">
          <table class="table table-striped table-bordered table-hover">
            <thead>
              <tr>
                <th>Ngày tạo</th>
                <th>Người tạo</th>
                <th>Ngày sửa</th>
                <th>Người sửa</th>
                <th>Chi tiết giao dịch</th>
                <th>Thao tác</th>
              </tr>
            </thead>
            <tbody>
                <c:forEach var="list" items="${item.key == 'CSKH' ? CSKHList : DDXList }">
                    <tr>
                        <td>${list.createdDate}</td>
                        <td>${list.createdBy}</td>
                        <td>${list.modifiedDate}</td>
                        <td>${list.modifiedBy}</td>
                        <td>${list.note}</td>
                        <td>
                            <div class="hidden-sm hidden-xs btn-group">
                                <button class="btn btn-xs btn-primary" title="sửa chi tiết giao dịch" onclick="showFormTransaction(${list.id},'${item.key}')">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
</c:forEach>
</div>
<!-- /.main-content -->

<!-- Modal -->
<div class="modal fade" id="transactionTypeModal" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content -->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"></button>
        <h4 class="modal-title">Nhập giao dịch</h4>
      </div>
      <div class="modal-body">
        <div class="form-group has-success">
          <label for="transactionNote" class="col-xs-12 col-sm-3 control-label no-padding-right">
            Chi tiết giao dịch
          </label>
          <div class="col-xs-12 col-sm-9">
            <span class="block input-icon input-icon-right">
              <input type="text" id="transactionNote" class="width-100">
            </span>
          </div>
        </div>

        <input type="hidden" name="customerId" id="customerId" value="">
        <input type="hidden" name="code" id="code" value="">
        <input type="hidden" name="id" id="id" value="">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" id="btnAddOrUpdateTransaction">
          Thêm giao dịch
        </button>
        <button type="button" class="btn btn-default" data-dismiss="modal">
          Đóng
        </button>
      </div>
    </div>
  </div>
</div>




<script>
    function transactionType(code, customerId){
        $('#transactionTypeModal').modal();
        $('#code').val(code);
        $('#customerId').val(customerId);
    }

    function showFormTransaction(id, code) {
        $('#transactionTypeModal').modal();
        $('#id').val(id);
        $('#code').val(code);
        loadTransactionDetail(id);
    }

    function loadTransactionDetail(id) {
    $.ajax({
        url: "/api/customer/" + id + "/details",
        type: "GET",
        dataType: "json",
        success: function (res) {
            let row = '';
            $.each(res.data, function (index, item) {
                row += '<input type="text" id="transactionNote" class="width-100" value="' + item + '"/>';
            });
            $("#formSerial .input-icon").html(row);
            $("#transactionTypeModal").modal();
        },
        error: function (res) {
            window.alert("Fail");
        }
    });
}


    $('#btnAddOrUpdateTransaction').click(function (e) {
        e.preventDefault();
        var data = {};
        var id = $('#id').val();
        data['id'] = id;
        let customerId = $('#customerId').val();
        data['customerId'] = customerId;
        data['code'] = $('#code').val();
        data['note'] = $('#transactionNote').val();
        // customerId + code + transactionDetail
        if(id !== ''){
            updateTransaction(data, customerId);
        }
        addTransaction(data, customerId);
    });

    function updateTransaction(id, data) {
       $.ajax({
            type: "PUT",
            url: "/api/customer/transaction",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function (res) {
                alert("Update transaction success");
                window.location.href="${customerEditURL}" + "-" + id;
            },
            error: function (res) {
                alert("Update transaction error");
                window.location.href = "<c:url value='/admin/customer-list'/>";
            }
       })
    }

    function addTransaction(data, customerId) {
        $.ajax({
            type: "POST",
            url: "${customerAPI}/transaction",
            data: JSON.stringify(data),
            contentType: "application/json",
            //dataType: "JSON",
            success: function (respond) {
                alert("Add transaction success");
                window.location.href="${customerEditURL}" + "-" + customerId;
            },
            error: function (respond) {
                alert("Add transaction error");
                window.location.href = "<c:url value='/admin/customer-list'/>";
            }
        })
    }

    $('#btnAddOrUpdateCustomer').click(function (e) {
    e.preventDefault();
    var data = {};
    var formData = $('#listForm').serializeArray();
    $.each(formData, function (i, v) {
        data["" + v.name + ""] = v.value;
    });
    data['modifiedDate'] = $('#modifiedDate').val();
    data['modifiedBy'] = $('#modifiedBy').val().toString();

    var customerId = $('#customerId').val(); // Lấy ID

    if (customerId) {
        // Nếu có ID, đây là Cập nhật (UPDATE)
        updateCustomer(data, customerId);
    } else {
        // Nếu không có ID, đây là Thêm mới (ADD)
        addCustomer(data);
    }
});

    function addCustomer(data) {
        $.ajax({
            type: "POST", // Dùng POST
            url: "${customerAPI}", // URL không có ID
            data: JSON.stringify(data),
            contentType: "application/json",
            // dataType: "JSON",
            success: function (respond) {
                alert("success");
                window.location.href = '<c:url value = "/admin/customer-list" />';
            },
            error: function (respond) {
                window.location.href = '<c:url value = "/admin/customer-edit?message=error" />';
            }
        });
    }

    function updateCustomer(data, id) {
        $.ajax({
            type: "PUT", // ⭐ Sửa thành PUT
            url: "${customerAPI}/" + id, // ⭐ Thêm ID vào URL
            data: JSON.stringify(data),
            contentType: "application/json",
            // dataType: "JSON",
            success: function (respond) {
                window.location.href = '<c:url value = "/admin/customer-list?message=update_success" />';
            },
            error: function (respond) {
                window.location.href = '<c:url value = "/admin/customer-edit-' + id + '?message=error" />';
            }
        });
    }

    $('#btnCancel').click(function () {
    // Bạn đã định nghĩa customerListURL ở đầu file
    window.location.href = '<c:url value="/admin/customer-list" />';
});
</script>
</body>
</html>