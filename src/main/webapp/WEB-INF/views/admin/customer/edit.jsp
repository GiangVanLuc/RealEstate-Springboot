<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerAPI" value="/api/customer" />
<c:url var="customerEditURL" value = "/admin/customer-edit"/>
<html>
<head>
    <title>Chỉnh sửa thông tin</title>
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
                <li class="active">Chỉnh sửa khách hàng</li>
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
                                    <form:input class="form-control" path="status" id="status"/>
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
          <button class="btn btn-lg btn-primary" onclick="transactionType('${item.key}', '${customerEdit.id}')">
            <i class="orange ace-icon fa fa-location-arrow bigger-130"></i>
            Add
          </button>
        </div>

        <c:if test="${item.key == 'CSKH'}">
    <div class="col-xs-12">
      <table id="simple-table" class="table table-striped table-bordered table-hover">
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
          <tr>
           <td>12/05/2025</td>
           <td>Ngyễn văn A</td>
           <td>12/09/2025</td>
           <td>Ngyễn văn B</td>
           <td>Gọi Điện và tư vấn</td>
           <td>
           <td>
            <div class="hidden-sm hidden-xs btn-group">
                <button class="btn btn-xs btn-info" data-toggle="tooltip" title="sửa thông tin giao dịch"
                        onclick="UpdateTransaction(1)">
                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                </button>
            </div>
            </td>
</tr>
        </tbody>
      </table>
    </div>
  </c:if>

  <c:if test="${item.key == 'DDX'}">
    <div class="col-xs-12">
      <table id="simple-table" class="table table-striped table-bordered table-hover">
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
          <tr>
            <td>12/05/2025</td>
           <td>Ngyễn văn A</td>
           <td>12/09/2025</td>
           <td>Ngyễn văn B</td>
           <td>Gọi Điện và tư vấn</td>
            <div class="hidden-sm hidden-xs btn-group">
                <button class="btn btn-xs btn-info" data-toggle="tooltip" title="sửa thông tin giao dịch"
                        onclick="UpdateTransaction(1)">
                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                </button>
            </div>
            </td>
</tr>
        </tbody>
      </table>
    </div>
  </c:if>
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
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Nhập giao dịch</h4>
      </div>
      <div class="modal-body">
        <div class="form-group has-success">
          <label for="transactionDetail" class="col-xs-12 col-sm-3 control-label no-padding-right">
            Chi tiết giao dịch
          </label>
          <div class="col-xs-12 col-sm-9">
            <span class="block input-icon input-icon-right">
              <input type="text" id="transactionDetail" class="width-100">
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

    function UpdateTransaction(id) {
        $('#transactionTypeModal').modal();
        $('#id').val(id);

    }

    $('#btnAddOrUpdateTransaction').click(function (e) {
        e.preventDefault();
        var data = {};
        data['id'] = $('#id').val();
        data['customerId'] = $('#customerId').val();
        data['code'] = $('#code').val();
        data['transactionDetail'] = $('#transactionDetail').val();
        // customerId + code + transactionDetail

        addTransaction(data);
    });

    function addTransaction(data) {
        $.ajax({
            type: "POST",
            url: "${customerAPI}/transaction",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "JSON",
            success: function (respond) {
                console.log("success");
                alert("add transaction success");
            },
            error: function (respond) {
                console.log("error");
                window.location.href = "<c:url value='/admin/customer-edit?message=error' />";
            }
        })
    }

    $('#btnAddOrUpdateCustomer').click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $('#listForm').serializeArray();
        $.each(formData, function (i, v) {
            data["" + v.name + ""]  = v.value;
        })
        updateAndCreateCustomer(data);
    });

    function updateAndCreateCustomer(data) {
        // call API
        $.ajax({
            type: "POST",
            url: "${customerAPI}",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "JSON",
            success: function (respond) {
                window.location.href = '<c:url value = "/admin/customer-list?message=success" />';
            },
            error: function (respond) {

                window.location.href = '<c:url value = "/admin/customer-edit?message=error" />';
            }
        })
}
    $('#btnCancel').click(function () {
        window.location.href = "${customerAPI}";
});

</script>
</body>
</html>