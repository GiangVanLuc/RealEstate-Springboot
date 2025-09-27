<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingListURL" value="/admin/building-list"/>
<c:url var="buildingAPI" value="/api/building"/>
<html>
<head>
    <title>Danh sách tòa nhà</title>
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
                <li class="active">Quản lý toà nhà</li>
            </ul>
            <!-- /.breadcrumb -->
        </div>
        <!-- /.page-header -->

        <div class="row">
            <div class="col-xs-12" >
                <div class="widget-box ui-sortable-handle" bis_skin_checked="1">
                    <div class="widget-header" bis_skin_checked="1">
                        <h5 class="widget-title">Tìm kiếm</h5>

                        <div class="widget-toolbar" bis_skin_checked="1">
                            <a href="#" data-action="collapse">
                                <i class="ace-icon fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>

                    <div class="widget-body" style="font-family: 'Times New Roman', Times, serif" >
                        <div class="widget-main" >
                            <form:form modelAttribute="modelSearch" id="listForm" action="${buildingListURL}" method="GET">
                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-6">
                                                <label class="name"> Tên tòa nhà</label>
                                                    <%--                            <input type="text" class="form-control" name = "name" value = ""/>--%>
                                                <form:input class="form-control" path="name" type="text"/>
                                            </div>
                                            <div class="col-xs-6">
                                                <label class="name"> Diện tích sàn</label>
                                                    <%--                            <input id = "name" type="number" class="form-control" name = "floorArea" value = "" />--%>
                                                <form:input class="form-control" path="floorArea" type="text"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-2">
                                                <label class="name"> Quận Hiện có</label>
                                                <form:select class="form-control" path="district">
                                                    <form:option value="">---Chọn Quận---</form:option>
                                                    <form:options items="${districts}"/>

                                                </form:select>
                                            </div>
                                            <div class="col-xs-5">
                                                <label class="name"> Phường </label>
                                                    <%--                            <input type="text" class="form-control" name = "ward" value = ""/>--%>
                                                <form:input class="form-control" path="ward" type="text"/>
                                            </div>
                                            <div class="col-xs-5">
                                                <label class="name"> Đường </label>
                                                    <%--                            <input type="text" class="form-control" name = "street" value = ""/>--%>
                                                <form:input class="form-control" path="street" type="text"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-4">
                                                <label class="name"> Số tầng hầm</label>
                                                    <%--                            <input type="text" class="form-control" name = "numberOfBasements" value = ""/>--%>
                                                <form:input class="form-control" path="numberOfBasement" type="text"/>
                                            </div>
                                            <div class="col-xs-4">
                                                <label class="name"> Hướng</label>
                                                    <%--                            <input type="text" class="form-control" name = "direction" value = ""/>--%>
                                                <form:input class="form-control" path="direction" type="text"/>
                                            </div>
                                            <div class="col-xs-4">
                                                <label class="name"> Hạng </label>
                                                    <%--                            <input type="number" class="form-control" name = "level" value = ""/>--%>
                                                <form:input class="form-control" path="level" type="text"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-3">
                                                <label class="name"> Diện tích từ </label>
                                                    <%--                            <input type="number" class="form-control" name = "areaFrom" value = "" />--%>
                                                <form:input class="form-control" path="areaFrom" type="text"/>
                                            </div>
                                            <div class="col-xs-3">
                                                <label class="name"> Diện tích đến </label>
                                                    <%--                            <input type="number" class="form-control" name = "areaTo" value = "" />--%>
                                                <form:input class="form-control" path="areaTo" type="text"/>
                                            </div>
                                            <div class="col-xs-3">
                                                <label class="name"> Giá thuê từ </label>
                                                    <%--                            <input type="number" class="form-control" name = "rentPriceFrom" value = "" />--%>
                                                <form:input class="form-control" path="rentPriceFrom" type="text"/>
                                            </div>
                                            <div class="col-xs-3">
                                                <label class="name"> Giá thuê đến </label>
                                                    <%--                            <input type="number" class="form-control" name = "rentPriceTo" value = "" />--%>
                                                <form:input class="form-control" path="rentPriceTo" type="text"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-5">
                                                <label class="name"> Tên quản lý</label>
                                                    <%--                            <input type="text" class="form-control" name = "managerName" value = ""/>--%>
                                                <form:input class="form-control" path="managerName" type="text"/>
                                            </div>
                                            <div class="col-xs-5">
                                                <label class="name"> SĐT quản lý </label>
                                                    <%--                            <input type="number" class="form-control" name = "managerPhoneNumber" value = ""/>--%>
                                                <form:input class="form-control" path="managerPhone" type="text"/>
                                            </div>
                                            <div class="col-xs-2">
                                                <label class="name">
                                                    Chọn nhân viên phụ trách
                                                </label>
                                                <form:select class="form-control" path="staffId">
                                                    <form:option value="">---Chọn Nhân Viên---</form:option>
                                                    <form:options items="${listStaffs}"/>
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-6">
                                                <form:checkboxes items="${typeCodes}" path="typeCode"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-12">
                                        <div class="col-xs-6">
                                            <button type="button" class="btn btn-danger" id="btnSearchBuilding">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16"
                                                     height="16" fill="currentColor" class="bi bi-search"
                                                     viewBox="0 0 16 16">
                                                    <path
                                                            d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0">
                                                    </path>
                                                </svg>
                                                Tìm kiếm
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                    <div class="pull-right">
                        <a href="/admin/building-edit">
                            <button class="btn btn-info" title="thêm tòa nhà">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-building-add" viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
                                    <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                    <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                </svg>
                            </button>
                        </a>
                        <a href="#">
                            <button class="btn btn-danger" title="xóa tòa nhà" id = "btnDeleteBuilding">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-building-dash" viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"/>
                                    <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                    <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                </svg>
                            </button>
                        </a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bảng danh sách -->
        <div class="row">
            <div class="col-xs-12">
                        <form:form modelAttribute="buildingList" >
                            <display:table name="buildingList.listResult"
                                   cellspacing="0" cellpadding="0"
                                   requestURI="${buildingListURL}"
                                   partialList="true" sort="external"
                                   size="${buildingList.totalItems}"
                                   defaultsort="2" defaultorder="ascending"
                                   id="tableList"
                                   pagesize="${buildingList.maxPageItems}"
                                   export="false"
                                   class="table table-fcv-ace table-striped table-bordred table-hover dataTable no-footer"
                                   style="margin: 3em 0 1.5em;">
                                   <display:setProperty name="paging.banner.pageNumberParam" value="page"/>
                                <display:column title = "<fieldset class = 'form-group'>
                                <input type = 'checkbox' id = 'checkALl' class = 'check-box-element'>
                                </fieldset>" class = "center select-cell" headerClass="center select-cell">
                                   <fieldset>
                                    <input type = "checkbox" name = "checkList" value = "${tableList.id}"
                                    id = "checkbox_${tableList.id}" class = "check-box-element"/>
