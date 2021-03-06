# Missed Call Center App
This app has been developped for handling missed calls to send notification of related users using websocket.

## How to Run Project
1. Install Docker Desktop and switch to Windows container
2. Clone this repository in to your local machine
3. Open a command prompt in the root directory of the local project repository
4. Run command  ```./mvnw install dockerfile:build```
5. Run command  ```docker run -p 8085:8081 -t missedcallcenter/missedcallcenter```
6. The project will up and running on http://localhost:8085/

## Enpoints
* Websocket Client: http://localhost:8085/index.html
* Swagger UI: http://localhost:8085/swagger-ui/index.html
* H2 Console: http://localhost:8085/h2-console
* Other Rest API enpoints can be seen on Swagger UI page.

## Configuration
You can change app language with ```Turkish``` and ```English``` and also you can change the date format. For setting these variables, simply set the app.language and ```app.dateFormat``` properties that located in ```application.properties``` file. For example:

 ```
app.language=Turkish
app.dateFormat=dd.MM HH:mm
 ```

## Usage
A user can do 6 things with using this project.
* Creating a user (Explicitly) - http://localhost:8085/saveUser - by POST request
* Saving a phone number (Explicitly) - http://localhost:8085/savePhone - by POST request
* Calling a phone number (Explicitly) - http://localhost:8085/call - by POST request
* Sending delivery report (Explicitly) - http://localhost:8085/deliveryReport - by POST request
* Connecting websocket (Explicitly) - http://localhost:8085 - by Providing User Phone and Clicking Connect Button
* Getting notifications from websocket (Implicitly) - http://localhost:8085 


### Create a user
For creating a new user: Send POST request to http://localhost:8085/saveUser

Sample JSON request:
```JSON
{
    "name":"Mehmet",
    "password":"123456"
}
```

### Save phone
Saving a phone for a created user: Send POST request to http://localhost:8085/savePhone

Sample JSON request: (The id have to be same with id of respond of saveUser endpoint.)
```JSON
{
    "id": 1,
    "name": "Mehmet",
    "phone": "99999999999"
}
```

### Call phone
Calling a phone number: Send POST request to http://localhost:8085/call

Sample JSON request: (The id have to be same with id of respond of saveUser endpoint.)
```JSON
{
  "calledPhone": "88888888888",
  "callerUserDto": {
    "id": 1,
    "name": "Mehmet",
    "phone": "99999999999"
   }
}
```
Note: calledPhone => Aranan Numara, callerUserDto => Arayan User

### Send Delivery Report 
Calling a phone number: Send POST request to http://localhost:8085/deliveryReport

Sample JSON request: (The id have to be same with id of respond of saveUser endpoint.)
```JSON
{
    "id": 1,
    "name": "Mehmet",
    "phone": "99999999999"
}
```
## An Example Use Case
**1.User A Saves Itself To The System By Rest API and Saves Phone To The System**<br />
 * <kbd>![alt text](https://user-images.githubusercontent.com/23100256/100641262-49938a80-3348-11eb-9609-7c5cba632c85.png)</kbd>
 * <kbd>![alt text](https://user-images.githubusercontent.com/23100256/100641395-78116580-3348-11eb-94c1-5a1107320038.png)</kbd>
<br /><br />
**2. User A Creates Websocket Connection With Server**<br />
 * <kbd>![alt text](https://user-images.githubusercontent.com/23100256/100642073-5a90cb80-3349-11eb-8ea9-9fed9bcc43a7.png)</kbd>
 * <kbd>![alt text](https://user-images.githubusercontent.com/23100256/100642241-988def80-3349-11eb-9759-dc1b00880fc6.png)</kbd>
<br /><br />
**3. User A Calls User B three times(phone:88888888888, but has not logged in to system) With Rest API**<br />
<kbd>![alt text](https://user-images.githubusercontent.com/23100256/100642958-86608100-334a-11eb-87e8-809f2d15fee9.png)</kbd>
  (Note: I sent same request for 3 times)
<br /><br />
**4. User B Saves Itself To The System By Rest API and Saves Phone To The System and Able To Get Notification From Web Socket**<br />
  * <kbd>![alt text](https://user-images.githubusercontent.com/23100256/100644393-51edc480-334c-11eb-8afa-5408b395a916.png)</kbd>
  * <kbd>![alt text](https://user-images.githubusercontent.com/23100256/100644754-c45ea480-334c-11eb-88ce-7b23ac18c870.png)</kbd>
<br /><br />
**5. User B Gets Notification Of Missed Calls Over The Web Socket**<br />
<kbd>![alt text](https://user-images.githubusercontent.com/23100256/100644975-143d6b80-334d-11eb-8b8d-cd590114ad41.png)</kbd>
<br /><br />
**6. User B Sends Delivery Report To The Sytem**<br />
<kbd>![alt text](https://user-images.githubusercontent.com/23100256/100645392-a9406480-334d-11eb-9c1d-4b955a26320d.png)</kbd>
<br /><br />
**7. User A Gets Available Notification About the User B(since it sent delivery report in part 6)**<br />
<kbd>![alt text](https://user-images.githubusercontent.com/23100256/100645528-d9880300-334d-11eb-8be9-4e99ee62d977.png)</kbd>

### H2 Database
```
H2 Database Console Credentials
User name= sa
Password= password
```
**After the above 7 steps of the example use case, the H2 database will look like this:**
 * <kbd>![alt text](https://user-images.githubusercontent.com/23100256/100658382-02b18f00-3360-11eb-8055-349f0d39e3ce.png)</kbd>
 * <kbd>![alt text](https://user-images.githubusercontent.com/23100256/100660076-92583d00-3362-11eb-85cf-c866ef8dd638.png)</kbd>
 * <kbd>![alt text](https://user-images.githubusercontent.com/23100256/100660160-adc34800-3362-11eb-8508-4639cacc780e.png)</kbd>


