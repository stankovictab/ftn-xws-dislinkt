echo '{"username": "agent1", "passwordInput": "test1", "email": "test@test.test", "number": "1", "firstName": "Bice Owner", "lastName": "Prezime"}' | http post localhost:5003/agent/register

echo '{"username": "agent2", "passwordInput": "test1", "email": "test@test.test", "number": "1", "firstName": "Bice reg User", "lastName": "Prezime"}' | http post localhost:5003/agent/register

echo '{"username": "agent3", "passwordInput": "test1", "email": "test@test.test", "number": "1", "firstName": "Bice Admin", "lastName": "Prezime"}' | http post localhost:5003/agent/register

echo '{"username": "agent4", "passwordInput": "test1", "email": "test@test.test", "number": "1", "firstName": "Test", "lastName": "Prezime"}' | http post localhost:5003/agent/register

