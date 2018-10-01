## REST webservice with Gradle
A simple example

### Build
```bash
$ ./gradlew build
```

### Run
```bash
$ java -jar build/libs/rest-example-1.0-SNAPSHOT-all.jar Alice localhost:8080
$ java -jar build/libs/rest-example-1.0-SNAPSHOT-all.jar Bob localhost:8081

```

### Transaction APIs
* Send Payment
    - Method: `POST`
    - URL: `localhost:8080/api/v1/users/1`
    - ```curl -X POST  -H "Content-Type: application/json" http://local1/trustline -d '{"user":{"userName":"bob", "endPoint":"localhost:8081"}, "amount": 10}'```
    - ```{"balance":-40}```


### User APIs
* Get all Users
    - Method: `GET`
    - URL: `localhost:8080/api/v1/users/1`
    - Response body data type: Json
    - ```{"userId":1,"userName":"a"}```
* Add new User
    - Method: `POST`
    - ```curl -X POST  -H "Content-Type: application/json" http://localhost:8080/api/v1/users -d '{"userName":"a3"}'```
    - Success code: 201 Created
* Get a User
    - Method: `GET`
    - URL: `localhost:8080/api/v1/users/{userId}`
    - Response body data type: Json
    - ```[{"userId":1,"userName":"a"},{"userId":2,"userName":"b"}]```
* Update a User
    - Method: `PUT`
    - ```curl -X PUT  -H "Content-Type: application/json" http://localhost:8080/api/v1/users/1 -d '{"userName":"a3"}'```
    - Response body data type: Json
    - "userId":1,"userName":"a"}
* Delete a User
    - Method: `DELETE`
    - ```curl -X DELETE  -H "Content-Type: application/json" http://localhost:8080/api/v1/users/1```
    - Success code: 204 No Content


### Example Run Alice
```
[main] INFO org.eclipse.jetty.server.Server - Started @1211ms
[main] INFO com.myorg.user.server.TrustLineServer - [Trustline] Balance: 0
[main] INFO com.myorg.user.server.TrustLineServer - Join -- server: org.eclipse.jetty.server.Server@45fe3ee3 user: User [userId=0, userName=Alice, endpoint= localhost:8080]
[qtp379110473-18] INFO com.myorg.user.service.DefaultTrustlineService - [Trustline] Transaction Transaction [ user: User [userId=0, userName=bob, endpoint= localhost:8081] amount: 10 ] Sent!!
[qtp379110473-18] INFO com.myorg.user.service.DefaultTrustlineService - [Trustline] Balance: Balance [ -10 ]
[qtp379110473-13] INFO com.myorg.user.service.DefaultTrustlineService - [Trustline] Transaction Transaction [ user: User [userId=0, userName=bob, endpoint= localhost:8081] amount: 20 ] Sent!!
[qtp379110473-13] INFO com.myorg.user.service.DefaultTrustlineService - [Trustline] Balance: Balance [ -30 ]
[qtp379110473-12] INFO com.myorg.user.service.DefaultTrustlineService - [Trustline] Transaction Transaction [ user: User [userId=0, userName=bob, endpoint= localhost:8081] amount: 10 ] Sent!!
[qtp379110473-12] INFO com.myorg.user.service.DefaultTrustlineService - [Trustline] Balance: Balance [ -40 ]
````

### Example Run Bob
```
[main] INFO org.eclipse.jetty.server.Server - Started @1201ms
[main] INFO com.myorg.user.server.TrustLineServer - [Trustline] Balance: 0
[main] INFO com.myorg.user.server.TrustLineServer - Join -- server: org.eclipse.jetty.server.Server@45fe3ee3 user: User [userId=0, userName=Bob, endpoint= localhost:8081]
[qtp379110473-11] INFO com.myorg.user.service.DefaultTrustlineService - [Trustline] Transaction: Transaction [ user: User [userId=0, userName=Alice, endpoint= localhost:8080] amount: -10 ] Received!!
[qtp379110473-11] INFO com.myorg.user.service.DefaultTrustlineService - [Trustline] Balance: Balance [ 10 ]
[qtp379110473-14] INFO com.myorg.user.service.DefaultTrustlineService - [Trustline] Transaction: Transaction [ user: User [userId=0, userName=Alice, endpoint= localhost:8080] amount: -20 ] Received!!
[qtp379110473-14] INFO com.myorg.user.service.DefaultTrustlineService - [Trustline] Balance: Balance [ 30 ]
[qtp379110473-17] INFO com.myorg.user.service.DefaultTrustlineService - [Trustline] Transaction: Transaction [ user: User [userId=0, userName=Alice, endpoint= localhost:8080] amount: -10 ] Received!!
[qtp379110473-17] INFO com.myorg.user.service.DefaultTrustlineService - [Trustline] Balance: Balance [ 40 ]
````