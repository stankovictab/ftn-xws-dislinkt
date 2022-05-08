# ftn-xws-dislinkt

> `TODO:` Images, setup, docker-compose launch via script?\
> `TODO:` mvn clean install -DskipTests script or in readme\
> `TODO:` localhost:8080, localhost:8761, localhost:8081, localhost:5001\
> `TODO:` dislinkt db in mongo express needs to be created manually??\
> `TODO:` inserting posts requiers 3 user ids which need to be obtained after inserting the users\
> `TODO:` find fix for permission denied on script launch (marko & mateja)
> `TODO:` localhost:5001/user/update sets everything else that isn't updated to null
---
---
---

# Inserting data into the database
Inserting users: generates 100 users with the username: user{#number} and password: password{#number}.
Every third user is a private user.

```
http post localhost:5001/user/generateUsers
```
Inserting posts: generates 99 posts, 66 for 2 public users and 33 for the private user.

```
http post localhost:5002/post/generatePosts
```

# 1.1 Unregisterd user can search public profiles
Able to show on frontend via search.
```
http post localhost:5001/user/find/ firstName="first name or username here"
``` 
Payload: 
```json
{
	"firstName": "first name or username here",
	"lastName": "last name here, can be empty string"
}
```

# 1.2 Unregisterd user has access to posts on public profiles
Get the user id from the database.
```
http post localhost:5002/post/getAll userId="userId here"
```
Payload: id of the user whose posts are to be seen (plaintext)
```plaintext
userId
```

# 1.3 Unregisterd user can register (unique username)
Frontend.
```
http post localhost:5001/user/register
``` 
Payload: UserDTO object
```json
{
	"username": "",
	"passwordInput": "",
	...
}
```

# 2.2 Loging in
Frontend. Pick a username from the database. Password for, for example user0, is password0.
```
http post localhost:5001/user/login
```
Payload: UserDTO object
```json
{
    "username": "",
    "passwordInput": "",
}
```

# 2.2 Following a user

## 1.1. Following of a public profile is always possible
```
http post localhost:5001/user/follow
```
Payload: 
```json
{
	"userId": "user id that wants to follow",
	"toFollowUserId": "id of the user that is to be followed"
}
```

## 1.2. Following of a private profile needs to be apporved by the private user
```
http post localhost:5001/user/follow
```
Payload: 
```json
{
    "userId": "user id that wants to follow",
    "toFollowUserId": "id of the user that is to be followed"
}
```

## 2. Approving a follow request
```
http post localhost:5001/user/approveFollow
```
Payload: 
```json
{
	"userId": "user id that is approving the follow request",
	"followerUserId": "id of the user that has requested to follow"
}
```

## 3. Feed which shows a user posts from all users he follows
Do from ThunderClient, userId in plain text.
```
http post localhost:5002/post/getFeed
```
Payload: id of the user who is logged in (plaintext)
```plaintext
userId
```

## 4. User can see all previous posts of the user he follows
```
http post localhost:5002/post/getAll
```
Payload: id of the user whos posts are to be seen (plaintext)
```plaintext
userId
```

# 2.6 Publishing posts
```
http post localhost:5002/post/create
```
Payload: postDTO object, userId must exist
```json
{
	"title": "",
	"description": "",
	"imageTitle": "",
	"userId": ""
}
```

# 2.9 Updating ones own profile
```
http post localhost:5001/user/update
```
Payload: UserDTO object, username is mandatory
```json
{
    "username": "",
    // ...
}
```
## Updating own username
```
http post localhost:5001/user/updateUsername
```
Payload: 
```json
{
	"oldUsername": "old username",
	"newUsername": "new username, must be unique"
}
```

## Running the app

Install Docker.

```bash
sudo apt install ca-certificates curl gnupg lsb-release

curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg

echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

sudo apt update

sudo apt install docker-ce docker-ce-cli containerd.io

```

Install Docker Compose.
```bash
sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

sudo chmod +x /usr/local/bin/docker-compose

docker-compose --version
```

Install Maven.
```bash
sudo apt install maven
```

To run the app, run the following command.
```bash
./downup.sh
```

### Running the frontend app

TODO: Install `npm`

`cd` into `frontend` and install all dependencies.
```
npm install
```
Run the app.
```
npm run serve
```

## `Permission Denied` Fix
When running the script you might get the `Permission Denied` error.\
This is the fix.

```
sudo groupadd docker
sudo usermod -aG docker $USER
sudo newgrp docker 
```
Exit from root user shell and `reboot`.