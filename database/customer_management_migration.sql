-- Migration script to update customer table for Customer Management feature
-- This script adds new columns to the existing customer table

USE `estateadvance`;

-- Add new columns to customer table
ALTER TABLE `customer` 
ADD COLUMN IF NOT EXISTS `address` varchar(255) DEFAULT NULL AFTER `email`,
ADD COLUMN IF NOT EXISTS `customertype` varchar(50) DEFAULT NULL AFTER `address`,
ADD COLUMN IF NOT EXISTS `notes` TEXT DEFAULT NULL AFTER `customertype`;

-- Update existing records to set default customer type
UPDATE `customer` SET `customertype` = 'POTENTIAL' WHERE `customertype` IS NULL;

-- Ensure is_active column exists (it was already in the schema but let's make sure)
ALTER TABLE `customer` 
MODIFY COLUMN `is_active` TINYINT(1) DEFAULT 1;
