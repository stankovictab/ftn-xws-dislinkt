cd eureka-server
printf '\n'
echo "-------------------------------------------------------------------------------"
echo "------------------------INSTALLING EUREKA SERVER-------------------------------"
echo "-------------------------------------------------------------------------------"
printf '\n'
mvn clean install -DskipTests

cd ..
cd user
printf '\n'
echo "-------------------------------------------------------------------------------"
echo "----------------------------INSTALLING USER------------------------------------"
echo "-------------------------------------------------------------------------------"
printf '\n'
mvn clean install -DskipTests

cd ..
cd post
printf '\n'
echo "-------------------------------------------------------------------------------"
echo "----------------------------INSTALLING POST------------------------------------"
echo "-------------------------------------------------------------------------------"
printf '\n'
mvn clean install -DskipTests

echo "Done!"