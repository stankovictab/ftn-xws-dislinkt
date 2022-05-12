![Dislinkt - A LinkedIn Clone!](/docs/Dislinkt.png)

# ftn-xws-dislinkt

> `TODO:` Description and images.\
> `TODO:` `localhost:8080`, `localhost:8761`, `localhost:8081`, `localhost:5001`, explain each one.\
> `TODO:` `dislinkt` database in MongoDB needs to be created manually?\
> `TODO:` Inserting posts requiers 3 user ids which need to be obtained after inserting the users\
> `TODO:` `localhost:5001/user/update` sets everything else that isn't updated to `null`.\
> `TODO:` Test the whole setup from scratch in a VM, for a better setup tutorial.\
> `TODO:` Install `npm` into tutorial

---

## Setup

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

When running the script you might get a `Permission Denied` error.\
This set of commands fixes that issue.

```bash
sudo groupadd docker
sudo usermod -aG docker $USER
sudo newgrp docker 
```
Exit from root user shell and `reboot`.

Install Maven.
```bash
sudo apt install maven
```

To startup the backend of the app, run the following command.
```bash
./downup.sh
```

### Running the App

Head into the `frontend` folder, install all dependencies and run the app, \
which will be running on port `8080`.
```
npm install
```
Run the app.
```
npm run serve
```
