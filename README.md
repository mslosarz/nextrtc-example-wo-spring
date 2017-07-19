# nextrtc-example-wo-spring
Example of use NextRTC in standalone mode

Clone project and then
- enter to directory with project

enter to directory ```conf```
and generate self signed certificate (in repository certificate might be expired):
```bash
keytool -keystore keystore.jks -genkey -alias tomcat -keyalg RSA
```
When answering the questions asked by keytool, make sure to use the name (CN) "127.0.0.1":
```
Enter keystore password: password
Re-enter new password: password
What is your first and last name?
  [Unknown]:  127.0.0.1
What is the name of your organizational unit?
  [Unknown]:  Develepment
What is the name of your organization?
  [Unknown]:  NextRTC
What is the name of your City or Locality?
  [Unknown]:  Cracow
What is the name of your State or Province?
  [Unknown]:  Malopolskie
What is the two-letter country code for this unit?
  [Unknown]:  PL
Is CN=127.0.0.1, OU=Develepment, O=NextRTC, L=Cracow, ST=Malopolskie, C=PL corre
ct?
  [no]:  yes

Enter key password for <tomcat>
  (RETURN if same as keystore password): <RETURN>
```

 run 
```bash
mvn clean install && mvn cargo:run
```

_Sometimes websocket (js side) is throwing exception then try to change localhost to 127.0.0.1_
