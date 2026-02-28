Vulnerability: Plain text password storage
Risk: Credential theft if DB leaks
Fix: BCrypt hashing with salt
Principle: Never store raw secrets