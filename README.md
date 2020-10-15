# ATM (Automatated Teller Machine) API
ATM API is a backend service API for ATM which can used to perform different banking transactions. It uses H2 database with its default credentials as Username: "sa" and password = ""

This API can be used to perform following functionalities:
  1. Create accounts (Accept sufficient attributes required to create the user account)
  2. Cash deposits (Based on user account details users can deposit amounts from their bank account)
  3. Cash withdrawals (Based on user account details users can withdraw amounts from their bank account)
  4. Balance enquiry (Check Balance)

# Creating an Account
To create an account, the following URL and sample JSON object can be used. Make sure to follow certain rules while creating an account (Minimum balance: 1000, Pin should be 4 digit).

URL : https://localhost:8080/atm/createaccount    
JSON Object : {"accountNumber":"105", "accountName":"icici", "pin":"1234","balance":"1500"}

# Generating an OTP
To perform any of those functionalities (except Account Creation), the user will have to generate a one time password. This can be done by using following sample URL:

https://localhost:8080/atm/getotp and following sample JSON object: 
  { 
  "accountNumber":"105", 
  "accountName":"icici", 
  "pin":"1234" 
  }

# Performing other Functionalities
Once an OTP is generated (let it be 9815 for instance), the user can perform following functionalities using URLS mentioned besides them:
  1. Cash deposits : https://localhost:8080/atm/deposit/9815/105/200 in the form of - "/deposit/{otp}/{accountnumber}/{amount}"
  2. Cash withdrawals : https://localhost:8080/atm/withdraw/9815/105/200 in the form of - "/withdraw/{otp}/{accountnumber}/{amount}"
  3. Balance enquiry : https://localhost:8080/atm/checkbalance/9815/105 in the form of - "/checkbalance/{otp}/{accountnumber}"
