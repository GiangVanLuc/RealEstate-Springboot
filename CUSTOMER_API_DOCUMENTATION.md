# Customer Management API Documentation

## Overview
This document describes the Customer Management REST API endpoints for the Real Estate Management System.

## Base URL
```
/api/customers
```

## Endpoints

### 1. Create Customer
**POST** `/api/customers`

Creates a new customer in the system.

**Request Body:**
```json
{
  "fullName": "Nguyen Van A",
  "phoneNumber": "0905123456",
  "email": "nguyenvana@example.com",
  "address": "123 Nguyen Hue, Q1, TP.HCM",
  "customerType": "POTENTIAL",
  "notes": "Khách hàng tiềm năng, quan tâm đến căn hộ cao cấp",
  "companyName": "ABC Corporation",
  "demand": "Cần thuê văn phòng"
}
```

**Validation Rules:**
- `fullName`: Required, 2-100 characters
- `phoneNumber`: Required, Vietnamese phone format (10-11 digits starting with 0)
- `email`: Optional, must be valid email format
- `customerType`: POTENTIAL, NEGOTIATING, or PURCHASED

**Response:** `201 Created`
```json
{
  "id": 1,
  "fullName": "Nguyen Van A",
  "phoneNumber": "0905123456",
  "email": "nguyenvana@example.com",
  "address": "123 Nguyen Hue, Q1, TP.HCM",
  "customerType": "POTENTIAL",
  "notes": "Khách hàng tiềm năng, quan tâm đến căn hộ cao cấp",
  "companyName": "ABC Corporation",
  "demand": "Cần thuê văn phòng",
  "isActive": true,
  "createdDate": "2025-11-08T08:10:00",
  "createdBy": "admin",
  "modifiedDate": null,
  "modifiedBy": null
}
```

### 2. Update Customer
**PUT** `/api/customers/{id}`

Updates an existing customer.

**Path Parameters:**
- `id` (Long): Customer ID

**Request Body:** Same as Create Customer

**Response:** `200 OK`
```json
{
  "id": 1,
  "fullName": "Nguyen Van A Updated",
  ...
}
```

### 3. Delete Customer (Soft Delete)
**DELETE** `/api/customers/{id}`

Soft deletes a customer (sets isActive to false).

**Path Parameters:**
- `id` (Long): Customer ID

**Response:** `200 OK`
```json
{
  "message": "Xóa khách hàng thành công"
}
```

### 4. Delete Multiple Customers
**DELETE** `/api/customers`

Soft deletes multiple customers.

**Request Body:**
```json
[1, 2, 3]
```

**Response:** `200 OK`
```json
{
  "message": "Xóa khách hàng thành công"
}
```

### 5. Get Customer by ID
**GET** `/api/customers/{id}`

Retrieves a single customer by ID.

**Path Parameters:**
- `id` (Long): Customer ID

**Response:** `200 OK`
```json
{
  "id": 1,
  "fullName": "Nguyen Van A",
  ...
}
```

### 6. Get All Customers (with Pagination)
**GET** `/api/customers`

Retrieves all active customers with pagination.

**Query Parameters:**
- `page` (Integer, optional): Page number (default: 0)
- `size` (Integer, optional): Page size (default: 10)
- `searchValue` (String, optional): Search by name, phone, or email

**Example:**
```
GET /api/customers?page=0&size=10
GET /api/customers?searchValue=nguyen
```

**Response:** `200 OK`
```json
{
  "content": [
    {
      "id": 1,
      "fullName": "Nguyen Van A",
      ...
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10
  },
  "totalElements": 25,
  "totalPages": 3,
  "last": false,
  "first": true
}
```

### 7. Search Customers (Advanced)
**POST** `/api/customers/search`

Advanced search with multiple criteria.

**Request Body:**
```json
{
  "fullName": "Nguyen",
  "phoneNumber": "0905",
  "email": "example.com",
  "customerType": "POTENTIAL",
  "searchValue": "nguyen",
  "page": 0,
  "size": 10
}
```

**Response:** `200 OK` (Same format as Get All Customers)

### 8. Get Statistics by Customer Type
**GET** `/api/customers/statistics`

Retrieves customer statistics grouped by customer type.

**Response:** `200 OK`
```json
{
  "POTENTIAL": 15,
  "NEGOTIATING": 8,
  "PURCHASED": 12
}
```

## Error Responses

### 400 Bad Request (Validation Error)
```json
{
  "fullName": "Họ tên không được để trống",
  "phoneNumber": "Số điện thoại phải có định dạng Việt Nam (10-11 số, bắt đầu bằng 0)"
}
```

### 404 Not Found
```json
{
  "error": "Customer Not Found",
  "message": "Không tìm thấy khách hàng với ID: 999"
}
```

### 409 Conflict (Duplicate)
```json
{
  "error": "Duplicate Customer",
  "message": "Email đã tồn tại trong hệ thống: nguyenvana@example.com"
}
```

### 500 Internal Server Error
```json
{
  "error": "Internal Server Error",
  "message": "An unexpected error occurred"
}
```

## Customer Type Enum Values
- `POTENTIAL`: Khách hàng tiềm năng
- `NEGOTIATING`: Đang giao dịch
- `PURCHASED`: Đã mua

## Testing with cURL

### Create Customer
```bash
curl -X POST http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Nguyen Van A",
    "phoneNumber": "0905123456",
    "email": "nguyenvana@example.com",
    "customerType": "POTENTIAL"
  }'
```

### Get All Customers
```bash
curl -X GET "http://localhost:8080/api/customers?page=0&size=10"
```

### Search Customers
```bash
curl -X GET "http://localhost:8080/api/customers?searchValue=nguyen"
```

### Get Statistics
```bash
curl -X GET http://localhost:8080/api/customers/statistics
```

### Update Customer
```bash
curl -X PUT http://localhost:8080/api/customers/1 \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Nguyen Van A Updated",
    "phoneNumber": "0905123456",
    "email": "nguyenvana@example.com",
    "customerType": "NEGOTIATING"
  }'
```

### Delete Customer
```bash
curl -X DELETE http://localhost:8080/api/customers/1
```

## Notes
- All endpoints require appropriate authentication (configured via Spring Security)
- Dates are returned in ISO 8601 format
- The API uses soft delete (isActive flag) to maintain data integrity
- Duplicate email and phone number checks are performed automatically
- Search is case-insensitive for text fields
