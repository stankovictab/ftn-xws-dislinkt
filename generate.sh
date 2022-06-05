echo 'generating users'
http post localhost:5001/user/generateUsers

echo 'sleeping 25s before generating posts'
sleep 25s

echo 'generating posts'
http post localhost:5002/post/generatePosts

echo 'sleeping 10s before generating agents, firm and offer'
sleep 10s

echo 'generating agents, firm and offer'
http post localhost:5003/agent/generateAgents

echo 'sleeping 5s, before generating api token for user1'
sleep 5s

echo 'generating api token for first user'
userId=$(echo 'user1' | http --stream post localhost:5001/user/findByUsername | jq -r '.[].id')
echo $userId | http --stream post localhost:5001/user/generateApiToken