  curl -H "Origin:http://localhost:4200" http://192.168.1.37:8080/session/signup\
	--header "Content-Type: application/json" \
	--request POST \ 
	--data "{\"name\":\"Name\",\"password\":\"Password123#\",\"email\":\"email@g.c\"}" \

# curl --header "Content-Type: application/json" \
#        --request POST \
#        --data "{\"name\":\"\",\"password\":\"\",\"email\":\"\"}" \
#        http://192.168.1.37:8080/session/signup
#
# "{\"password\":\"Test123#\",\"email\":\"b@ios.m\"}" \
