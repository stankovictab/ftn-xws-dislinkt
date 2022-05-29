# TODO: znaci automatsko kreiranje agenata, komentara i admina
## registracija korisnika (agenta)

## Agent Registration
```
localhost:5003/agent/register
```
Payload: AgentDTO object
```JSON
{
    "username": "agent1",
    "passwordInput": "test1",
    "email": "test@test.test",
    "number": "123",
    "firstName": "Test",
    "lastName": "Prezime"
}
```
need to register at least three agents, one needs to become firm owner, one needs to make comments, one needs to be the admin
```JSON
{
    "username": "agent2",
    "passwordInput": "test1",
    "email": "test@test.test",
    "number": "123",
    "firstName": "Test",
    "lastName": "Prezime"
}
```
```JSON
{
    "username": "agent3",
    "passwordInput": "test1",
    "email": "test@test.test",
    "number": "123",
    "firstName": "Test",
    "lastName": "Prezime"
}
```
## Firm Registration
```
localhost:5003/firm/register
```
Payload: FirmDTO object
```JSON
{
    "ownerId": "id of the owner, get after you make the first agent",
    "email": "firm@firm.firm",
    "number": "123",
    "name": "Firma",
    "description": "Opis",
    "culture": "hr"
}
```
## Admin Approval
### Making agent3 an admin
```
localhost:5003/agent/setAdmin
```
### Approving the firm
```
localhost:5003/firm/approve
```
Payload: Map<String, String> ids
```JSON
{
    "firmId": "id of the firm",
    "adminId": "id of the admin that approves the firm"
}
```
## Owner can update firm data
```
localhost:5003/firm/update
```
Payload: FirmDTO object
```JSON
{
  "id": "id of the firm",
  "ownerId": "id of the owner",
  "email": "firm@firm.firm",
  "number": "123",
  "name": "Firma3",
  "description": "Opis",
  "culture": "hr"
}
```
## Regular user can create a comment about the firm
```
localhost:5003/comment/create
```
Payload: CommentDTO object
```JSON
{
    "firmId": "id of the firm",
    "userId": "id of the user that makes the comment",
    "comment": "comment about the firm",
    "rating": "8.3",
    "salary": "4.300E"
}
```
## Owner can create positions for the firm
```
localhost:5003/offer/create
```
Payload: OfferDTO object
```JSON
{
    "firmId": "id of the firm",
    "jobTittle": "L",
    "jobDescription": "has to use eclipse",
    "jobLocation": "Novi Sad",
    "jobSeniority": "Medior",
    "jobField": "Backend",
    "jobTechnologies": [
        "python",
        "java"
    ],
    "jobResponsibilities": [
        "fix and review code",
        "write your own code"
    ],
    "jobRequirements": [
        "you just have to use eclipse",
        "there is no way around that",
        "you have to use it",
        "eclipse"
    ],
    "jobBonuses": [
        "have eclipse ide already installed"
    ],
    "dislinktShare": false
}
```
## User generating API Token
```
localhost:5001/user/generateApiToken
```
Payload: UserId 
```plain-text
userId
```
## Agent setting the User generated API Token
```
localhost:5003/agent/setApiToken
```
Payload: json object
```JSON
{
    "agentId": "id of the agent",
    "apiToken": "user generated api token"
}
```