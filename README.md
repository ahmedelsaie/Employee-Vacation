
Running the app
Docker
$ docker-compose up

mysql port 3309
app   port 8080


open swagger on http://localhost:8080/swagger-ui/index.html
System is copomsed of two api groups
* one for admin role employees
* another group for non-admin employees


***in order to use non-admin employees
* register user using http://localhost:8080/auth/signup
* login with the registered user on http://localhost:8080/auth/login
* use authorize in swagger with the bearer token returned
* then you can use any of the given apis
  ** get employees-platform/employees/me
  ** put employees-platform/employees/me
  ** post employees-platform/employees/me/create-vacation
  
  
***in order to use admin employees
* there is a predefined admin user
  ** email admin@gmail.com
  ** password admin
* login with the registered user on http://localhost:8080/auth/login
* use authorize in swagger with the bearer token returned
* then you can use any of the given apis
  ** get admin-platform/employees/me
  ** put admin-platform/employees/me
  ** post admin-platform/employees/me/create-vacation
  
