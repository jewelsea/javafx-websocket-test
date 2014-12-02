Command to generate a self-signed keystore:

Password to use is "password".

This is necessary because the maven command jfx:generate-key-store doesn't seem to work with the JavaFX maven plugin 8.1.2:
  Refer to https://github.com/zonski/javafx-maven-plugin/issues/66 for more detail.

$ keytool -genkey -keyalg RSA -alias selfsigned -keystore keystore.jks -storepass password -validity 360 -keysize 2048
What is your first and last name?
  [Unknown]:  jewelsea
What is the name of your organizational unit?
  [Unknown]:  jewelsea.org
What is the name of your organization?
  [Unknown]:  jewelsea
What is the name of your City or Locality?
  [Unknown]:  Mountain View
What is the name of your State or Province?
  [Unknown]:  CA
What is the two-letter country code for this unit?
  [Unknown]:  US
Is CN=jewelsea, OU=jewelsea.org, O=jewelsea, L=Mountain View, ST=CA, C=US correct?
  [no]:  yes

Enter key password for <selfsigned>
	(RETURN if same as keystore password):
Re-enter new password:
$