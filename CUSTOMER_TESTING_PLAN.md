# Customer Management Feature - Testing Plan

## Prerequisites
1. MySQL database running with `estateadvance` schema
2. Run migration script: `database/customer_management_migration.sql`
3. Application running on `http://localhost:8080`

## Test Scenarios

### 1. Test Create Customer (POST /api/customers)

**Valid Request:**
```bash
curl -X POST http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Nguyen Van Test",
    "phoneNumber": "0905123456",
    "email": "test@example.com",
    "address": "123 Test Street, Q1, HCM",
    "customerType": "POTENTIAL",
    "notes": "Test customer",
    "companyName": "Test Company"
  }'
```

**Expected Result:** 201 Created with customer details

**Invalid Request - Missing Required Field:**
```bash
curl -X POST http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -d '{
    "phoneNumber": "0905123456"
  }'
```

**Expected Result:** 400 Bad Request with validation errors

**Invalid Request - Invalid Phone Format:**
```bash
curl -X POST http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Test",
    "phoneNumber": "123"
  }'
```

**Expected Result:** 400 Bad Request with phone format error

**Invalid Request - Duplicate Email:**
```bash
# First create a customer, then try to create another with same email
curl -X POST http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Test Duplicate",
    "phoneNumber": "0905999999",
    "email": "test@example.com"
  }'
```

**Expected Result:** 409 Conflict - Email already exists

### 2. Test Get All Customers (GET /api/customers)

**Basic Request:**
```bash
curl -X GET "http://localhost:8080/api/customers"
```

**Expected Result:** 200 OK with paginated list (page 0, size 10)

**With Pagination:**
```bash
curl -X GET "http://localhost:8080/api/customers?page=0&size=5"
```

**Expected Result:** 200 OK with 5 customers per page

**With Search:**
```bash
curl -X GET "http://localhost:8080/api/customers?searchValue=nguyen"
```

**Expected Result:** 200 OK with customers matching "nguyen" in name, phone, or email

### 3. Test Get Customer by ID (GET /api/customers/{id})

**Valid Request:**
```bash
curl -X GET "http://localhost:8080/api/customers/1"
```

**Expected Result:** 200 OK with customer details

**Invalid Request - Non-existent ID:**
```bash
curl -X GET "http://localhost:8080/api/customers/99999"
```

**Expected Result:** 404 Not Found

### 4. Test Update Customer (PUT /api/customers/{id})

**Valid Request:**
```bash
curl -X PUT http://localhost:8080/api/customers/1 \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Nguyen Van Updated",
    "phoneNumber": "0905123456",
    "email": "updated@example.com",
    "customerType": "NEGOTIATING"
  }'
```

**Expected Result:** 200 OK with updated customer details

**Invalid Request - Non-existent ID:**
```bash
curl -X PUT http://localhost:8080/api/customers/99999 \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Test",
    "phoneNumber": "0905123456"
  }'
```

**Expected Result:** 404 Not Found

### 5. Test Advanced Search (POST /api/customers/search)

**Search by Multiple Criteria:**
```bash
curl -X POST http://localhost:8080/api/customers/search \
  -H "Content-Type: application/json" \
  -d '{
    "customerType": "POTENTIAL",
    "page": 0,
    "size": 10
  }'
```

**Expected Result:** 200 OK with customers of type POTENTIAL

**Search with Text:**
```bash
curl -X POST http://localhost:8080/api/customers/search \
  -H "Content-Type: application/json" \
  -d '{
    "searchValue": "test"
  }'
```

**Expected Result:** 200 OK with matching customers

### 6. Test Get Statistics (GET /api/customers/statistics)

**Request:**
```bash
curl -X GET "http://localhost:8080/api/customers/statistics"
```

**Expected Result:** 200 OK with counts by customer type
```json
{
  "POTENTIAL": 10,
  "NEGOTIATING": 5,
  "PURCHASED": 8
}
```

### 7. Test Delete Customer (DELETE /api/customers/{id})

**Valid Request:**
```bash
curl -X DELETE "http://localhost:8080/api/customers/1"
```

**Expected Result:** 200 OK with success message
Note: This is soft delete - customer still exists but isActive=false

**Verify Deletion:**
```bash
curl -X GET "http://localhost:8080/api/customers/1"
```

**Expected Result:** 404 Not Found (if properly soft deleted)

**Invalid Request - Non-existent ID:**
```bash
curl -X DELETE "http://localhost:8080/api/customers/99999"
```

**Expected Result:** 404 Not Found

### 8. Test Delete Multiple Customers (DELETE /api/customers)

**Valid Request:**
```bash
curl -X DELETE http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -d '[1, 2, 3]'
```

**Expected Result:** 200 OK with success message

## Test Data Setup

Run these commands to create test data:

```bash
# Create customer with POTENTIAL type
curl -X POST http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Tran Thi A",
    "phoneNumber": "0901234567",
    "email": "tranthia@test.com",
    "customerType": "POTENTIAL"
  }'

# Create customer with NEGOTIATING type
curl -X POST http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Le Van B",
    "phoneNumber": "0912345678",
    "email": "levanb@test.com",
    "customerType": "NEGOTIATING"
  }'

# Create customer with PURCHASED type
curl -X POST http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Pham Thi C",
    "phoneNumber": "0923456789",
    "email": "phamthic@test.com",
    "customerType": "PURCHASED"
  }'
```

## Validation Test Cases

### 1. Full Name Validation
- Empty: ❌ Should fail
- 1 character: ❌ Should fail (min 2)
- 2 characters: ✅ Should pass
- 100 characters: ✅ Should pass
- 101 characters: ❌ Should fail (max 100)

### 2. Phone Number Validation
- "123": ❌ Invalid format
- "0905123456": ✅ Valid (10 digits)
- "09051234567": ✅ Valid (11 digits)
- "905123456": ❌ Invalid (must start with 0)
- "090512345678": ❌ Invalid (too long)

### 3. Email Validation
- "test@example.com": ✅ Valid
- "test": ❌ Invalid
- "@example.com": ❌ Invalid
- "test@": ❌ Invalid

### 4. Customer Type Validation
- "POTENTIAL": ✅ Valid
- "NEGOTIATING": ✅ Valid
- "PURCHASED": ✅ Valid
- "INVALID": ⚠️ Falls back to POTENTIAL
- null/empty: ⚠️ Falls back to POTENTIAL

## Expected Response Formats

### Success Response (Customer Object)
```json
{
  "id": 1,
  "fullName": "Nguyen Van A",
  "phoneNumber": "0905123456",
  "email": "test@example.com",
  "address": "123 Test St",
  "customerType": "POTENTIAL",
  "notes": "Some notes",
  "companyName": "ABC Corp",
  "demand": "Need office",
  "status": null,
  "isActive": true,
  "createdDate": "2025-11-08T08:10:00",
  "createdBy": "admin",
  "modifiedDate": null,
  "modifiedBy": null
}
```

### Error Response (Validation)
```json
{
  "fullName": "Họ tên không được để trống",
  "phoneNumber": "Số điện thoại phải có định dạng Việt Nam (10-11 số, bắt đầu bằng 0)"
}
```

### Error Response (Not Found)
```json
{
  "error": "Customer Not Found",
  "message": "Không tìm thấy khách hàng với ID: 999"
}
```

### Error Response (Duplicate)
```json
{
  "error": "Duplicate Customer",
  "message": "Email đã tồn tại trong hệ thống: test@example.com"
}
```

## Notes
- All timestamps are in ISO 8601 format
- Pagination uses zero-based page numbers
- Soft delete preserves data (isActive flag)
- Search is case-insensitive
- Statistics include only active customers
