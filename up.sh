if [ "$1" = "--skipMaven" ] || [ "$2" = "--skipMaven" ]
then 
	printf '\n'

	BLUE="\e[32m"
	ENDCOLOR="\e[0m"
	echo -e "${BLUE}Skipping Maven installation.${ENDCOLOR}"
else 
	./maveninstall.sh
fi

printf '\n'
echo "Starting up the containers, please wait ..."
printf '\n'

# --file needs to be specified
# --detach is in order to run them both and not get stuck on the first
docker-compose --file docker-compose-1.yml up --build --detach

printf '\n'
echo "Waiting for 6 seconds, please wait ..."
printf '\n'

sleep 6s
docker-compose --file docker-compose-2.yml up --build --detach
# To take the containers down, either run ./down, or do it from VSCode

printf '\n'
echo "Finished!"
echo "Run ./down.sh to stop the application."
printf '\n'