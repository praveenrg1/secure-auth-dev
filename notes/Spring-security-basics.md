Spring Security auto-protects endpoints when dependency is added.
401 occurs because endpoints require authentication.
Solution: define SecurityFilterChain and configure access rules.