</fieldset>
</display:column>


                                        <!-- Tên tòa nhà -->
                                        <display:column headerClass="text-left" property="name" title="Tên tòa nhà" />

                                        <!-- Địa chỉ -->
                                        <display:column headerClass="text-left" property="address" title="địa chỉ"/>

                                        <!-- Số tầng hầm -->
                                        <display:column  headerClass="text-left" property="numberOfBasement" title="Số tầng hầm" />

                                        <display:column  headerClass="text-left" property="managerName" title="Tên quản lí" />
                                         <display:column  headerClass="text-left" property="managerPhone" title="SĐT quản lí" />
                                        <!-- Diện tích sàn -->
                                        <display:column  headerClass="text-left" property="floorArea" title="D.tích sàn" />
                                         <display:column  headerClass="text-left" property="emptyArea" title="D,tích trống" />

                                        <!-- Giá thuê -->
                                        <display:column  headerClass="text-left" property="rentArea" title="D.tích thuê" />

                                        <!-- Phí môi giới -->
                                        <display:column  headerClass="text-left" property="brokerageFee" title="Phí môi giới" />

                                        <!-- Thao tác -->
                                        <display:column headerClass="col-action" title="Thao tác" escapeXml="false">
                                            <button class="btn btn-xs btn-success" title="Giao tòa nhà" onclick="assignmentBuilding(${tableList.id})">
                                                <i class="ace-icon fa fa-list"></i>
                                            </button>

                                            <a class="btn btn-xs btn-info"
                                               href="/admin/building-edit-${tableList.id}"
                                               title="Sửa tòa nhà">
                                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                                            </a>

                                            <button type="button"
                                                    class="btn btn-xs btn-danger"
                                                    title="Xóa tòa nhà"
                                                    onclick="deleteBuilding(${tableList.id})">
                                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                            </button>
                                        </display:column>

                                    </display:table>
                        </form:form>

                    </tbody>
                </table>
            </div><!-- /.span -->
        </div>
    </div>
    <!-- /.page-content -->
