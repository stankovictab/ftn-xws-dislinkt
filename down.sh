printf '\n'
echo "Shutting the app down, please wait ..."
printf '\n'

# -f is for the specific file name
# Taking containers down needs to be in the reverse order
# Instead of down we'll be using kill (which doesn't remove them) and rm -f (force)
docker-compose --file docker-compose-2.yml kill
docker-compose --file docker-compose-1.yml kill

docker-compose --file docker-compose-2.yml rm -f
docker-compose --file docker-compose-1.yml rm -f

printf '\n'
echo "Finished!"
printf '\n'