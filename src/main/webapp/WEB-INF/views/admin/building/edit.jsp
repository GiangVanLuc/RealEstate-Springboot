<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value="/api/building"/>
<c:url var="buildingList" value = "/admin/building-list"/>
<html>
<head>
    <title>Thêm tòa nhà</title>
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
                    <a href="#">Home</a>
                </li>
                <li class="active">Dashboard</li>
            </ul>
            <!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="page-header">
                <h1>
                    Danh sách tòa nhà
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        overview &amp; stats
                    </small>
                </h1>
            </div>
            <!-- /.page-header -->

            <div class="row">
                <div class="col-xs-12" bis_skin_checked="1">
                </div>
            </div>

            <!-- Bảng danh sách -->
            <form:form modelAttribute="buildingEdit" id="listForm" method="GET">
                <div class="row" >
                    <div class="col-xs-12" >
                        <form class="form-horizontal" role="form" >
                            <div class="form-group">
                                <form:label class="col-xs-3" path = "name">Tên tòa nhà</form:label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="name"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <form:label class="col-xs-3" path = "district">Quận</form:label>
                                 <div class="col-xs-9">
                                    <form:select class="form-control" path="district">
                                        <form:option value="">---Chọn Quận---</form:option>
                                        <form:options items="${districts}"/>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group">
                                <form:label class="col-xs-3" path = "ward">Phường</form:label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" type="text" path="ward"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <form:label class="col-xs-3" path = "street">Đường</form:label>
                                <div class="col-xs-9">
                                        <%-- <input class="form-control" type="text" id="street" name="street">--%>
                                    <form:input class="form-control" type="text" path="street"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <form:label class="col-xs-3" path = "structure">Kết cấu</form:label>
                                <div class="col-xs-9">
                                    <form:input class="form-control" path="structure"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Số tầng hầm</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="number" id="nameofbasement" name="nameofbasement" value = "">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Diện tích sàn</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" id="floorarea" name="floorarea" value ="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Hướng</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" id="direction" name="direction">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Hạng</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="number" id="level" name="level">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Diện tích thuê</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" id="rentarea" name="rentarea">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Giá thuê</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="number" id="rentprice" name="rentprice">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Mô tả giá</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" id="rentpricedescription"
                                           name="rentpricedescription">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Phí dịch vụ</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" id="name" name="name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Phí ô tô</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" id="name" name="name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Phí ngoài giờ</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" id="name" name="name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Tiền điện</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" id="name" name="name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Đặt cọc</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" id="name" name="name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Thanh toán</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" id="name" name="name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Thời hạn thuê</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" id="name" name="name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Thời gian trang trí</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" id="name" name="name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Tên quản lý</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" id="name" name="name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">SĐT quản lý</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" id="name" name="name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Phí môi giới</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" id="name" name="name">
                                </div>
                            </div>
                            <div class="form-group">
                                <span class="col-xs-3">Loại tòa nhà</span>
                                <div class="col-xs-9">
                                    <form:checkboxes items="${typeCodes}" path="typeCode"/>
                                </div>
                            </div>
                            <div class=form-group>
                                <label class="col-xs-3">Ghi chú</label>
                                <div class="col-xs-9">
                                    <input class="form-control" type="text" id="name" name="name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="hinhAnhInput" class="col-xs-3">Hình đại diện</label>
                                <div class="col-xs-9">
                                    <input type="file" id="hinhAnhInput" accept="image/*">

                                    <img id="hinhAnhPreview" src="#" alt="Ảnh xem trước" class="img-thumbnail"
                                         style="display: none;"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3"></label>
                                <div class="col-xs-9">
                                    <c:if test="${not empty buildingEdit.id}">
                                        <button type="button" class="btn btn-primary" id="btnAddOrUpdateBuilding">Cập nhật tòa nhà</button>
                                        <button type="button" class="btn btn-primary" id = "btnCancel">Hủy thao tác</button>
                                    </c:if>
                                    <c:if test="${empty buildingEdit.id}">
                                        <button type="button" class="btn btn-primary" id="btnAddOrUpdateBuilding">Thêm tòa nhà</button>
                                        <button type="button" class="btn btn-primary" id = "btnCancel">Hủy thao tác</button>
                                    </c:if>
                                </div>
                            </div>
                            <form:hidden path="id" id="buildingId"/>

                        </form>
                    </div><!-- /.span -->
                </div>
                <!-- /.row -->
            </form:form>
        </div>
        <!-- /.page-content -->
    </div>
</div>
<!-- /.main-content -->
<script>
    $('#btnAddOrUpdateBuilding').click(function () {
        var data = {};
        var typeCode = [];
        var formData = $('#listForm').serializeArray();
        $.each(formData, function (i, v) {
            if (v.name != 'typeCode') {
                data["" + v.name + ""] = v.value;
            } else {
                typeCode.push(v.value);
            }
        })
        data['typeCode'] = typeCode;
        if(typeCode != ''){
            addOrUpdateBuilding(data);
        }
        else{
            window.location.href = "<c:url value = "/admin/building-edit?typeCode=require" />";
        }
    });

    function addOrUpdateBuilding(data) {
        // call API
        $.ajax({
            type: "POST",
            url: "${buildingAPI}",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "JSON",
            success: function (respond) {
                console.log("Success");
            },
            error: function (respond) {
                console.log("Fail");
                console.log(respond);
            }
        })
}
    $('#btnCancel').click(function () {
        window.location.href = "${buildingList}";
});

</script>
</body>
</html>
