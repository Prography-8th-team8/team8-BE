export JASYPT_ENCRYPTOR_PASSWORD=${password}

docker ps -q --filter "name=cakk" | grep -q . && docker stop cakk && docker rm cakk | true
docker rmi cakk:lastest
docker build -t cakk:lastest /home/ubuntu/docker/jenkins/workspace/cakk/.
docker run -p 8081:8080 -d --name=cakk -e JASYPT_ENCRYPTOR_PASSWORD=$JASYPT_ENCRYPTOR_PASSWORD cakk:lastest
