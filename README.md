# Getting Started
[Register](http://localhost:9090/realms/socialmedia/protocol/openid-connect/auth?response_type=code&client_id=socialmedia-core-api&scope=openid&redirect_uri=http://localhost:8080/swagger-ui/index.html), 
open [Swagger](http://localhost:8080/swagger-ui/index.html),
select Authentication server, generate the [token](http://localhost:8080/swagger-ui/index.html#/Authentication/getToken)
filling as following:
* grant_type=password,
* client_id=socialmedia-core-api,
* username=<your_user>,
* password=<your_password>

Copy access_token and click ("Authorize"), paste access_token
Switch the server back to ("Social media core api server") and enjoy the API.

# Useful Links

* [Login & Register](http://localhost:9090/realms/socialmedia/protocol/openid-connect/auth?response_type=code&client_id=socialmedia-core-api&scope=openid&redirect_uri=http://localhost:8080/swagger-ui/index.html)
* [Logout](http://localhost:9090/realms/socialmedia/protocol/openid-connect/logout)


* [Swagger](http://localhost:8080/swagger-ui/index.html)
* [Mongo Express](http://localhost:8081/)
* [Keycloak](http://localhost:9090/realms/master/protocol/openid-connect/auth?client_id=security-admin-console&redirect_uri=http%3A%2F%2Flocalhost%3A9090%2Fadmin%2Fmaster%2Fconsole%2F&state=f7ae2802-99bc-4548-9374-84478c428c29&response_mode=fragment&response_type=code&scope=openid&nonce=9c454464-bcd2-40b4-b746-49a95e03d60b&code_challenge=10koUSpGwgc3NQgRz8Zh7mGPcKKt_TvaJ1dGePTn85A&code_challenge_method=S256)


* [OpenID config](http://0.0.0.0:9090/realms/socialmedia/.well-known/openid-configuration)
