Vulnerability: Weak JWT secret
Impact: Token forgery
Cause: Predictable signing key
Fix: Strong random secret + rotation

Vulnerability Type: Outdated Dependency
Risk: Runtime failure + security flaws
Fix: Use maintained versions
Principle: Always check library compatibility

Request - security filter - jwt filter - controller

generate - validate - parse - extract - verify expiry

1. Scan @Configuration classes
2. Create beans defined inside
3. Scan @Component classes
4. Inject dependencies
5. Build filter chain