</div>


<div class="modal" id="assignmentBuildingModal" role="dialog" style="font-family: 'Times New Roman', Times, serif;">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Danh sách nhân viên</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
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
                <input type="hidden" id="buildingId" name="buildingId" value="">
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" id="btnassignmentBuilding">Giao tòa nhà</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
            </div>

        </div>
    </div>
</div>
<script>
    function assignmentBuilding(buildingId) {
        $("#assignmentBuildingModal").modal("show");
        loadStaffs(buildingId);
        $('#buildingId').val(buildingId);


    }

    function loadStaffs(buildingId) {
         $.ajax({
            type: "GET",
            url: "${buildingAPI}/" + buildingId + '/staffs',
            dataType: "JSON",
            success: function (response) {
                var row = '';
                $.each(response.data, function (index, item) {
                    row += '<tr>';
                    row += '<td class="text-center"><input type="checkbox" value="' + item.staffId + '" id="checkbox_' + item.staffId + '" class="check-box-element" ' + item.checked + '/></td>';                    row += '<td class="text-center">' + item.fullName + '</td>';
                    row += '</tr>';


                });
                $('#staffList tbody').html(row);
                console.log("Success");
            },
            error: function (response) {
                console.log("Fail");
                 window.location.href = "<c:url value = "/admin/building-list?message=error" />";
                console.log(response);
            }
        })
}

    $('#btnassignmentBuilding').click(function (e) {
        e.preventDefault();
        var data = {};
        data['buildingId'] = $('#buildingId').val();
        var staffs = $('#staffList').find('tbody input[type="checkbox"]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffs'] = staffs;
        if(data['staffs'] != ''){
            assignmentBuildingForStaff(data);
        }
        console.log("ok");
    })

    function assignmentBuildingForStaff(data) {
        $.ajax({
            type: "POST",
            url: "${buildingAPI}/" + 'assignment',
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "JSON",
            success: function (respond) {
                console.log("Success");
            },
            error: function (respond) {
                console.info("Giao không thành công")
                window.location.href = "<c:url value = "/admin/building-list?message=error" />";
                console.log(respond);
            }
        })
}

    $('#btnSearchBuilding').click(function (e) {
        e.preventDefault();
        $('#listForm').submit();
    });

     function deleteBuilding(id) {
        var buildingId = [id];
        deleteBuildings(buildingId);
    }

    $('#btnDeleteBuilding').click(function (e) {
        e.preventDefault();
        var buildingIds = $('#tableList').find('tbody input[type="checkbox"]:checked').map(function () {
            return $(this).val();
        }).get();
        deleteBuildings(buildingIds);

    })

    function deleteBuildings(data){
         $.ajax({
            type: "DELETE",
            url: "${buildingAPI}/" + data,
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "JSON",
            success: function (response) {
                console.log("Success");
            },
            error: function (response) {
                console.log("Fail");
                console.log(response);
            }
        })
    }


</script>
</body>
</html>
